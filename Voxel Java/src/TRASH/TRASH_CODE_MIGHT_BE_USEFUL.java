package TRASH;

import PckgMath.Float3D;

/**
 *
 * @author Carlos
 */
public class TRASH_CODE_MIGHT_BE_USEFUL {
    
    /* NORMAL CALCULATION
     * 
     * 
                private float[] GetNormalVector(float[] cc) {

                    Float3D bottom = normal(new Float3D(cc[0], cc[1], cc[2]),
                                            new Float3D(cc[3], cc[4], cc[5]),
                                            new Float3D(cc[6], cc[7], cc[8]),
                                            new Float3D(cc[9], cc[10], cc[11]));

                    Float3D top = normal(   new Float3D(cc[12], cc[13], cc[14]),
                                            new Float3D(cc[15], cc[16], cc[17]),
                                            new Float3D(cc[18], cc[19], cc[20]),
                                            new Float3D(cc[21], cc[22], cc[23]));

                    Float3D front = normal( new Float3D(cc[24], cc[25], cc[26]),
                                            new Float3D(cc[27], cc[28], cc[29]),
                                            new Float3D(cc[30], cc[31], cc[32]),
                                            new Float3D(cc[33], cc[34], cc[35]));

                    Float3D back = normal(  new Float3D(cc[36], cc[37], cc[38]),
                                            new Float3D(cc[39], cc[40], cc[41]),
                                            new Float3D(cc[42], cc[43], cc[44]),
                                            new Float3D(cc[45], cc[46], cc[47]));

                    Float3D left = normal(  new Float3D(cc[48], cc[49], cc[50]),
                                            new Float3D(cc[51], cc[52], cc[53]),
                                            new Float3D(cc[54], cc[55], cc[56]),
                                            new Float3D(cc[57], cc[58], cc[59]));

                    Float3D right = normal( new Float3D(cc[60], cc[61], cc[62]),
                                            new Float3D(cc[63], cc[64], cc[65]),
                                            new Float3D(cc[66], cc[67], cc[68]),
                                            new Float3D(cc[69], cc[70], cc[71]));

                    return new float[]{
                                //BOTTOM
                                0, 1, 0,
                                0, 1, 0,
                                0, 1, 0,
                                0, 1, 0,
                                //TOP
                                0, -1, 0,
                                0, -1, 0,
                                0, -1, 0,
                                0, -1, 0,
                                //FRONT
                                0, 0, 1,
                                0, 0, 1,
                                0, 0, 1,
                                0, 0, 1,
                                //BOTTOM
                                0, 0, 1,
                                0, 0, 1,
                                0, 0, 1,
                                0, 0, 1,
                                //LEFT QUAD
                                1, 0, 0,
                                1, 0, 0,
                                1, 0, 0,
                                1, 0, 0,
                                //RIGHT QUAD
                                -1, 0, 0,
                                -1, 0, 0,
                                -1, 0, 0,
                                -1, 0, 0,};        

                    return new float[]{
                                //BOTTOM
                                bottom.getX(), bottom.getY(), bottom.getZ(),
                                bottom.getX(), bottom.getY(), bottom.getZ(),
                                bottom.getX(), bottom.getY(), bottom.getZ(),
                                bottom.getX(), bottom.getY(), bottom.getZ(),
                                //TOP
                                top.getX(), top.getY(), top.getZ(),
                                top.getX(), top.getY(), top.getZ(),
                                top.getX(), top.getY(), top.getZ(),
                                top.getX(), top.getY(), top.getZ(),
                                //FRONT
                                front.getX(), front.getY(), front.getZ(),
                                front.getX(), front.getY(), front.getZ(),
                                front.getX(), front.getY(), front.getZ(),
                                front.getX(), front.getY(), front.getZ(),
                                //BACK
                                back.getX(), back.getY(), back.getZ(),
                                back.getX(), back.getY(), back.getZ(),
                                back.getX(), back.getY(), back.getZ(),
                                back.getX(), back.getY(), back.getZ(),
                                //LEFT
                                left.getX(), left.getY(), left.getZ(),
                                left.getX(), left.getY(), left.getZ(),
                                left.getX(), left.getY(), left.getZ(),
                                left.getX(), left.getY(), left.getZ(),
                                //RIGHT
                                right.getX(), right.getY(), right.getZ(),
                                right.getX(), right.getY(), right.getZ(),
                                right.getX(), right.getY(), right.getZ(),
                                right.getX(), right.getY(), right.getZ()};
                }

                public Float3D normal(Float3D v0, Float3D v1, Float3D v2, Float3D v3) {
                    Float3D a = new Float3D(v0.getX() - v1.getX(), v0.getY() - v1.getY(), v0.getZ() - v1.getZ());
                    Float3D b = new Float3D(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
                    Float3D c = new Float3D(v2.getX() - v3.getX(), v2.getY() - v3.getY(), v2.getZ() - v3.getZ());

                    Float3D normal = new Float3D();

                    normal.setX((a.getY() * b.getZ()) - (a.getZ() * b.getY()));
                    normal.setY((a.getZ() * b.getX()) - (a.getX() * b.getZ()));
                    normal.setZ((a.getX() * b.getY()) - (a.getY() * b.getX()));

                    return normalize(normal);
                }

                public Float3D normalize (Float3D normal) {
                    float length = (float) (Math.sqrt((normal.getX() * normal.getX()) + (normal.getY() * normal.getY()) + (normal.getZ() * normal.getZ())));

                    if (length == 0.0f)
                        length = 1.0f;

                    normal.setX(normal.getX()/length);
                    normal.setY(normal.getY()/length);
                    normal.setZ(normal.getZ()/length);

                    return normal;
                }
    * 
    */
    
    

//public class Chunk {
//
//    public static final int CHUNK_SIZE = 16;
//    public static final int CUBE_LENGTH = 1;
//    public static final int Y_CHUNK_SIZE = CHUNK_SIZE * CHUNK_SIZE;
//    public static final int MAX_CHUNK_SIZE = CHUNK_SIZE * Y_CHUNK_SIZE * CHUNK_SIZE;
//    
//    public Block[][][] blocks;
//    
//    private int indexSize;
//    private int activeBlocks;
//    
//    private Chunk[] neighbours;                                                 //[0] Left | [1] Right | [2] Front | [3] Back
//    
//    private int VBOVertexHandle;
//    private int VBONormalHandle;
//    private int VBOColorHandle;
//    
//    private int startX;
//    private int startY;
//    private int startZ;
//    
//    private boolean emptyChunk = true;
//    private boolean generated = false;
//    private boolean allNeighbours = false;
//
//    public Chunk(int startX, int startY, int startZ, boolean visible) {
//        blocks = new Block[CHUNK_SIZE][Y_CHUNK_SIZE][CHUNK_SIZE];
//        
//        for (int x = 0; x < CHUNK_SIZE; x++) {
//            for (int y = 0; y < Y_CHUNK_SIZE; y++) {
//                for (int z = 0; z < CHUNK_SIZE; z++) {
//                    blocks[x][y][z] = new Block(new Float3D(x, y, z), visible, new BlockType(BlockType.Type.DIRT));
//                }
//            }
//        }       
//        
//        this.startX = startX;
//        this.startZ = startZ;
//        
//        neighbours = new Chunk[4];
//        
//        indexSize = 0;
//    }
//    
//    public void assignNeighbours() {
//        if (!allNeighbours) {
//            Chunk chunk;
//            
//            if (neighbours[0] == null) {
//                chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX - 16, startZ);
//                if (chunk != null) {
//                    neighbours[0] = chunk;
//                    buildMesh(startX, startY, startZ);
//                }
//            }
//            
//            if (neighbours[1] == null) {
//                chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX + 16, startZ);
//                if (chunk != null) {
//                    neighbours[1] = chunk;
//                    buildMesh(startX, startY, startZ);
//                }
//            }
//            
//            if (neighbours[2] == null) {
//                chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX, startZ - 16);
//                if (chunk != null) {
//                    neighbours[2] = chunk;
//                    buildMesh(startX, startY, startZ);
//                }
//            }
//            
//            if (neighbours[3] == null) {
//                chunk = ChunkManager.getInstance().getChunkInRenderListWithWorldCoordinates(startX, startZ + 16);
//                if (chunk != null) {
//                    neighbours[3] = chunk;
//                    buildMesh(startX, startY, startZ);
//                }
//            }
//            
//            if (neighbours[0] != null && neighbours[1] != null && neighbours[2] != null && neighbours[3] != null) {
//                setAllNeighbours(true);
//            }
//        }
//    }
//
//    public void update() {
//        assignNeighbours();
//    }
//
//    public void render() {  
//        if (isEmptyChunk() && isGenerated())
//            return;
//        
//        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
//        GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
//        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
//
//        GL11.glPushMatrix();
//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
//            GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0L);
//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBONormalHandle);
//            GL11.glNormalPointer(GL11.GL_FLOAT, 0, 0L);
//            GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
//            GL11.glColorPointer(3, GL11.GL_FLOAT, 0, 0L);
//            GL11.glDrawArrays(GL11.GL_QUADS, 0, (activeBlocks / 3));
//        GL11.glPopMatrix();
//
//        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
//        GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);
//        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
//    }
//
//    public void buildMesh(float startX, float startY, float startZ) {        
//        VBOColorHandle = GL15.glGenBuffers();
//        VBONormalHandle = GL15.glGenBuffers();
//        VBOVertexHandle = GL15.glGenBuffers();
//        
//        activeBlocks = activeBlocks() * 72;                                     //72 = 6 faces * 4 coordinates per face * 3 values per coordinate
//
//        FloatBuffer VertexPositionData = BufferUtils.createFloatBuffer(activeBlocks);
//        FloatBuffer VertexNormalData = BufferUtils.createFloatBuffer(activeBlocks);
//        FloatBuffer VertexColorData = BufferUtils.createFloatBuffer(activeBlocks);
//        
//        for (int x=0; x<CHUNK_SIZE; x++) {
//            for (int y=0; y<Y_CHUNK_SIZE; y++) {
//                for (int z=0; z<CHUNK_SIZE; z++) {
//                    if (blocks[x][y][z].isActive() == false) {
//                        continue;
//                    }
//                    
//                    setEmptyChunk(false);
//                    
//                    boolean xN = true;
//                    if(x > 0) {
//                        xN = !blocks[x-1][y][z].isActive();
//                    } else {
//                        if (neighbours[0] != null) {
//                            xN = !neighbours[0].blocks[15][y][z].isActive();
//                        }
//                    }
//                    
//                    boolean xP = true;
//                    if(x < CHUNK_SIZE - 1) {
//                        xP = !blocks[x+1][y][z].isActive();
//                    } else {
//                        if (neighbours[1] != null) {
//                            xP = !neighbours[1].blocks[15][y][z].isActive();
//                        }
//                    }
//                    
//                    boolean yN = true;
//                    if(y > 0) {
//                        yN = !blocks[x][y-1][z].isActive();
//                    }
//                    
//                    boolean yP = true;
//                    if(y < CHUNK_SIZE * CHUNK_SIZE - 1) {
//                        yP = !blocks[x][y+1][z].isActive();
//                    }
//                    
//                    boolean zN = true;
//                    if(z > 0) {
//                        zN = !blocks[x][y][z-1].isActive();
//                    } else {
//                        if (neighbours[2] != null) {
//                            zN = !neighbours[2].blocks[x][y][0].isActive();
//                        }
//                    }
//                    
//                    boolean zP = true;
//                    if(z < CHUNK_SIZE - 1) {
//                        zP = !blocks[x][y][z+1].isActive();
//                    } else {
//                        if (neighbours[3] != null) {
//                            zP = !neighbours[3].blocks[x][y][0].isActive();
//                        }
//                    }
//        
//                    if (xN && xP && yN && yP && zN && zP) {
//                        blocks[x][y][z].setActive(false);
//                        continue;
//                    }
//                    
//                    float[] cubeCoordinates = CreateCube((float) startX + x * CUBE_LENGTH, 
//                                                         (float) startY + y * CUBE_LENGTH, 
//                                                         (float) startZ + z * CUBE_LENGTH,
//                                                         xN, xP, yN, yP, zN, zP);
//                    VertexPositionData.put(cubeCoordinates);
//                    VertexNormalData.put(GetNormalVector());
//                    VertexColorData.put(CreateCubeVertexCol(GetCubeColor(blocks[x][y][z])));
//                }
//            }
//        }
//        
//        if (isEmptyChunk())
//            return;
//        
//        VertexColorData.flip();
//        VertexNormalData.flip();
//        VertexPositionData.flip();
//        
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOVertexHandle);
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexPositionData, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBONormalHandle);
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexNormalData, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBOColorHandle);
//        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, VertexColorData, GL15.GL_STATIC_DRAW);
//        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); 
//    }
//    
//    private int activeBlocks() {
//        int count = 0;
//        
//        for (int x=0; x<CHUNK_SIZE; x++) {
//            for (int y=0; y<Y_CHUNK_SIZE; y++) {
//                for (int z=0; z<CHUNK_SIZE; z++) {
//                    if (blocks[x][y][z].isActive()) {
//                        count++;
//                    }
//                }
//            }
//        }
//        
//        return count;
//    }
//
//    private float[] CreateCubeVertexCol(float[] CubeColorArray) {
//        float[] cubeColors = new float[CubeColorArray.length * 4 * 6];
//        
//        for (int i = 0; i < cubeColors.length; i++) {
//            cubeColors[i] = CubeColorArray[i % CubeColorArray.length];
//        }
//        
//        return cubeColors;
//    }
//
//    private float[] GetCubeColor(Block block) {
//        return new float[]{block.getColor().getX(), block.getColor().getY(), block.getColor().getZ()};
//    }
//
//    private float[] CreateCube(float x, float y, float z, boolean faceXN, boolean faceXP, boolean faceYN, boolean faceYP, boolean faceZN, boolean faceZP) {
//        float offset = (float) CUBE_LENGTH/2;
//        
//        float xP = x+offset;
//        float xM = x-offset;
//        float yP = y+offset;
//        float yM = y-offset;
//        float zP = z+offset;
//        float zM = z-offset;
//        
//        int cubeFloatSize = 0;
//        
//        if (faceXN) {
//            cubeFloatSize+=12;
//        }
//        if (faceXP) {
//            cubeFloatSize+=12;
//        }
//        if (faceYN) {
//            cubeFloatSize+=12;
//        }
//        if (faceYP) {
//            cubeFloatSize+=12;
//        }
//        if (faceZN) {
//            cubeFloatSize+=12;
//        }
//        if (faceZP) {
//            cubeFloatSize+=12;
//        }
//        
//        float cube[] = new float[cubeFloatSize];
//        
//        //BOTTOM
//        if (faceYP) {
//            cube[0] = xP; cube[1] = yP; cube[2] = zP;
//            cube[3] = xM; cube[4] = yP; cube[5] = zP;
//            cube[6] = xM; cube[7] = yP; cube[8] = zM;
//            cube[9] = xP; cube[10] = yP; cube[11] = zM;
//            
//            indexSize += 4;
//        } else {
//            //cube[0] = cube[1] = cube[2] = cube[3] = cube[4] = cube[5] = cube[6] = cube[7] = cube[8] = cube[9] = cube[10] = cube[11] = 0;
//        }
//        
//        //TOP
//        if (faceYN) {
//            cube[12] = xP; cube[13] = yM; cube[14] = zM;
//            cube[15] = xM; cube[16] = yM; cube[17] = zM;
//            cube[18] = xM; cube[19] = yM; cube[20] = zP;
//            cube[21] = xP; cube[22] = yM; cube[23] = zP;
//            
//            indexSize += 4;
//        } else {
//            //cube[12] = cube[13] = cube[14] = cube[15] = cube[16] = cube[17] = cube[18] = cube[19] = cube[20] = cube[21] = cube[22] = cube[23] = 0;
//        }
//        
//        //FRONT
//        if (faceZN) {
//            cube[24] = xP; cube[25] = yP; cube[26] = zM;
//            cube[27] = xM; cube[28] = yP; cube[29] = zM;
//            cube[30] = xM; cube[31] = yM; cube[32] = zM;
//            cube[33] = xP; cube[34] = yM; cube[35] = zM;
//            
//            indexSize += 4;
//        } else {
//            //cube[24] = cube[25] = cube[26] = cube[27] = cube[28] = cube[29] = cube[30] = cube[31] = cube[32] = cube[33] = cube[34] = cube[35] = 0;
//        }
//        
//        //BACK
//        if (faceZP) {
//            cube[36] = xP; cube[37] = yM; cube[38] = zP;
//            cube[39] = xM; cube[40] = yM; cube[41] = zP;
//            cube[42] = xM; cube[43] = yP; cube[44] = zP;
//            cube[45] = xP; cube[46] = yP; cube[47] = zP;
//            
//            indexSize += 4;
//        } else {
//            //cube[36] = cube[37] = cube[38] = cube[39] = cube[40] = cube[41] = cube[42] = cube[43] = cube[44] = cube[45] = cube[46] = cube[47] = 0;
//        }
//        
//        //LEFT
//        if (faceXN) {
//            cube[48] = xM; cube[49] = yP; cube[50] = zM;
//            cube[51] = xM; cube[52] = yP; cube[53] = zP;
//            cube[54] = xM; cube[55] = yM; cube[56] = zP;
//            cube[57] = xM; cube[58] = yM; cube[59] = zM;
//            
//            indexSize += 4;
//        } else {
//            //cube[48] = cube[49] = cube[50] = cube[51] = cube[52] = cube[53] = cube[54] = cube[55] = cube[56] = cube[57] = cube[58] = cube[59] = 0;
//        }
//        
//        //RIGHT
//        if (faceXP) {
//            cube[60] = xP; cube[61] = yP; cube[62] = zP;
//            cube[63] = xP; cube[64] = yP; cube[65] = zM;
//            cube[66] = xP; cube[67] = yM; cube[68] = zM;
//            cube[69] = xP; cube[70] = yM; cube[71] = zP;
//            
//            indexSize += 4;
//        } else {
//            //cube[60] = cube[61] = cube[62] = cube[63] = cube[64] = cube[65] = cube[66] = cube[67] = cube[68] = cube[69] = cube[70] = cube[71] = 0;
//        }
//        
//        return cube;
//    }
//
//    private float[] GetNormalVector() {
//        return new float[]{
//                    //BOTTOM
//                    0, 1, 0,
//                    0, 1, 0,
//                    0, 1, 0,
//                    0, 1, 0,
//                    //TOP
//                    0, -1, 0,
//                    0, -1, 0,
//                    0, -1, 0,
//                    0, -1, 0,
//                    //FRONT
//                    0, 0, 1,
//                    0, 0, 1,
//                    0, 0, 1,
//                    0, 0, 1,
//                    //BOTTOM
//                    0, 0, 1,
//                    0, 0, 1,
//                    0, 0, 1,
//                    0, 0, 1,
//                    //LEFT QUAD
//                    1, 0, 0,
//                    1, 0, 0,
//                    1, 0, 0,
//                    1, 0, 0,
//                    //RIGHT QUAD
//                    -1, 0, 0,
//                    -1, 0, 0,
//                    -1, 0, 0,
//                    -1, 0, 0,};        
//    }
//    
//    public void print() {
//        System.out.println("Chunk (X: " + startX + " - Y: " + startY + " - Z: " + startZ + ")");
//    }
//
//    public int getStartX() {
//        return startX;
//    }
//
//    public int getStartY() {
//        return startY;
//    }
//
//    public int getStartZ() {
//        return startZ;
//    }
//
//    public void setGenerated(boolean generated) {
//        this.generated = generated;
//    }
//    
//    public void setEmptyChunk(boolean emptyChunk) {
//        this.emptyChunk = emptyChunk;
//    }
//
//    public void setAllNeighbours(boolean allNeighbours) {
//        this.allNeighbours = allNeighbours;
//    }
//
//    public boolean isGenerated() {
//        return generated;
//    }
//
//    public boolean isEmptyChunk() {
//        return emptyChunk;
//    }
//
//    public boolean isAllNeighbours() {
//        return allNeighbours;
//    }
//}
    
    
    
    
    
    
    
    
    
    
    
    //    private float[] CreateCubeVertexCol(Block block, boolean faceXN, boolean faceXP, boolean faceYN, boolean faceYP, boolean faceZN, boolean faceZP) {
