package PckgChunk;

import PckgBlock.Block;
import PckgBlock.BlockType;
import PckgChunk.PckgSprite.Sprite;
import PckgMath.Float3D;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

/**
 *
 * @author Carlos
 */
public class Chunk {

    public static final int CHUNK_SIZE = 16;
    public static final int CUBE_LENGTH = 1;
    public static final int Y_CHUNK_SIZE = CHUNK_SIZE * CHUNK_SIZE;
    public static final int MAX_CHUNK_SIZE = CHUNK_SIZE * Y_CHUNK_SIZE * CHUNK_SIZE;
    
    public Block[][][] blocks;
    public List<Sprite> sprites;
    
    private int indexSize;
    private int activeBlocks;
    
    private Chunk[] neighbours;                                                 //[0] Left | [1] Right | [2] Front | [3] Back
    
    private int VBOVertexHandle;
    private int VBONormalHandle;
    private int VBOColorHandle;
    
    private int startX;
    private int startY;
    private int startZ;
    
    private boolean emptyChunk = true;
    private boolean generated = false;
    private boolean allNeighbours = false;

    public Chunk(int startX, int startY, int startZ, boolean visible) {
        blocks = new Block[CHUNK_SIZE][Y_CHUNK_SIZE][CHUNK_SIZE];
        sprites = new ArrayList<>();

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < Y_CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    blocks[x][y][z] = new Block(new Float3D(x, y, z), visible, BlockType.Type.DIRT);
                }
            }
        }

        this.startX = startX;
        this.startY = startY;
        this.startZ = startZ;

        neighbours = new Chunk[4];

        indexSize = 0;
    }

    public void assignNeighbours() {
        Chunk chunk;

        if (neighbours[0] == null) {
            chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX - 16, startZ);
            if (chunk != null) {
                neighbours[0] = chunk;
                buildMesh();
            }
        }

        if (neighbours[1] == null) {
            chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX + 16, startZ);
            if (chunk != null) {
                neighbours[1] = chunk;
                buildMesh();
            }
        }

        if (neighbours[2] == null) {
            chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX, startZ - 16);
            if (chunk != null) {
                neighbours[2] = chunk;
                buildMesh();
            }
        }

        if (neighbours[3] == null) {
            chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX, startZ + 16);
            if (chunk != null) {
                neighbours[3] = chunk;
                buildMesh();
            }
        }

        if (neighbours[0] != null && neighbours[1] != null && neighbours[2] != null && neighbours[3] != null) {
            setAllNeighbours(true);
        }
    }

    public void update() {
        if (!allNeighbours) {
            assignNeighbours();
        }
    }

    public void render() {
        if (isEmptyChunk() && isGenerated()) {
            return;
        }

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);

        GL11.glPushMatrix();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBONormalHandle);
        GL11.glNormalPointer(GL11.GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
        GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
        GL11.glDrawArrays(GL11.GL_QUADS, 0, indexSize / 3);
        GL11.glPopMatrix();

        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
    }

    public void buildMesh() {
        VBOColorHandle = GL15.glGenBuffers();
        VBONormalHandle = GL15.glGenBuffers();
        VBOVertexHandle = GL15.glGenBuffers();

        int noOptimizedBlocks = activeBlocks() * 72;                                     //72 = 6 faces * 4 coordinates per face * 3 values per coordinate

        FloatBuffer VertexPositionData = BufferUtils.createFloatBuffer(noOptimizedBlocks);
        FloatBuffer VertexNormalData = BufferUtils.createFloatBuffer(noOptimizedBlocks);
        FloatBuffer VertexColorData = BufferUtils.createFloatBuffer(noOptimizedBlocks);

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < Y_CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    if (blocks[x][y][z].isActive() == false) {
                        continue;
                    }

                    setEmptyChunk(false);

                    boolean xN = true;
                    if (x > 0) {
                        xN = !blocks[x - 1][y][z].isActive();
                    } else {
                        if (neighbours[0] != null) {
                            xN = !neighbours[0].blocks[15][y][z].isActive();
                        }
                    }

                    boolean xP = true;
                    if (x < CHUNK_SIZE - 1) {
                        xP = !blocks[x + 1][y][z].isActive();
                    } else {
                        if (neighbours[1] != null) {
                            xP = !neighbours[1].blocks[0][y][z].isActive();
                        }
                    }

                    boolean yN = true;
                    if (y > 0) {
                        yN = !blocks[x][y - 1][z].isActive();
                    }

                    boolean yP = true;
                    if (y < CHUNK_SIZE * CHUNK_SIZE - 1) {
                        yP = !blocks[x][y + 1][z].isActive();
                    }

                    boolean zN = true;
                    if (z > 0) {
                        zN = !blocks[x][y][z - 1].isActive();
                    } else {
                        if (neighbours[2] != null) {
                            zN = !neighbours[2].blocks[x][y][15].isActive();
                        }
                    }

                    boolean zP = true;
                    if (z < CHUNK_SIZE - 1) {
                        zP = !blocks[x][y][z + 1].isActive();
                    } else {
                        if (neighbours[3] != null) {
                            zP = !neighbours[3].blocks[x][y][0].isActive();
                        }
                    }
//a

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
                    
                    if (cubeFloatSize != 0)
                        blocks[x][y][z].setActive(true);

                    float cube[] = new float[cubeFloatSize];
                    float normal[] = new float[cubeFloatSize];
                    float color[] = new float[cubeFloatSize];
                    
                    fillFloats(cube, normal, color, (float) startX + x * CUBE_LENGTH, (float) startY + y * CUBE_LENGTH, (float) startZ + z * CUBE_LENGTH, blocks[x][y][z], xN, xP, yN, yP, zN, zP);
                    VertexPositionData.put(cube);
                    VertexNormalData.put(normal);
                    VertexColorData.put(color);
                }
            }
        }

        if (isEmptyChunk()) {
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

        float offset = (float) CUBE_LENGTH / 2;
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
            cube[advance + 0] = xP;
            cube[advance + 1] = yP;
            cube[advance + 2] = zP;
            cube[advance + 3] = xM;
            cube[advance + 4] = yP;
            cube[advance + 5] = zP;
            cube[advance + 6] = xM;
            cube[advance + 7] = yP;
            cube[advance + 8] = zM;
            cube[advance + 9] = xP;
            cube[advance + 10] = yP;
            cube[advance + 11] = zM;

            color[advance + 0] = colorX;
            color[advance + 1] = colorY;
            color[advance + 2] = colorZ;
            color[advance + 3] = colorX;
            color[advance + 4] = colorY;
            color[advance + 5] = colorZ;
            color[advance + 6] = colorX;
            color[advance + 7] = colorY;
            color[advance + 8] = colorZ;
            color[advance + 9] = colorX;
            color[advance + 10] = colorY;
            color[advance + 11] = colorZ;

            normal[advance + 0] = 0;
            normal[advance + 1] = 1;
            normal[advance + 2] = 0;
            normal[advance + 3] = 0;
            normal[advance + 4] = 1;
            normal[advance + 5] = 0;
            normal[advance + 6] = 0;
            normal[advance + 7] = 1;
            normal[advance + 8] = 0;
            normal[advance + 9] = 0;
            normal[advance + 10] = 1;
            normal[advance + 11] = 0;

            advance += 12;
        }

        //TOP
        if (faceYN) {
            cube[advance + 0] = xP;
            cube[advance + 1] = yM;
            cube[advance + 2] = zM;
            cube[advance + 3] = xM;
            cube[advance + 4] = yM;
            cube[advance + 5] = zM;
            cube[advance + 6] = xM;
            cube[advance + 7] = yM;
            cube[advance + 8] = zP;
            cube[advance + 9] = xP;
            cube[advance + 10] = yM;
            cube[advance + 11] = zP;

            color[advance + 0] = colorX;
            color[advance + 1] = colorY;
            color[advance + 2] = colorZ;
            color[advance + 3] = colorX;
            color[advance + 4] = colorY;
            color[advance + 5] = colorZ;
            color[advance + 6] = colorX;
            color[advance + 7] = colorY;
            color[advance + 8] = colorZ;
            color[advance + 9] = colorX;
            color[advance + 10] = colorY;
            color[advance + 11] = colorZ;

            normal[advance + 0] = 0;
            normal[advance + 1] = -1;
            normal[advance + 2] = 0;
            normal[advance + 3] = 0;
            normal[advance + 4] = -1;
            normal[advance + 5] = 0;
            normal[advance + 6] = 0;
            normal[advance + 7] = -1;
            normal[advance + 8] = 0;
            normal[advance + 9] = 0;
            normal[advance + 10] = -1;
            normal[advance + 11] = 0;

            advance += 12;
        }

        //FRONT
        if (faceZN) {
            cube[advance + 0] = xP;
            cube[advance + 1] = yP;
            cube[advance + 2] = zM;
            cube[advance + 3] = xM;
            cube[advance + 4] = yP;
            cube[advance + 5] = zM;
            cube[advance + 6] = xM;
            cube[advance + 7] = yM;
            cube[advance + 8] = zM;
            cube[advance + 9] = xP;
            cube[advance + 10] = yM;
            cube[advance + 11] = zM;

            color[advance + 0] = colorX;
            color[advance + 1] = colorY;
            color[advance + 2] = colorZ;
            color[advance + 3] = colorX;
            color[advance + 4] = colorY;
            color[advance + 5] = colorZ;
            color[advance + 6] = colorX;
            color[advance + 7] = colorY;
            color[advance + 8] = colorZ;
            color[advance + 9] = colorX;
            color[advance + 10] = colorY;
            color[advance + 11] = colorZ;

            normal[advance + 0] = 0;
            normal[advance + 1] = 0;
            normal[advance + 2] = -1;
            normal[advance + 3] = 0;
            normal[advance + 4] = 0;
            normal[advance + 5] = -1;
            normal[advance + 6] = 0;
            normal[advance + 7] = 0;
            normal[advance + 8] = -1;
            normal[advance + 9] = 0;
            normal[advance + 10] = 0;
            normal[advance + 11] = -1;

            advance += 12;
        }

        //BACK
        if (faceZP) {
            cube[advance + 0] = xP;
            cube[advance + 1] = yM;
            cube[advance + 2] = zP;
            cube[advance + 3] = xM;
            cube[advance + 4] = yM;
            cube[advance + 5] = zP;
            cube[advance + 6] = xM;
            cube[advance + 7] = yP;
            cube[advance + 8] = zP;
            cube[advance + 9] = xP;
            cube[advance + 10] = yP;
            cube[advance + 11] = zP;

            color[advance + 0] = colorX;
            color[advance + 1] = colorY;
            color[advance + 2] = colorZ;
            color[advance + 3] = colorX;
            color[advance + 4] = colorY;
            color[advance + 5] = colorZ;
            color[advance + 6] = colorX;
            color[advance + 7] = colorY;
            color[advance + 8] = colorZ;
            color[advance + 9] = colorX;
            color[advance + 10] = colorY;
            color[advance + 11] = colorZ;

            normal[advance + 0] = 0;
            normal[advance + 1] = 0;
            normal[advance + 2] = 1;
            normal[advance + 3] = 0;
            normal[advance + 4] = 0;
            normal[advance + 5] = 1;
            normal[advance + 6] = 0;
            normal[advance + 7] = 0;
            normal[advance + 8] = 1;
            normal[advance + 9] = 0;
            normal[advance + 10] = 0;
            normal[advance + 11] = 1;

            advance += 12;
        }

        //LEFT
        if (faceXN) {
            cube[advance + 0] = xM;
            cube[advance + 1] = yP;
            cube[advance + 2] = zM;
            cube[advance + 3] = xM;
            cube[advance + 4] = yP;
            cube[advance + 5] = zP;
            cube[advance + 6] = xM;
            cube[advance + 7] = yM;
            cube[advance + 8] = zP;
            cube[advance + 9] = xM;
            cube[advance + 10] = yM;
            cube[advance + 11] = zM;

            color[advance + 0] = colorX;
            color[advance + 1] = colorY;
            color[advance + 2] = colorZ;
            color[advance + 3] = colorX;
            color[advance + 4] = colorY;
            color[advance + 5] = colorZ;
            color[advance + 6] = colorX;
            color[advance + 7] = colorY;
            color[advance + 8] = colorZ;
            color[advance + 9] = colorX;
            color[advance + 10] = colorY;
            color[advance + 11] = colorZ;

            normal[advance + 0] = -1;
            normal[advance + 1] = 0;
            normal[advance + 2] = 0;
            normal[advance + 3] = -1;
            normal[advance + 4] = 0;
            normal[advance + 5] = 0;
            normal[advance + 6] = -1;
            normal[advance + 7] = 0;
            normal[advance + 8] = 0;
            normal[advance + 9] = -1;
            normal[advance + 10] = 0;
            normal[advance + 11] = 0;

            advance += 12;
        }

        //RIGHT
        if (faceXP) {
            cube[advance + 0] = xP;
            cube[advance + 1] = yP;
            cube[advance + 2] = zP;
            cube[advance + 3] = xP;
            cube[advance + 4] = yP;
            cube[advance + 5] = zM;
            cube[advance + 6] = xP;
            cube[advance + 7] = yM;
            cube[advance + 8] = zM;
            cube[advance + 9] = xP;
            cube[advance + 10] = yM;
            cube[advance + 11] = zP;

            color[advance + 0] = colorX;
            color[advance + 1] = colorY;
            color[advance + 2] = colorZ;
            color[advance + 3] = colorX;
            color[advance + 4] = colorY;
            color[advance + 5] = colorZ;
            color[advance + 6] = colorX;
            color[advance + 7] = colorY;
            color[advance + 8] = colorZ;
            color[advance + 9] = colorX;
            color[advance + 10] = colorY;
            color[advance + 11] = colorZ;

            normal[advance + 0] = 1;
            normal[advance + 1] = 0;
            normal[advance + 2] = 0;
            normal[advance + 3] = 1;
            normal[advance + 4] = 0;
            normal[advance + 5] = 0;
            normal[advance + 6] = 1;
            normal[advance + 7] = 0;
            normal[advance + 8] = 0;
            normal[advance + 9] = 1;
            normal[advance + 10] = 0;
            normal[advance + 11] = 0;

            advance += 12;
        }

        indexSize += advance / 3;
    }

    private int activeBlocks() {
        int count = 0;

        for (int x = 0; x < CHUNK_SIZE; x++) {
            for (int y = 0; y < Y_CHUNK_SIZE; y++) {
                for (int z = 0; z < CHUNK_SIZE; z++) {
                    if (blocks[x][y][z] != null) {
                        if (blocks[x][y][z].isActive()) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    public void print() {
        System.out.println("Chunk (X: " + startX + " - Y: " + startY + " - Z: " + startZ + ")");
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStartZ() {
        return startZ;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    public void setEmptyChunk(boolean emptyChunk) {
        this.emptyChunk = emptyChunk;
    }

    public void setAllNeighbours(boolean allNeighbours) {
        this.allNeighbours = allNeighbours;
    }

    public boolean isGenerated() {
        return generated;
    }

    public boolean isEmptyChunk() {
        return emptyChunk;
    }

    public boolean isAllNeighbours() {
        return allNeighbours;
    }
}
