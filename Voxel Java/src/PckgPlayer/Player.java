package PckgPlayer;

import PckgChunk.Chunk;
import PckgChunk.ChunkManager;
import PckgChunk.PckgSprite.Sprite;
import PckgChunk.PckgSprite.SpriteManager;
import PckgEntity.Entity;
import PckgInput.InputHandler;
import PckgMath.Calculations;
import PckgMath.Float3D;
import PckgMath.PckgStructure.AABB;
import PckgMath.PckgStructure.RayBlockIntersection;
import PckgOverlay.Crosshair;
import PckgUtil.Configuration;
import java.util.List;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;

public class Player extends Entity {

    private Crosshair crosshair;
    private Camera camera;
    private Body body;
    
    private float bobbing, bobbingProcess;
    private float rayCastLength;
    private float smashTime, buildTime;
    
    private Float3D aimedBlockPosition;
    private Float3D nextBlockPosition;
    private Float3D aimedAdjacentBlockPosition;
    private Float3D SaimedBlockPosition;
    private Float3D SnextBlockPosition;
    private Float3D SaimedAdjacentBlockPosition;
    
    private AABB aimedBlockAABB;
    private AABB rayAABB;
    private AABB intersectionTestingAABB;
    private AABB SaimedBlockAABB;
    private AABB SrayAABB;
    private AABB SintersectionTestingAABB;

    public Player(float x, float y, float z) {
        super(x, y, z);
        
        crosshair = new Crosshair();
        camera = new Camera();
        body = new Body();

        rayCastLength = stats.rayCastLength;
        smashTime = stats.smashTime;
        buildTime = stats.buildTime;

        aimedBlockPosition = new Float3D();
        nextBlockPosition = new Float3D();
        aimedAdjacentBlockPosition = new Float3D();
        SaimedBlockPosition = new Float3D();
        SnextBlockPosition = new Float3D();
        SaimedAdjacentBlockPosition = new Float3D();

        aimedBlockAABB = new AABB(new Float3D(), new Float3D(0.5f, 0.5f, 0.5f));
        rayAABB = new AABB(new Float3D(), new Float3D(0.5f, 0.5f, 0.5f));
        intersectionTestingAABB = new AABB(new Float3D(), new Float3D(0.5f, 0.5f, 0.5f));
        SaimedBlockAABB = new AABB(new Float3D(), new Float3D(0.125f, 0.125f, 0.125f));
        SrayAABB = new AABB(new Float3D(), new Float3D(0.125f, 0.125f, 0.125f));
        SintersectionTestingAABB = new AABB(new Float3D(), new Float3D(0.125f, 0.125f, 0.125f));
    }

    public Player(Float3D spawnPoint) {
        this(spawnPoint.getX(), spawnPoint.getY(), spawnPoint.getZ());
    }

    @Override
    public void update() {
        keyboardInput();
        physics();
        movement();
        
        position.set(x, y, z);
        getCamera().setPosition(x, y + currentEyeHeight, z);
        getCamera().setRotation(rotX, rotY, getBobbing());
        
        body.update();

        rayCastBlock();
        mouseInput();
        
        if (smashTime < stats.smashTime)
            smashTime += 0.01f;
        if (buildTime < stats.buildTime)
            buildTime += 0.01f;
    }

    @Override
    public void render() {
        aimedBlockAABB.render(0, 0, 0, 1);
        SaimedBlockAABB.render(0, 0, 0, 1);
        
        glPushMatrix();
            glClear(GL_DEPTH_BUFFER_BIT);
            
            glMatrixMode(GL_PROJECTION);
                glLoadIdentity();
                glOrtho(0, Configuration.getInstance().getWidth(), 0, Configuration.getInstance().getHeight(), -1, 1);
            glMatrixMode(GL_MODELVIEW);
                glLoadIdentity();
                
            getCrosshair().render();
        glPopMatrix();

        getCamera().lookThrough(512f);
    }
    
    public void keyboardInput() {
        if (InputHandler.isActionHold(InputHandler.FLY)) {
            isFlying = !isFlying;
        }

        if (InputHandler.isActionHold(InputHandler.RUN) && !isWalking) {
            isRunning = true;
        } else {
            isRunning = false;
        }

        if (InputHandler.isActionHold(InputHandler.WALK) && !isRunning) {
            isWalking = true;
        } else {
            isWalking = false;
        }

        if (InputHandler.isActionHold(InputHandler.CROUCH)) {
            currentEyeHeight = 0.2f;
            currentPlayerHeight = 0.4f;

            isCrouching = true;
        } else {
            currentEyeHeight = 1.2f;
            currentPlayerHeight = 1.4f;

            isCrouching = false;
        }
    }
    
    public void mouseInput() {
        if (InputHandler.isActionHold(InputHandler.MOUSE_LEFT) && smashTime >= stats.smashTime) {
            smash();
            smashTime = 0;
        }
        if (InputHandler.isActionHold(InputHandler.MOUSE_RIGHT) && buildTime >= stats.buildTime) {
            build();
            buildTime = 0;
        }
    }

    @Override
    public void movement() {
        super.movement();
        
        float speed = (float) Math.sqrt(speedForward * speedForward + speedSide * speedSide);
        speed /= maxSpeed;

        if (isRunning) {
            speed /= 2;
        }

        if (isWalking) {
            speed /= 2;
        }

        detectCollision(xStep, zStep);

        bobbingProcess += (step * 10.0f);
        bobbingProcess = Calculations.simplifyRadians(getBobbingProcess());

        float dx = Mouse.getDX();
        float dy = Mouse.getDY();

        dx /= 300.0f;
        dy /= 300.0f;

        rotY -= dx / Configuration.getInstance().getMouseSensitivity();
        rotX += dy / Configuration.getInstance().getMouseSensitivity();

        rotY = Calculations.simplifyRadians(rotY);
        rotX = Calculations.clamp(rotX, (float) (-Math.PI / 2.001f), (float) (Math.PI / 2.001f));

        if (isFlying) {
            bobbing *= 0.8f;
        } else {
            bobbing = (float) (Math.sin(getBobbingProcess()) * 0.03f * speed * (Math.PI / 2.0f - Math.abs(rotX)) / (Math.PI / 2.0f));
        }
    }