//        int cubeFloatSize = 0;
//        int advance = 0;
//        
//        float colorX = block.getColor().getX();
//        float colorY = block.getColor().getY();
//        float colorZ = block.getColor().getZ();
//        
//        if (faceXN) {
//            cubeFloatSize+=12;
//        }
//        if (faceXP) {
//            cubeFloatSize+=12;
//        }
//        if (faceYN) {
//            cubeFloatSize+=12;
//        }
//        if (faceYP) {
//            cubeFloatSize+=12;
//        }
//        if (faceZN) {
//            cubeFloatSize+=12;
//        }
//        if (faceZP) {
//            cubeFloatSize+=12;
//        }
//        
//        float cube[] = new float[cubeFloatSize];
//        
//        //BOTTOM
//        if (faceYP) {
//            cube[advance+0] = colorX; cube[advance+1] = colorY; cube[advance+2] = colorZ;
//            cube[advance+3] = colorX; cube[advance+4] = colorY; cube[advance+5] = colorZ;
//            cube[advance+6] = colorX; cube[advance+7] = colorY; cube[advance+8] = colorZ;
//            cube[advance+9] = colorX; cube[advance+10] = colorY; cube[advance+11] = colorZ;
//            
//            advance+=12;
//        } else {
//            //cube[0] = cube[1] = cube[2] = cube[3] = cube[4] = cube[5] = cube[6] = cube[7] = cube[8] = cube[9] = cube[10] = cube[11] = 0;
//        }
//        
//        //TOP
//        if (faceYN) {
//            cube[advance+0] = colorX; cube[advance+1] = colorY; cube[advance+2] = colorZ;
//            cube[advance+3] = colorX; cube[advance+4] = colorY; cube[advance+5] = colorZ;
//            cube[advance+6] = colorX; cube[advance+7] = colorY; cube[advance+8] = colorZ;
//            cube[advance+9] = colorX; cube[advance+10] = colorY; cube[advance+11] = colorZ;
//            
//            advance+=12;
//        } else {
//            //cube[12] = cube[13] = cube[14] = cube[15] = cube[16] = cube[17] = cube[18] = cube[19] = cube[20] = cube[21] = cube[22] = cube[23] = 0;
//        }
//        
//        //FRONT
//        if (faceZN) {
//            cube[advance+0] = colorX; cube[advance+1] = colorY; cube[advance+2] = colorZ;
//            cube[advance+3] = colorX; cube[advance+4] = colorY; cube[advance+5] = colorZ;
//            cube[advance+6] = colorX; cube[advance+7] = colorY; cube[advance+8] = colorZ;
//            cube[advance+9] = colorX; cube[advance+10] = colorY; cube[advance+11] = colorZ;
//            
//            advance+=12;
//        } else {
//            //cube[24] = cube[25] = cube[26] = cube[27] = cube[28] = cube[29] = cube[30] = cube[31] = cube[32] = cube[33] = cube[34] = cube[35] = 0;
//        }
//        
//        //BACK
//        if (faceZP) {
//            cube[advance+0] = colorX; cube[advance+1] = colorY; cube[advance+2] = colorZ;
//            cube[advance+3] = colorX; cube[advance+4] = colorY; cube[advance+5] = colorZ;
//            cube[advance+6] = colorX; cube[advance+7] = colorY; cube[advance+8] = colorZ;
//            cube[advance+9] = colorX; cube[advance+10] = colorY; cube[advance+11] = colorZ;
//            
//            advance+=12;
//        } else {
//            //cube[36] = cube[37] = cube[38] = cube[39] = cube[40] = cube[41] = cube[42] = cube[43] = cube[44] = cube[45] = cube[46] = cube[47] = 0;
//        }
//        
//        //LEFT
//        if (faceXN) {
//            cube[advance+0] = colorX; cube[advance+1] = colorY; cube[advance+2] = colorZ;
//            cube[advance+3] = colorX; cube[advance+4] = colorY; cube[advance+5] = colorZ;
//            cube[advance+6] = colorX; cube[advance+7] = colorY; cube[advance+8] = colorZ;
//            cube[advance+9] = colorX; cube[advance+10] = colorY; cube[advance+11] = colorZ;
//            
//            advance+=12;
//        } else {
//            //cube[48] = cube[49] = cube[50] = cube[51] = cube[52] = cube[53] = cube[54] = cube[55] = cube[56] = cube[57] = cube[58] = cube[59] = 0;
//        }
//        
//        //RIGHT
//        if (faceXP) {
//            cube[advance+0] = colorX; cube[advance+1] = colorY; cube[advance+2] = colorZ;
//            cube[advance+3] = colorX; cube[advance+4] = colorY; cube[advance+5] = colorZ;
//            cube[advance+6] = colorX; cube[advance+7] = colorY; cube[advance+8] = colorZ;
//            cube[advance+9] = colorX; cube[advance+10] = colorY; cube[advance+11] = colorZ;
//            
//            advance+=12;
//        } else {
//            //cube[60] = cube[61] = cube[62] = cube[63] = cube[64] = cube[65] = cube[66] = cube[67] = cube[68] = cube[69] = cube[70] = cube[71] = 0;
//        }
//        
//        return cube;
//    }
//
//    private float[] GetCubeColor(Block block) {
//        return new float[] {
//            block.getColor().getX(), 
//            block.getColor().getY(), 
//            block.getColor().getZ()
//        };
//    }

