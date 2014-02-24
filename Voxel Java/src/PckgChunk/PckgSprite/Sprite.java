package PckgChunk.PckgSprite;

import PckgBlock.Block;
import PckgBlock.BlockType;
import PckgMath.Float3D;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

/**
 *
 * @author Carlos
 */
public class Sprite {
    
    public int SPRITE_SIZE_X, SPRITE_SIZE_Y, SPRITE_SIZE_Z;
    public int MAX_SPRITE_SIZE;
    public float SPRITE_LENGTH;
    public float PERFECT_HALF;
    
    public Block[][][] blocks;
    
    private int indexSize = 0;
    private int activeBlocks = 0;
    
    private int VBOVertexHandle;
    private int VBONormalHandle;
    private int VBOColorHandle;
    
    private float startX;
    private float startY;
    private float startZ;
    
    private SpriteConfiguration sC;
    private int spriteType;
    
    private boolean collision = false;
    private boolean emptySprite = true;
    private boolean generated = false;
    
    public Sprite(int SIZE_X, int SIZE_Y, int SIZE_Z) {
        initSizes(SIZE_X, SIZE_Y, SIZE_Z);
    }
    
    public Sprite(int spriteType, SpriteConfiguration sC, int SIZE_X, int SIZE_Y, int SIZE_Z) {
        initSizes(SIZE_X, SIZE_Y, SIZE_Z);
        generateSprite(spriteType, sC);
    }
    
    private void initSizes(int SIZE_X, int SIZE_Y, int SIZE_Z) {
        SPRITE_LENGTH = 0.125f;
        PERFECT_HALF = (float) 0.5 + SPRITE_LENGTH / 2;
        SPRITE_SIZE_X = SIZE_X;
        SPRITE_SIZE_Y = SIZE_Y;
        SPRITE_SIZE_Z = SIZE_Z;
        MAX_SPRITE_SIZE = SPRITE_SIZE_X * SPRITE_SIZE_Y * SPRITE_SIZE_Z;
    }
    
    public void generateSprite(int spriteType, SpriteConfiguration sC) {
        createSprite();
        fillBlocks(false);
        
        this.sC = sC;
        this.spriteType = spriteType;
    }
    
    private void createSprite() {
        blocks = new Block[SPRITE_SIZE_X][SPRITE_SIZE_Y][SPRITE_SIZE_Z];
    }
    
    private void fillBlocks(boolean visible) {
        for (int x = 0; x < SPRITE_SIZE_X; x++) {
            for (int y = 0; y < SPRITE_SIZE_Y; y++) {
                for (int z = 0; z < SPRITE_SIZE_Z; z++) {
                    blocks[x][y][z] = new Block(new Float3D(x, y, z), visible, BlockType.Type.DIRT);
                }
            }
        }
    }
    
    public void update() {
        if (Keyboard.isKeyDown(Keyboard.KEY_Q))
            getsC().setRotationY(getsC().getRotationY()+1.01f);
        if (Keyboard.isKeyDown(Keyboard.KEY_Z))
            getsC().setRotationY(getsC().getRotationY()-1.01f);
        if (Keyboard.isKeyDown(Keyboard.KEY_E))
            getsC().setScale(getsC().getScale()+.05f);
        if (Keyboard.isKeyDown(Keyboard.KEY_C))
            getsC().setScale(getsC().getScale()-.05f);
        
        if (getsC().getScale() < 0.5f)
            getsC().setScale(getsC().getScale()+.05f);
        if (getsC().getScale() > 4.0f)
            getsC().setScale(getsC().getScale()-.05f);
    }

    public void render() {
        if (isEmptySprite() && isGenerated()) {
            return;
        }

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);