    @Override
    public void detectCollision(float xStep, float zStep) {
        super.detectCollision(xStep, zStep);
    }

    @Override
    public void physics() {
        super.physics();
    }

    private void rayCastBlock() {
        float rayCastLengthSquared = getRayCastLength() * getRayCastLength();

        Float3D rayDirection = getCamera().getLookDirection();
        Float3D rayOrigin = getCamera().getPosition();

        rayDirection.normalise();

        rayAABB.getPosition().set(rayOrigin);
        rayAABB.getPosition().addFactor(rayDirection, getRayCastLength() * 0.5f);

        rayAABB.getDimensions().set(Math.abs(rayDirection.getX()), Math.abs(rayDirection.getY()), Math.abs(rayDirection.getZ()));
        rayAABB.getDimensions().scale(getRayCastLength() * 0.5f);

        RayBlockIntersection.Intersection closestIntersection = null;

        Chunk chunk;
        Float3D tmp = new Float3D();
        Float3D newAimedBlockPosition = new Float3D();
        boolean block;
        boolean selected = false;

        for (int rayX = Calculations.floor(rayAABB.minX()); rayX <= Calculations.ceil(rayAABB.maxX()); rayX++) {
            for (int rayY = Calculations.floor(rayAABB.minY()); rayY <= Calculations.ceil(rayAABB.maxY()); rayY++) {
                for (int rayZ = Calculations.floor(rayAABB.minZ()); rayZ <= Calculations.ceil(rayAABB.maxZ()); rayZ++) {                   
                    chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(rayX, rayZ);
                    if (chunk == null) {
                        return;
                    }
                    
                    int tmpX = rayX - chunk.getStartX();
                    int tmpZ = rayZ - chunk.getStartZ();
                    
                    if (chunk.blocks[tmpX][rayY][tmpZ] == null) {
                        continue;
                    }
                    
                    block = chunk.blocks[tmpX][rayY][tmpZ].isActive();
                    if (!block) {
                        continue;
                    }

                    tmp.set(rayX - 0.5f, rayY, rayZ - 0.5f);
                    tmp.sub(position);
                    float lengthSquared = tmp.lengthSquared();

                    if (lengthSquared < rayCastLengthSquared) {
                        intersectionTestingAABB.getPosition().set(rayX + 0.5f, rayY, rayZ + 0.5f);
                        intersectionTestingAABB.recalcVertices();

                        List<RayBlockIntersection.Intersection> intersections = RayBlockIntersection.executeIntersection(Calculations.floor(rayX + 0.5f), rayY, Calculations.floor(rayZ + 0.5f), intersectionTestingAABB, rayOrigin, rayDirection);
                        if (!intersections.isEmpty()) {
                            if (closestIntersection == null || intersections.get(0).getDistance() < closestIntersection.getDistance()) {
                                closestIntersection = intersections.get(0);
                                newAimedBlockPosition.set(rayX, rayY, rayZ);

                                aimedBlockAABB.getPosition().set(aimedBlockPosition.getX() - 0.5f, aimedBlockPosition.getY() - 0.5f, aimedBlockPosition.getZ() - 0.5f);                                
                                aimedBlockAABB.getPosition().add(new Float3D(0.5f, 0.5f, 0.5f));
                                
                                selected = true;
                            }
                        }
                    }
                }
            }
        }
        
        if (closestIntersection != null) {
            if (!aimedBlockPosition.equals(newAimedBlockPosition)) {
                aimedBlockPosition.set(newAimedBlockPosition);
                aimedBlockAABB.recalcVertices();
            }
            
            aimedAdjacentBlockPosition = closestIntersection.calcAdjacentBlockPos();
        } else {
            aimedBlockPosition.setY(-1);
            aimedAdjacentBlockPosition = null;
        }
        
        if (!selected) {
            aimedBlockAABB.visible = false;
        } else {
            aimedBlockAABB.visible = true;
        }
    }
    
    private void smash() {
        int xx = (int) SaimedBlockAABB.getPosition().getX();
        int yy = (int) SaimedBlockAABB.getPosition().getY();
        int zz = (int) SaimedBlockAABB.getPosition().getZ();
        
        SpriteManager.getInstance().removeBlock(xx, yy, zz);
    }
    
    private void build() {
        if (SaimedBlockPosition.getY() != -1) {
            int bX = (int) SaimedAdjacentBlockPosition.getX();
            int bY = (int) SaimedAdjacentBlockPosition.getY();
            int bZ = (int) SaimedAdjacentBlockPosition.getZ();

            int pX = Calculations.floor(x);
            int pY = Calculations.floor(y);
            int pZ = Calculations.floor(z);

            if (!(bX == pX && (bY == pY || bY == pY + 1) && bZ == pZ))
                ChunkManager.getInstance().addBlock(bX, bY, bZ);
        }
    }
    
    public Crosshair getCrosshair() {
        return crosshair;
    }

    public Camera getCamera() {
        return camera;
    }

    public float getBobbing() {
        return bobbing;
    }

    public float getBobbingProcess() {
        return bobbingProcess;
    }
    
    public float getRayCastLength() {
        return rayCastLength;
    }
}