//    private float[] CreateCube(float x, float y, float z, boolean faceXN, boolean faceXP, boolean faceYN, boolean faceYP, boolean faceZN, boolean faceZP) {
//        float offset = (float) CUBE_LENGTH/2;
//        
//        float xP = x+offset;
//        float xM = x-offset;
//        float yP = y+offset;
//        float yM = y-offset;
//        float zP = z+offset;
//        float zM = z-offset;
//        
//        int cubeFloatSize = 0;
//        int advance = 0;
//        
//        if (faceXN) {
//            cubeFloatSize+=12;
//        }
//        if (faceXP) {
//            cubeFloatSize+=12;
//        }
//        if (faceYN) {
//            cubeFloatSize+=12;
//        }
//        if (faceYP) {
//            cubeFloatSize+=12;
//        }
//        if (faceZN) {
//            cubeFloatSize+=12;
//        }
//        if (faceZP) {
//            cubeFloatSize+=12;
//        }
//        
//        float cube[] = new float[cubeFloatSize];
//        
//        //BOTTOM
//        if (faceYP) {
//            cube[advance+0] = xP; cube[advance+1] = yP; cube[advance+2] = zP;
//            cube[advance+3] = xM; cube[advance+4] = yP; cube[advance+5] = zP;
//            cube[advance+6] = xM; cube[advance+7] = yP; cube[advance+8] = zM;
//            cube[advance+9] = xP; cube[advance+10] = yP; cube[advance+11] = zM;
//            
//            advance+=12;
//        } else {
//            //cube[0] = cube[1] = cube[2] = cube[3] = cube[4] = cube[5] = cube[6] = cube[7] = cube[8] = cube[9] = cube[10] = cube[11] = 0;
//        }
//        
//        //TOP
//        if (faceYN) {
//            cube[advance+0] = xP; cube[advance+1] = yM; cube[advance+2] = zM;
//            cube[advance+3] = xM; cube[advance+4] = yM; cube[advance+5] = zM;
//            cube[advance+6] = xM; cube[advance+7] = yM; cube[advance+8] = zP;
//            cube[advance+9] = xP; cube[advance+10] = yM; cube[advance+11] = zP;
//            
//            advance+=12;
//        } else {
//            //cube[12] = cube[13] = cube[14] = cube[15] = cube[16] = cube[17] = cube[18] = cube[19] = cube[20] = cube[21] = cube[22] = cube[23] = 0;
//        }
//        
//        //FRONT
//        if (faceZN) {
//            cube[advance+0] = xP; cube[advance+1] = yP; cube[advance+2] = zM;
//            cube[advance+3] = xM; cube[advance+4] = yP; cube[advance+5] = zM;
//            cube[advance+6] = xM; cube[advance+7] = yM; cube[advance+8] = zM;
//            cube[advance+9] = xP; cube[advance+10] = yM; cube[advance+11] = zM;
//            
//            advance+=12;
//        } else {
//            //cube[24] = cube[25] = cube[26] = cube[27] = cube[28] = cube[29] = cube[30] = cube[31] = cube[32] = cube[33] = cube[34] = cube[35] = 0;
//        }
//        
//        //BACK
//        if (faceZP) {
//            cube[advance+0] = xP; cube[advance+1] = yM; cube[advance+2] = zP;
//            cube[advance+3] = xM; cube[advance+4] = yM; cube[advance+5] = zP;
//            cube[advance+6] = xM; cube[advance+7] = yP; cube[advance+8] = zP;
//            cube[advance+9] = xP; cube[advance+10] = yP; cube[advance+11] = zP;
//            
//            advance+=12;
//        } else {
//            //cube[36] = cube[37] = cube[38] = cube[39] = cube[40] = cube[41] = cube[42] = cube[43] = cube[44] = cube[45] = cube[46] = cube[47] = 0;
//        }
//        
//        //LEFT
//        if (faceXN) {
//            cube[advance+0] = xM; cube[advance+1] = yP; cube[advance+2] = zM;
//            cube[advance+3] = xM; cube[advance+4] = yP; cube[advance+5] = zP;
//            cube[advance+6] = xM; cube[advance+7] = yM; cube[advance+8] = zP;
//            cube[advance+9] = xM; cube[advance+10] = yM; cube[advance+11] = zM;
//            
//            advance+=12;
//        } else {
//            //cube[48] = cube[49] = cube[50] = cube[51] = cube[52] = cube[53] = cube[54] = cube[55] = cube[56] = cube[57] = cube[58] = cube[59] = 0;
//        }
//        
//        //RIGHT
//        if (faceXP) {
//            cube[advance+0] = xP; cube[advance+1] = yP; cube[advance+2] = zP;
//            cube[advance+3] = xP; cube[advance+4] = yP; cube[advance+5] = zM;
//            cube[advance+6] = xP; cube[advance+7] = yM; cube[advance+8] = zM;
//            cube[advance+9] = xP; cube[advance+10] = yM; cube[advance+11] = zP;
//            
//            advance+=12;
//        } else {
//            //cube[60] = cube[61] = cube[62] = cube[63] = cube[64] = cube[65] = cube[66] = cube[67] = cube[68] = cube[69] = cube[70] = cube[71] = 0;
//        }
//        
//        return cube;
//    }

