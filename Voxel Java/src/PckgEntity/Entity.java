package PckgEntity;

import PckgChunk.Chunk;
import PckgChunk.ChunkManager;
import PckgChunk.PckgSprite.Sprite;
import PckgInput.InputHandler;
import PckgMath.Calculations;
import PckgMath.Float3D;

public abstract class Entity {
    
    public EntityStats stats;
    
    public float step;

    public float x, y, z;    
    public float rotX, rotY;
    
    public Float3D position;
    public Float3D spawnPoint;
    
    public float currentEyeHeight;
    public float currentPlayerHeight;
    
    public boolean onGround;
    public boolean isCrouching;
    public boolean isRunning;
    public boolean isWalking;
    public boolean isFlying;
    
    //tmp variables
    public float xStep;
    public float zStep;
    
    public float speedForward;
    public float speedSide;
    public float speedY;
    public float maxSpeed;
    public float acceleration;

    public Entity(float x, float y, float z) {
        stats = new EntityStats();
        
        step = 0.02f;
        
        this.x = x;
        this.y = y;
        this.z = z;

        position = new Float3D(x, y, z);
        spawnPoint = new Float3D(x, y, z);
        
        currentEyeHeight = stats.eyeHeight;
        currentPlayerHeight = stats.playerHeight;
        
        onGround = isCrouching = isRunning = isWalking = isFlying = false;
    
        speedForward = speedSide = speedY = 0.0f;
        maxSpeed = 3.0f;
        acceleration = 9.8f;
    }

    public abstract void update();
    public abstract void render();

    public void physics() {
        float supportHeight = Float.NEGATIVE_INFINITY;

        boolean support = false;
        boolean subSupport = false;

        Chunk chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(x, z);

        float xx = Calculations.floor(x), yy = Calculations.floor(y) - 1, zz = Calculations.floor(z);

        if (chunk != null) {
            xx -= chunk.getStartX();
            zz -= chunk.getStartZ();

            if (chunk.blocks[Calculations.floor(xx)][Calculations.floor(yy)][Calculations.floor(zz)].isActive()) {
                support = true;
            }
        }

        xx = Calculations.floor(x);
        yy = Calculations.floor(y) - 2;
        zz = Calculations.floor(z);

        if (chunk != null) {
            xx -= chunk.getStartX();
            zz -= chunk.getStartZ();

            if (chunk.blocks[Calculations.floor(xx)][Calculations.floor(yy)][Calculations.floor(zz)].isActive()) {
                subSupport = true;
            }
        }

        if (InputHandler.isActionHold(InputHandler.JUMP)) {
            if (onGround) {
                speedY = (isRunning) ? 24f : 18f;
                onGround = false;
            } else if (isFlying) {
                speedY += 18f * step;
            }
        }

        if (InputHandler.isActionHold(InputHandler.DESCEND)) {
            speedY = -5f;
        }

        if (support) {
            supportHeight = Calculations.floor(y);
            onGround = false;
        } else if (subSupport) {
            supportHeight = Calculations.floor(y) - 1;
            onGround = false;
        }

        if (supportHeight > y) {
            y = supportHeight;
            speedY = 0.0f;

            onGround = true;
        } else {
            if (!isFlying) {
                speedY -= step * 55f;
            } else {
                float newSpeedY = speedY * 0.1f;
                float diffY = newSpeedY - speedY;

                speedY += diffY * step;
            }

            y += speedY * step;

            if (speedY > 0.0f) {
                int headbangY = Calculations.floor(y + 0.1f) + 2;

                boolean headbang = false;

                chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(x, z);

                if (chunk != null) {
                    headbang = chunk.blocks[Calculations.floor(xx)][headbangY][Calculations.floor(zz)].isActive();
                }

                if (headbang && (headbangY < y + currentPlayerHeight)) {
                    y = (float) headbangY - currentPlayerHeight;
                    speedY = 0.0f;
                }
            }

            onGround = false;

            if (supportHeight >= y) {
                y = supportHeight;
                speedY = 0.0f;

                onGround = true;
            }
        }
    }
    