        GL11.glPushMatrix();
            GL11.glTranslatef((-startX*(getsC().getScale()-1)-(0.5f-0.0625f-(-SPRITE_LENGTH*getsC().getCenter().getX()))*(getsC().getScale()-1) + sC.getTranslation().getX()), 
                              (-startY*((getsC().getScale()-1))+0.0625f*(getsC().getScale()-1) + sC.getTranslation().getY()), 
                              (-startZ*(getsC().getScale()-1)-(0.5f-0.0625f-(-SPRITE_LENGTH*getsC().getCenter().getX()))*(getsC().getScale()-1)) + sC.getTranslation().getZ());
            GL11.glScalef(getsC().getScale(), getsC().getScale(), getsC().getScale());
            GL11.glTranslatef((startX)-PERFECT_HALF+1+(SPRITE_LENGTH*getsC().getCenter().getX()), 0, (startZ)-PERFECT_HALF+1+(SPRITE_LENGTH*getsC().getCenter().getX()));
            GL11.glRotatef(getsC().getRotationY(), 0, 1, 0);
            GL11.glTranslatef(-startX+PERFECT_HALF-1-(SPRITE_LENGTH*getsC().getCenter().getX()), 0, -startZ+PERFECT_HALF-1-(SPRITE_LENGTH*getsC().getCenter().getX()));
            
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
            GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBONormalHandle);
            GL11.glNormalPointer(GL11.GL_FLOAT, 0, 0L);
            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
            GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
            GL11.glDrawArrays(GL11.GL_QUADS, 0, indexSize);
        GL11.glPopMatrix();

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
    }

    public void buildMesh() {
        VBOColorHandle = GL15.glGenBuffers();
        VBONormalHandle = GL15.glGenBuffers();
        VBOVertexHandle = GL15.glGenBuffers();

        int noOptimizedBlocks = activeBlocks() * 72;                            //72 = 6 faces * 4 coordinates per face * 3 values per coordinate
        
        FloatBuffer VertexPositionData = BufferUtils.createFloatBuffer(noOptimizedBlocks);
        FloatBuffer VertexNormalData = BufferUtils.createFloatBuffer(noOptimizedBlocks);
        FloatBuffer VertexColorData = BufferUtils.createFloatBuffer(noOptimizedBlocks);

        for (int x = 0; x < SPRITE_SIZE_X; x++) {
            for (int y = 0; y < SPRITE_SIZE_Y; y++) {
                for (int z = 0; z < SPRITE_SIZE_Z; z++) {
                    if (blocks[x][y][z].isActive() == false) {
                        continue;
                    }

                    setEmptySprite(false);

                    boolean xN = true;
                    if (x > 0) {
                        xN = !blocks[x - 1][y][z].isActive();
                    }
                    
                    boolean xP = true;
                    if (x < SPRITE_SIZE_X - 1) {
                        xP = !blocks[x + 1][y][z].isActive();
                    }

                    boolean yN = true;
                    if (y > 0) {
                        yN = !blocks[x][y - 1][z].isActive();
                    }

                    boolean yP = true;
                    if (y < SPRITE_SIZE_Y - 1) {
                        yP = !blocks[x][y + 1][z].isActive();
                    }

                    boolean zN = true;
                    if (z > 0) {
                        zN = !blocks[x][y][z - 1].isActive();
                    }

                    boolean zP = true;
                    if (z < SPRITE_SIZE_Z - 1) {
                        zP = !blocks[x][y][z + 1].isActive();
                    }

                    if (!xN && !xP && !yN && !yP && !zN && !zP) {
                        blocks[x][y][z].setActive(true);
                        continue;
                    }

                    int cubeFloatSize = 0;

                    if (xN) {
                        cubeFloatSize += 12;
                    }
                    if (xP) {
                        cubeFloatSize += 12;
                    }
                    if (yN) {
                        cubeFloatSize += 12;
                    }
                    if (yP) {
                        cubeFloatSize += 12;
                    }
                    if (zN) {
                        cubeFloatSize += 12;
                    }
                    if (zP) {
                        cubeFloatSize += 12;
                    }

                    float cube[] = new float[cubeFloatSize];
                    float normal[] = new float[cubeFloatSize];
                    float color[] = new float[cubeFloatSize];

                    fillFloats(cube, normal, color, (float) startX + x * SPRITE_LENGTH, (float) startY + y * SPRITE_LENGTH, (float) startZ + z * SPRITE_LENGTH, blocks[x][y][z], xN, xP, yN, yP, zN, zP);
                    VertexPositionData.put(cube);
                    VertexNormalData.put(normal);
                    VertexColorData.put(color);
                }
            }
        }

        if (isEmptySprite()) {
            return;
        }

        activeBlocks = activeBlocks();

        VertexColorData.flip();
        VertexNormalData.flip();
        VertexPositionData.flip();

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexPositionData, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBONormalHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexNormalData, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexColorData, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    private void fillFloats(float[] cube, float[] normal, float[] color, float x, float y, float z, Block block, boolean faceXN, boolean faceXP, boolean faceYN, boolean faceYP, boolean faceZN, boolean faceZP) {
        int advance = 0;

        float offset = (float) SPRITE_LENGTH / 2;
        float xP = x + offset;
        float xM = x - offset;
        float yP = y + offset;
        float yM = y - offset;
        float zP = z + offset;
        float zM = z - offset;

        float colorX = block.getColor().getX();
        float colorY = block.getColor().getY();
        float colorZ = block.getColor().getZ();

        //BOTTOM
        if (faceYP) {
            cube[advance + 0] = xP; cube[advance + 1] = yP; cube[advance + 2] = zP;
            cube[advance + 3] = xM; cube[advance + 4] = yP; cube[advance + 5] = zP;
            cube[advance + 6] = xM; cube[advance + 7] = yP; cube[advance + 8] = zM; 
            cube[advance + 9] = xP; cube[advance + 10] = yP; cube[advance + 11] = zM;

            color[advance + 0] = colorX; color[advance + 1] = colorY; color[advance + 2] = colorZ;
            color[advance + 3] = colorX; color[advance + 4] = colorY; color[advance + 5] = colorZ;
            color[advance + 6] = colorX; color[advance + 7] = colorY; color[advance + 8] = colorZ;
            color[advance + 9] = colorX; color[advance + 10] = colorY; color[advance + 11] = colorZ;

            normal[advance + 0] = 0; normal[advance + 1] = 1; normal[advance + 2] = 0;
            normal[advance + 3] = 0; normal[advance + 4] = 1; normal[advance + 5] = 0;
            normal[advance + 6] = 0; normal[advance + 7] = 1; normal[advance + 8] = 0;
            normal[advance + 9] = 0; normal[advance + 10] = 1; normal[advance + 11] = 0;

            advance += 12;
        }

        //TOP
        if (faceYN) {
            cube[advance + 0] = xP; cube[advance + 1] = yM; cube[advance + 2] = zM;
            cube[advance + 3] = xM; cube[advance + 4] = yM; cube[advance + 5] = zM;
            cube[advance + 6] = xM; cube[advance + 7] = yM; cube[advance + 8] = zP;
            cube[advance + 9] = xP; cube[advance + 10] = yM; cube[advance + 11] = zP;

            color[advance + 0] = colorX; color[advance + 1] = colorY; color[advance + 2] = colorZ;
            color[advance + 3] = colorX; color[advance + 4] = colorY; color[advance + 5] = colorZ;
            color[advance + 6] = colorX; color[advance + 7] = colorY; color[advance + 8] = colorZ;
            color[advance + 9] = colorX; color[advance + 10] = colorY; color[advance + 11] = colorZ;

            normal[advance + 0] = 0; normal[advance + 1] = -1; normal[advance + 2] = 0;
            normal[advance + 3] = 0; normal[advance + 4] = -1; normal[advance + 5] = 0;
            normal[advance + 6] = 0; normal[advance + 7] = -1; normal[advance + 8] = 0;
            normal[advance + 9] = 0; normal[advance + 10] = -1; normal[advance + 11] = 0;

            advance += 12;
        }

        //FRONT
        if (faceZN) {
            cube[advance + 0] = xP; cube[advance + 1] = yP; cube[advance + 2] = zM;
            cube[advance + 3] = xM; cube[advance + 4] = yP; cube[advance + 5] = zM;
            cube[advance + 6] = xM; cube[advance + 7] = yM; cube[advance + 8] = zM;
            cube[advance + 9] = xP; cube[advance + 10] = yM; cube[advance + 11] = zM;

            color[advance + 0] = colorX; color[advance + 1] = colorY; color[advance + 2] = colorZ;
            color[advance + 3] = colorX; color[advance + 4] = colorY; color[advance + 5] = colorZ;
            color[advance + 6] = colorX; color[advance + 7] = colorY; color[advance + 8] = colorZ;
            color[advance + 9] = colorX; color[advance + 10] = colorY; color[advance + 11] = colorZ;

            normal[advance + 0] = 0; normal[advance + 1] = 0; normal[advance + 2] = -1;
            normal[advance + 3] = 0; normal[advance + 4] = 0; normal[advance + 5] = -1;
            normal[advance + 6] = 0; normal[advance + 7] = 0; normal[advance + 8] = -1;
            normal[advance + 9] = 0; normal[advance + 10] = 0; normal[advance + 11] = -1;

            advance += 12;
        }

        //BACK
        if (faceZP) {
            cube[advance + 0] = xP; cube[advance + 1] = yM; cube[advance + 2] = zP;
            cube[advance + 3] = xM; cube[advance + 4] = yM; cube[advance + 5] = zP;
            cube[advance + 6] = xM; cube[advance + 7] = yP; cube[advance + 8] = zP;
            cube[advance + 9] = xP; cube[advance + 10] = yP; cube[advance + 11] = zP;

            color[advance + 0] = colorX; color[advance + 1] = colorY; color[advance + 2] = colorZ;
            color[advance + 3] = colorX; color[advance + 4] = colorY; color[advance + 5] = colorZ;
            color[advance + 6] = colorX; color[advance + 7] = colorY; color[advance + 8] = colorZ;
            color[advance + 9] = colorX; color[advance + 10] = colorY; color[advance + 11] = colorZ;

            normal[advance + 0] = 0; normal[advance + 1] = 0; normal[advance + 2] = 1;
            normal[advance + 3] = 0; normal[advance + 4] = 0; normal[advance + 5] = 1;
            normal[advance + 6] = 0; normal[advance + 7] = 0; normal[advance + 8] = 1;
            normal[advance + 9] = 0; normal[advance + 10] = 0; normal[advance + 11] = 1;

            advance += 12;
        }

        //LEFT
        if (faceXN) {
            cube[advance + 0] = xM; cube[advance + 1] = yP; cube[advance + 2] = zM;
            cube[advance + 3] = xM; cube[advance + 4] = yP; cube[advance + 5] = zP;
            cube[advance + 6] = xM; cube[advance + 7] = yM; cube[advance + 8] = zP;
            cube[advance + 9] = xM; cube[advance + 10] = yM; cube[advance + 11] = zM;

            color[advance + 0] = colorX; color[advance + 1] = colorY; color[advance + 2] = colorZ;
            color[advance + 3] = colorX; color[advance + 4] = colorY; color[advance + 5] = colorZ;
            color[advance + 6] = colorX; color[advance + 7] = colorY; color[advance + 8] = colorZ;
            color[advance + 9] = colorX; color[advance + 10] = colorY; color[advance + 11] = colorZ;

            normal[advance + 0] = -1; normal[advance + 1] = 0; normal[advance + 2] = 0;
            normal[advance + 3] = -1; normal[advance + 4] = 0; normal[advance + 5] = 0;
            normal[advance + 6] = -1; normal[advance + 7] = 0; normal[advance + 8] = 0;
            normal[advance + 9] = -1; normal[advance + 10] = 0; normal[advance + 11] = 0;

            advance += 12;
        }

        //RIGHT
        if (faceXP) {
            cube[advance + 0] = xP; cube[advance + 1] = yP; cube[advance + 2] = zP;
            cube[advance + 3] = xP; cube[advance + 4] = yP; cube[advance + 5] = zM;
            cube[advance + 6] = xP; cube[advance + 7] = yM; cube[advance + 8] = zM;
            cube[advance + 9] = xP; cube[advance + 10] = yM; cube[advance + 11] = zP;

            color[advance + 0] = colorX; color[advance + 1] = colorY; color[advance + 2] = colorZ;
            color[advance + 3] = colorX; color[advance + 4] = colorY; color[advance + 5] = colorZ;
            color[advance + 6] = colorX; color[advance + 7] = colorY; color[advance + 8] = colorZ;
            color[advance + 9] = colorX; color[advance + 10] = colorY; color[advance + 11] = colorZ;

            normal[advance + 0] = 1; normal[advance + 1] = 0; normal[advance + 2] = 0;
            normal[advance + 3] = 1; normal[advance + 4] = 0; normal[advance + 5] = 0;
            normal[advance + 6] = 1; normal[advance + 7] = 0; normal[advance + 8] = 0;
            normal[advance + 9] = 1; normal[advance + 10] = 0; normal[advance + 11] = 0;

            advance += 12;
        }

        indexSize += advance / 3;
    }

    private int activeBlocks() {
        int count = 0;

        for (int x = 0; x < SPRITE_SIZE_X; x++) {
            for (int y = 0; y < SPRITE_SIZE_Y; y++) {
                for (int z = 0; z < SPRITE_SIZE_Z; z++) {
                    if (blocks[x][y][z].isActive()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public void print() {
        System.out.println("SPRITE (X: " + startX + " - Y: " + startY + " - Z: " + startZ + ")");
    }
    
    public void setStart(float startX, float startY, float startZ) {
        this.startX = startX;
        this.startY = startY + .53125f;
        this.startZ = startZ;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    public float getStartZ() {
        return startZ;
    }

    public void setsC(SpriteConfiguration sC) {
        this.sC = sC;
    }
    
    public SpriteConfiguration getsC() {
        return sC;
    }
    
    public void setSpriteType(int spriteType) {
        this.spriteType = spriteType;
    }
    
    public int getSpriteType() {
        return spriteType;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public void setEmptySprite(boolean emptySprite) {
        this.emptySprite = emptySprite;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean isGenerated() {
        return generated;
    }

    public boolean isEmptySprite() {
        return emptySprite;
    }

    public boolean isCollision() {
        return collision;
    }
}