//    private float[] GetNormalVector(boolean faceXN, boolean faceXP, boolean faceYN, boolean faceYP, boolean faceZN, boolean faceZP) {       
//        int cubeFloatSize = 0;
//        int advance = 0;
//        
//        if (faceXN) {
//            cubeFloatSize+=12;
//        }
//        if (faceXP) {
//            cubeFloatSize+=12;
//        }
//        if (faceYN) {
//            cubeFloatSize+=12;
//        }
//        if (faceYP) {
//            cubeFloatSize+=12;
//        }
//        if (faceZN) {
//            cubeFloatSize+=12;
//        }
//        if (faceZP) {
//            cubeFloatSize+=12;
//        }
//        
//        float normal[] = new float[cubeFloatSize];
//        
//        //BOTTOM
//        if (faceYP) {
//            normal[advance+0] = 0; normal[advance+1] = 1; normal[advance+2] = 0;
//            normal[advance+3] = 0; normal[advance+4] = 1; normal[advance+5] = 0;
//            normal[advance+6] = 0; normal[advance+7] = 1; normal[advance+8] = 0;
//            normal[advance+9] = 0; normal[advance+10] = 1; normal[advance+11] = 0;
//            
//            advance+=12;
//        } else {
//            //normal[0] = normal[1] = normal[2] = normal[3] = normal[4] = normal[5] = normal[6] = normal[7] = normal[8] = normal[9] = normal[10] = normal[11] = 0;
//        }
//        
//        //TOP
//        if (faceYN) {
//            normal[advance+0] = 0; normal[advance+1] = -1; normal[advance+2] = 0;
//            normal[advance+3] = 0; normal[advance+4] = -1; normal[advance+5] = 0;
//            normal[advance+6] = 0; normal[advance+7] = -1; normal[advance+8] = 0;
//            normal[advance+9] = 0; normal[advance+10] = -1; normal[advance+11] = 0;
//            
//            advance+=12;
//        } else {
//            //normal[12] = normal[13] = normal[14] = normal[15] = normal[16] = normal[17] = normal[18] = normal[19] = normal[20] = normal[21] = normal[22] = normal[23] = 0;
//        }
//        
//        //FRONT
//        if (faceZN) {
//            normal[advance+0] = 0; normal[advance+1] = 0; normal[advance+2] = 1;
//            normal[advance+3] = 0; normal[advance+4] = 0; normal[advance+5] = 1;
//            normal[advance+6] = 0; normal[advance+7] = 0; normal[advance+8] = 1;
//            normal[advance+9] = 0; normal[advance+10] = 0; normal[advance+11] = 1;
//            
//            advance+=12;
//        } else {
//            //normal[24] = normal[25] = normal[26] = normal[27] = normal[28] = normal[29] = normal[30] = normal[31] = normal[32] = normal[33] = normal[34] = normal[35] = 0;
//        }
//        
//        //BACK
//        if (faceZP) {
//            normal[advance+0] = 0; normal[advance+1] = 0; normal[advance+2] = -1;
//            normal[advance+3] = 0; normal[advance+4] = 0; normal[advance+5] = -1;
//            normal[advance+6] = 0; normal[advance+7] = 0; normal[advance+8] = -1;
//            normal[advance+9] = 0; normal[advance+10] = 0; normal[advance+11] = -1;
//            
//            advance+=12;
//        } else {
//            //normal[36] = normal[37] = normal[38] = normal[39] = normal[40] = normal[41] = normal[42] = normal[43] = normal[44] = normal[45] = normal[46] = normal[47] = 0;
//        }
//        
//        //LEFT
//        if (faceXN) {
//            normal[advance+0] = 1; normal[advance+1] = 0; normal[advance+2] = 0;
//            normal[advance+3] = 1; normal[advance+4] = 0; normal[advance+5] = 0;
//            normal[advance+6] = 1; normal[advance+7] = 0; normal[advance+8] = 0;
//            normal[advance+9] = 1; normal[advance+10] = 0; normal[advance+11] = 0;
//            
//            advance+=12;
//        } else {
//            //normal[48] = normal[49] = normal[50] = normal[51] = normal[52] = normal[53] = normal[54] = normal[55] = normal[56] = normal[57] = normal[58] = normal[59] = 0;
//        }
//        
//        //RIGHT
//        if (faceXP) {
//            normal[advance+0] = -1; normal[advance+1] = 0; normal[advance+2] = 0;
//            normal[advance+3] = -1; normal[advance+4] = 0; normal[advance+5] = 0;
//            normal[advance+6] = -1; normal[advance+7] = 0; normal[advance+8] = 0;
//            normal[advance+9] = -1; normal[advance+10] = 0; normal[advance+11] = 0;
//            
//            advance+=12;
//        } else {
//            //normal[60] = normal[61] = normal[62] = normal[63] = normal[64] = normal[65] = normal[66] = normal[67] = normal[68] = normal[69] = normal[70] = normal[71] = 0;
//        }
//        
//        return normal;      
//    }
    
    
    
    
    //OLD PLAYUER
    //OLD PLAYUER
    //OLD PLAYUER
    //OLD PLAYUER