    public void movement() {
        float factor = (onGround ? 3.0f : 0.5f);
        float accelerationStep = acceleration * step;

        xStep = 0.0f;
        zStep = 0.0f;

        if (InputHandler.isActionHold(InputHandler.MOVE_FORWARD)) {
            speedForward = Math.min(maxSpeed * (isFlying ? 4.0f : stats.movementSpeedForward), speedForward + acceleration * step);

            if (isRunning && !isCrouching)
                if (speedForward > 0)
                    speedForward *= stats.movementSpeedRun;

            if (isWalking)
                speedForward *= stats.movementSpeedWalk;
            if (isCrouching)
                speedForward *= stats.movementSpeedCrouch;
        } else if (InputHandler.isActionHold(InputHandler.MOVE_BACK)) {
            if (speedForward > 0.0f) {
                speedForward -= factor * accelerationStep;
                if (speedForward < 0.0f)
                    speedForward = 0.0f;
            }
            
            speedForward = Math.max(-maxSpeed * (isFlying ? 4.0f : stats.movementSpeedBackward), speedForward - acceleration * step);

            if (isWalking)
                speedForward *= stats.movementSpeedWalk;
        } else {
            if (speedForward != 0.0f) {
                if (speedForward < 0.0f) {
                    speedForward += factor * accelerationStep;
                    if (speedForward > 0.0f)
                        speedForward = 0.0f;
                } else {
                    speedForward -= factor * accelerationStep;
                    if (speedForward < 0.0f)
                        speedForward = 0.0f;
                }
            }
        }
        
        if (speedForward > 15.0f)
            speedForward = 15.0f;
        if (speedForward < -2.25f)
            speedForward = -2.25f;

        xStep += (float) (Math.cos(rotY)) * speedForward * step;
        zStep += (float) (Math.sin(-rotY)) * speedForward * step;

        if (InputHandler.isActionHold(InputHandler.MOVE_LEFT)) {
            speedSide = Math.max(-maxSpeed * (isFlying ? 4.0f : stats.movementSpeedSide), speedSide - acceleration * step);
        } else if (InputHandler.isActionHold(InputHandler.MOVE_RIGHT)) {
            speedSide = Math.min(maxSpeed * (isFlying ? 4.0f : stats.movementSpeedSide), speedSide + acceleration * step);
        } else {
            if (speedSide != 0.0f) {
                if (speedSide < 0.0f) {
                    speedSide += factor * accelerationStep;
                    if (speedSide > 0.0f)
                        speedSide = 0.0f;
                } else {
                    speedSide -= factor * accelerationStep;
                    if (speedSide < 0.0f)
                        speedSide = 0.0f;
                }
            }
        }

        xStep += (float) (Math.sin(rotY)) * speedSide * step;
        zStep += (float) (Math.cos(rotY)) * speedSide * step;

        x += xStep;
        z += zStep;
    }
    
    
    public void detectCollision(float xStep, float zStep) {
        Chunk chunk;

        boolean wallBot = false;
        boolean wallTop = false;
        boolean wallSprite = false;

        if (!isCrouching) {
            float xx = Calculations.floor(x), yy = Calculations.floor(y), zz = Calculations.floor(z);

            chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(x, z);

            if (chunk != null) {
                xx -= chunk.getStartX();
                zz -= chunk.getStartZ();

                if (chunk.blocks[Calculations.floor(xx)][Calculations.floor(yy)][Calculations.floor(zz)].isActive())
                    wallBot = true;
            }
        }

        {
            float xx = Calculations.floor(x), yy = Calculations.floor(y) + 1, zz = Calculations.floor(z);

            if (isCrouching)
                yy -= 1;

            chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(x, z);

            if (chunk != null) {
                xx -= chunk.getStartX();
                zz -= chunk.getStartZ();

                if (chunk.blocks[Calculations.floor(xx)][Calculations.floor(yy)][Calculations.floor(zz)].isActive())
                    wallTop = true;
            }
        }

        chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(x, z);

        if (chunk != null)
            for (Sprite sprite : chunk.sprites)
                if (((Calculations.ceil(sprite.getStartX()) == Calculations.ceil(x - 1)) && (Calculations.ceil(sprite.getStartY()) == Calculations.ceil(y - 1)) && (Calculations.ceil(sprite.getStartZ()) == Calculations.ceil(z - 1))) && sprite.isCollision())
                    wallSprite = true;

        if (wallBot || wallTop || wallSprite) {
            x -= xStep;
            z -= zStep;

            speedForward *= -0.2f;
            speedSide *= -0.2f;
        }
    }
}