//package PckgPlayer;
//
//import PckgBlock.Block;
//import PckgChunk.Chunk;
//import PckgChunk.ChunkManager;
//import PckgInput.InputHandler;
//import PckgMath.Calculations;
//import PckgMath.Float3D;
//import PckgMath.PckgStructure.AABB;
//import PckgMath.PckgStructure.RayBlockIntersection;
//import PckgOverlay.Crosshair;
//import PckgUtil.Configuration;
//import java.util.List;
//import org.lwjgl.input.Keyboard;
//import org.lwjgl.input.Mouse;
//import static org.lwjgl.opengl.GL11.*;
//
///**
// *
// * @author Carlos
// */
//public class Player {
//
//    public int WALKING_SPEED = 5;
//    public int MOUSE_SENSITIVITY = 3;
//    public int EDIT_DISTANCE = 5;
//    public final float GRAVITY_FORCE = 9.8f;
//    
//    private int editDistance = EDIT_DISTANCE;
//    private int walkingSpeed = WALKING_SPEED;
//    public int mouseSpeed = MOUSE_SENSITIVITY;
//    
//    public float fallingVelocity = 0.15f;
//    public float fallingSpeed = 0.01f;
//    public final int maxLookUp = 85;
//    public final int maxLookDown = -85;
//    
//    public boolean isFalling = false;
//    
//    private Crosshair crosshair;
//    private Float3D aimedBlockPosition;
//    private Float3D adjacentAimedBlockPosition;
//    private AABB rayAABB;
//    private AABB intersectionTestingAABB;
//    private AABB aimedBlockAABB;
//    private Camera camera;
//    private ChunkManager chunkManager = ChunkManager.getInstance();
//
//    public Player(float cameraSpeed) {
//        crosshair = new Crosshair();
//        
//        aimedBlockPosition = new Float3D();
//        adjacentAimedBlockPosition = new Float3D();
//        aimedBlockAABB = new AABB(new Float3D(), new Float3D(0.5f, 0.5f, 0.5f));
//        rayAABB = new AABB(new Float3D(), new Float3D());
//        intersectionTestingAABB = new AABB(new Float3D(), new Float3D());
//        
//        camera = new Camera(cameraSpeed);
//    }
//
//    public void update() {
//        physics();
//        movement();
//        rayCastBlock();
//
//        if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
//            WALKING_SPEED = 1;
//            walkingSpeed = WALKING_SPEED;
//        }
//    }
//
//    public void render() {
//        aimedBlockAABB.render(0.0f, 0.0f, 0.0f, 0.1f);
//
//        glPushMatrix();
//        glClear(GL_DEPTH_BUFFER_BIT);
//        glMatrixMode(GL_PROJECTION);
//        glLoadIdentity();
//        glOrtho(0, Configuration.getInstance().getWidth(), 0, Configuration.getInstance().getHeight(), -1, 1);
//        glMatrixMode(GL_MODELVIEW);
//        glLoadIdentity();
//
//        crosshair.render();
//        glPopMatrix();
//
//        camera.look();
//    }
//
//    private void movement() {
//        if (Mouse.isGrabbed()) {
//            float mouseDX = Mouse.getDX() * mouseSpeed * 0.01f;
//            float mouseDY = Mouse.getDY() * mouseSpeed * 0.01f;
//
//            if (camera.getRotation().getY() + mouseDX >= 360) {
//                camera.getRotation().setY(camera.getRotation().getY() + mouseDX - 360);
//            } else if (camera.getRotation().getY() + mouseDX < 0) {
//                camera.getRotation().setY(360 - camera.getRotation().getY() + mouseDX);
//            } else {
//                camera.getRotation().setY(camera.getRotation().getY() + mouseDX);
//            }
//            if (camera.getRotation().getX() - mouseDY >= maxLookDown && camera.getRotation().getX() - mouseDY <= maxLookUp) {
//                camera.getRotation().setX(camera.getRotation().getX() + -mouseDY);
//            } else if (camera.getRotation().getX() - mouseDY < maxLookDown) {
//                camera.getRotation().setX(maxLookDown);
//            } else if (camera.getRotation().getX() - mouseDY > maxLookUp) {
//                camera.getRotation().setX(maxLookUp);
//            }
//        }
//
//        boolean forward = InputHandler.isActionHold(InputHandler.MOVE_FORWARD);
//        boolean back = InputHandler.isActionHold(InputHandler.MOVE_BACK);
//        boolean left = InputHandler.isActionHold(InputHandler.MOVE_LEFT);
//        boolean right = InputHandler.isActionHold(InputHandler.MOVE_RIGHT);
//        boolean movementUsed = false;
//
//        if (forward || right || left || back) {
//            movementUsed = true;
//        }
//
//        if (movementUsed) {
//            float[] angles = new float[2];
//
//            if (forward && !left && !right && !back) {      //Forward
//                angles = calculateMovementAngles(camera.getRotation().getY(), 1);
//            }
//            if (forward && left && !right && !back) {       //Forward and Left
//                angles = calculateMovementAngles(camera.getRotation().getY() - 45, 1);
//            }
//            if (forward && right && !left && !back) {       //Forward and Right
//                angles = calculateMovementAngles(camera.getRotation().getY() + 45, 1);
//            }
//            if (back && !forward && !left && !right) {      //Back
//                angles = calculateMovementAngles(camera.getRotation().getY(), -1);
//            }
//            if (back && left && !right && !forward) {       //Back and Left
//                angles = calculateMovementAngles(camera.getRotation().getY() - 135, 1);
//            }
//            if (back && right && !left && !forward) {       //Back and Right
//                angles = calculateMovementAngles(camera.getRotation().getY() + 135, 1);
//            }
//            if (left && !right && !forward && !back) {      //Left
//                angles = calculateMovementAngles(camera.getRotation().getY() - 90, 1);
//            }
//            if (right && !left && !forward && !back) {      //Right
//                angles = calculateMovementAngles(camera.getRotation().getY() + 90, 1);
//            }
//
//            camera.getPosition().setZ(camera.getPosition().getZ() + angles[0]);
//            camera.getPosition().setX(camera.getPosition().getX() - angles[1]);
////            
////            Chunk currentChunk = chunkManager.getChunkInRenderListWithPlayerPosition((int) (Math.abs(camera.getPosition().getX())), (int) (Math.abs(camera.getPosition().getZ())));
////
////            if (currentChunk != null) {
////                boolean wall = checkForWalls(currentChunk);
////                if (wall) {
////                    
////                    
////                    camera.getPosition().setX(camera.getPosition().getX() + angles[1]);
////                    camera.getPosition().setZ(camera.getPosition().getZ() - angles[0]);
////                }
////            }
//        }
//
//        if (InputHandler.isActionHold(InputHandler.JUMP)) {
//            camera.getPosition().setY(camera.getPosition().getY() - 0.1f);
//        }
//        if (InputHandler.isActionHold(InputHandler.DESCEND)) {
//            camera.getPosition().setY(camera.getPosition().getY() + 0.1f);
//        }
//
//        while (Mouse.next()) {
//            if (InputHandler.isActionHold(InputHandler.MOUSE_LEFT)) {
//                Mouse.setGrabbed(true);
//            }
//            if (InputHandler.isActionHold(InputHandler.MOUSE_RIGHT)) {
//                Mouse.setGrabbed(false);
//            }
//        }
//    }
//
////    private boolean checkForWalls(Chunk chunk) {   
////        int currentBlockX = ((int) Math.abs(camera.getPosition().getX() - 0.5f) - Math.abs(chunk.getStartX()));
////        int currentBlockZ = ((int) Math.abs(camera.getPosition().getZ() - 0.5f) - Math.abs(chunk.getStartZ()));
////
////        Chunk borderChunk = null;
////        if (currentBlockZ >= 15) {
////            borderChunk = chunkManager.getChunkInRenderListWithWorldCoordinates(chunk.getStartX(), chunk.getStartZ() + 16);
////            if (borderChunk != null)
////                currentBlockZ = 0; 
////            else
////                currentBlockZ = 15;
////        }
////        if (currentBlockX >= 15) {
////            borderChunk = chunkManager.getChunkInRenderListWithWorldCoordinates(chunk.getStartX() + 16, chunk.getStartZ());
////            if (borderChunk != null)
////                currentBlockX = 0;
////            else
////                currentBlockX = 15;
////        }
////        if (currentBlockZ == 0) {
////            borderChunk = chunkManager.getChunkInRenderListWithWorldCoordinates(chunk.getStartX(), chunk.getStartZ() - 16);
////            if (borderChunk != null)
////                currentBlockZ = 15;
////            else
////                currentBlockZ = 0;
////        }
////        if (currentBlockX == 0) {
////            borderChunk = chunkManager.getChunkInRenderListWithWorldCoordinates(chunk.getStartX() - 16, chunk.getStartZ());
////            if (borderChunk != null)
////                currentBlockZ = 15;
////            else
////                currentBlockZ = 0;
////        }
////        
////        if (borderChunk == null)
////            chunk = chunkManager.getChunkInRenderListWithWorldCoordinates(chunk.getStartX(), chunk.getStartZ());
////        
////        return chunk.blocks[currentBlockX][Math.abs((int) camera.getPosition().getY())][currentBlockZ].isActive();
////    }
//
//    /*
//     * Returns the angles for basic movement [adjacentAngle, oppositeAngle]
//     */
//    private float[] calculateMovementAngles(float angle, float factor) {
//        float playerVelocity = walkingSpeed * 0.05f;
//        float adjacentAngle = (factor * playerVelocity) * (float) Math.cos(Math.toRadians(angle));
//        float oppositeAngle = (factor * playerVelocity) * (float) Math.sin(Math.toRadians(angle));
//        return new float[]{adjacentAngle, oppositeAngle};
//    }
//
//    private void physics() {
//        //playerPhysics();
//    }
//
//    private void playerPhysics() {
//        if (isFalling) {
//            if (fallingVelocity < 3) {
//                fallingSpeed += 0.0005f;
//                fallingVelocity += (GRAVITY_FORCE * 0.1) * fallingSpeed;
//            }
//
//            camera.setPositionY(camera.getPosition().getY() + fallingVelocity);
//        }
//
//        Chunk currentChunk = chunkManager.getChunkInRenderListWithPlayerPosition((int) (camera.getPosition().getX() * -1), (int) (camera.getPosition().getZ() * -1));
//
//        if (currentChunk != null) {
//            getBlockBelowPlayer(currentChunk);
//        }
//    }
//
//    public void getBlockBelowPlayer(Chunk chunk) {
//        int currentBlockX = Math.abs(((int) Math.abs(camera.getPosition().getX()) - Math.abs(chunk.getStartX())));
//        int currentBlockY = Math.abs(((int) Math.abs(camera.getPosition().getY()) - Math.abs(chunk.getStartY())));
//        int currentBlockZ = Math.abs(((int) Math.abs(camera.getPosition().getZ()) - Math.abs(chunk.getStartZ())));
//
//        if (currentBlockY - 1 < 0) {
//            return;
//        }
//
//        if (chunk.blocks[currentBlockX][currentBlockY - 1][currentBlockZ].isActive()) {
//            isFalling = false;
//        } else {
//            if (!isFalling) {
//                fallingVelocity = 0.15f;
//                fallingSpeed = 0.01f;
//            }
//
//            isFalling = true;
//        }
//    }
//
//    private void rayCastBlock() {
//        float editDistanceSquared = editDistance * editDistance;
//
//        Float3D rayDirection = new Float3D();
//        Float3D rayOrigin = new Float3D();
//        
//        rayDirection.set(camera.getRotation().getX() * camera.getPosition().getX(), camera.getRotation().getY() * camera.getPosition().getY(), camera.getRotation().getZ() * camera.getPosition().getZ());
//        rayOrigin.set(camera.getPosition());
//
//        rayDirection.normalise();
//
//        rayAABB.getPosition().set(rayOrigin);
//        rayAABB.getPosition().addFactor(rayDirection, editDistance * 0.5f);
//        rayAABB.getDimensions().set(Math.abs(rayDirection.getX()), Math.abs(rayDirection.getY()), Math.abs(rayDirection.getZ()));
//        rayAABB.getDimensions().scale(editDistance * 0.5f);
//
//        int aabbX = (Calculations.round(rayAABB.getPosition().getX()) * -1);
//        int aabbY = (Calculations.round(rayAABB.getPosition().getY()) * -1);
//        int aabbZ = (Calculations.round(rayAABB.getPosition().getZ()) * -1);
//
//        RayBlockIntersection.Intersection closestIntersection = null;
//        Block closestBlock = new Block();
//        Float3D v = new Float3D();
//        Float3D newAimedBlockPosition = new Float3D();
//        Block block;
//        
//        Chunk chunk = chunkManager.getChunkInRenderListWithPlayerPosition(aabbX, aabbY, aabbZ);
//        if (chunk == null)
//            return;
//
//        for (int x = Calculations.floor(rayAABB.minX()); x <= Calculations.ceil(rayAABB.maxX()); x++) {
//            for (int y = Calculations.floor(rayAABB.minY()); y <= Calculations.ceil(rayAABB.maxY()); y++) {
//                for (int z = Calculations.floor(rayAABB.minZ()); z <= Calculations.ceil(rayAABB.maxZ()); z++) {                    
//                    int tmpX = Math.abs(x);
//                    int tmpY = Math.abs(y);
//                    int tmpZ = Math.abs(z);
//                    while(tmpX > 15)
//                        tmpX-=16;
//                    while(tmpY > 15)
//                        tmpY-=16;
//                    while(tmpZ > 15)
//                        tmpZ-=16;
//                    
//                    block = chunk.blocks[tmpX][tmpY][tmpZ];
//                    if (block == null)
//                        continue;
//
//                    v.set(tmpX + 0.5f, tmpY + 0.5f, tmpZ + 0.5f);
//                    v.sub(camera.getPosition());
//                    float lengthSquared = v.lengthSquared();
//                    
//                    if (lengthSquared < editDistanceSquared) {
//                        intersectionTestingAABB.getPosition().set(tmpX + 0.5f, tmpY + 0.5f, tmpZ + 0.5f);
//                        intersectionTestingAABB.getDimensions().set(0.5f, 0.5f, 0.5f);
//                        intersectionTestingAABB.recalcVertices();
//                        
//                        List<RayBlockIntersection.Intersection> intersections = RayBlockIntersection.executeIntersection(x, y, z, intersectionTestingAABB, rayOrigin, rayDirection);
//                        System.out.println(intersections.isEmpty());
//                        if (!intersections.isEmpty()) {
//                            if (closestIntersection == null || intersections.get(0).getDistance() < closestIntersection.getDistance()) {
//                                closestIntersection = intersections.get(0);
//                                closestBlock.setPosition(block.getPosition());
//                                newAimedBlockPosition.set(x, y, z);
//                                
//                                aimedBlockAABB.getPosition().set(aimedBlockPosition.getX(), aimedBlockPosition.getY(), aimedBlockPosition.getZ());
//                                aimedBlockAABB.getPosition().add(0.5f, 0.5f, 0.5f);
//                                aimedBlockAABB.getDimensions().set(0.5f, 0.5f, 0.5f);
//                            }
//                        }
//                    }
//                }
//            
//        }
//
//        if (closestIntersection != null) {
//            if (!aimedBlockPosition.equals(newAimedBlockPosition)) {
//                aimedBlockPosition.set(newAimedBlockPosition);
//                aimedBlockAABB.recalcVertices();
//            }
//            
//            adjacentAimedBlockPosition = closestIntersection.calcAdjacentBlockPos();
//        } else {
//            aimedBlockPosition.setY(-1);
//            adjacentAimedBlockPosition = null;
//        }
////        
////        if (closestIntersection != null && _selectedItem != null && _aimedBlockPosition.y() != -1) {
////            _body.setBlockDistance(closestIntersection.getDistance());
////        } else {
////            _body.setBlockDistance(0.0f);
////        }
//    }
//}

    
    
    
    //OLD CAMERA
    //OLD CAMERA
    //OLD CAMERA
    //OLD CAMERA
    //OLD CAMERA
    //OLD CAMERA
//    package PckgPlayer;
//
//import PckgMath.Float3D;
//import PckgUtil.Configuration;
//import static org.lwjgl.opengl.GL11.*;
//import org.lwjgl.util.glu.GLU;
//
///**
// *
// * @author Carlos
// */
//public class Camera {
//
//    Configuration configuration = Configuration.getInstance();
//    
//    private Float3D position;
//    private Float3D rotation;
//    
//    private float cameraSpeed;
//    private float scrollSpeed;
//
//    public Camera(float cameraSpeed) {
//        position = new Float3D(-2, -18, -2);
//        rotation = new Float3D(0, 0, 0);
//        
//        setCameraSpeed(cameraSpeed);
//        setScrollSpeed(cameraSpeed * 5);
//    }
//
//    public void look() {
//        glMatrixMode(GL_PROJECTION);
//        glLoadIdentity();
//            GLU.gluPerspective(configuration.getFov(), configuration.getAspectRatio(), 0.1f, 100.0f);
//            
//        glMatrixMode(GL_MODELVIEW);
//        glLoadIdentity();
//            glRotatef(rotation.getX(), 1, 0, 0);
//            glRotatef(rotation.getY(), 0, 1, 0);
//            glRotatef(rotation.getZ(), 0, 0, 1);
//            glTranslatef(position.getX(), position.getY(), position.getZ());
//    }
//    
//    public void setPosition(float x, float y, float z) {
//        position.set(x, y, z);
//    }
//    
//    public void setPositionX(float x) {
//        position.setX(x);
//    }
//    
//    public void setPositionY(float y) {
//        position.setY(y);
//    }
//    
//    public void setPositionZ(float z) {
//        position.setZ(z);
//    }
//    
//    public void setRotation(float x, float y, float z) {
//        rotation.set(x, y, z);
//    }
//    
//    public void setRotationX(float x) {
//        rotation.setX(x);
//    }
//    
//    public void setRotationY(float y) {
//        rotation.setY(y);
//    }
//    
//    public void setRotationZ(float z) {
//        rotation.setZ(z);
//    }
//    
//    public Float3D getPosition() {
//        return position;
//    }
//    
//    public Float3D getRotation() {
//        return rotation;
//    }
//    
//    public void setCameraSpeed(float cameraSpeed) {
//        this.cameraSpeed = cameraSpeed;
//    }
//    
//    public float getCameraSpeed() {
//        return this.cameraSpeed;
//    }
//    
//    public void setScrollSpeed(float scrollSpeed) {
//        this.scrollSpeed = scrollSpeed;
//    }
//    
//    public float getScrollSpeed() {
//        return this.scrollSpeed;
//    }
//    
//    public void print() {
//        System.out.print("Position: "); position.print();
//        System.out.print("Looking: "); rotation.print();
//    }
//}
}
