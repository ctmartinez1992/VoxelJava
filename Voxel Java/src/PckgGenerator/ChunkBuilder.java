package PckgGenerator;

import PckgBlock.BlockType;
import PckgChunk.Chunk;
import PckgChunk.PckgSprite.*;
import PckgMath.Float2D;
import PckgMath.Float3D;
import PckgUtil.PerlinNoise;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class ChunkBuilder {
    
    private PerlinNoise perlin;
    
    private int[][] heightMap;
    
    private Random random;
    
    private int jshf=0;
    
    public ChunkBuilder() {
        perlin = new PerlinNoise(65465);  
        
        heightMap = new int[Chunk.CHUNK_SIZE][Chunk.CHUNK_SIZE];
        
        random = new Random();
    }
    
    private static ChunkBuilder _i;
    
    public static ChunkBuilder getInstance() {
        if(_i==null)
            _i = new ChunkBuilder();
        return _i;
    }
    
    public Chunk generateChunk(Chunk chunk) {
        for (int x = 0; x < Chunk.CHUNK_SIZE; x++) {
            for (int y = 0; y < Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE; y++) {
                for (int z = 0; z < Chunk.CHUNK_SIZE; z++) {
                    heightMap[x][z] = getHeightAt(x + chunk.getStartX(), z + chunk.getStartZ());
                    if (chunk != null) {
                        if (y < heightMap[x][z]) {
                            chunk.blocks[x][y][z].setActive(true);
                        } else {
                            chunk.blocks[x][y][z].setActive(false);
                        }
                    }
                    
//                    if (chunk != null) {
//                        if (y == 12) {
//                            chunk.blocks[x][y][z].setActive(true);
//                        } else {
//                            chunk.blocks[x][y][z].setActive(false);
//                        }
//                    }
                    
                    if (y == 0) {
                        if (random.nextFloat() > 0.99f) {
                            generateRandomSprites(chunk, x, heightMap[x][z]-2, z);
                        }
                    }
                }
            }
        }
        
        if (chunk.getStartX()==0 && chunk.getStartZ()==0) {
            chunk.blocks[2][13][2].setActive(true);
            chunk.blocks[4][14][2].setActive(true);
            chunk.blocks[5][13][5].setActive(true);
            chunk.blocks[5][14][5].setActive(true);
            chunk.blocks[5][15][5].setActive(true);
            chunk.blocks[5][15][6].setActive(true);
            chunk.blocks[5][15][7].setActive(true);
        }
        
        if (chunk != null)
            chunk.setGenerated(true);
        
        return chunk;
    }
    
    public void generateRandomSprites(Chunk chunk, int x, int y, int z) {
        SpriteManager.getInstance().addSpriteSetup(new Sprite(SpriteFactory.FLOWER_WHITE, new SpriteConfiguration(new Float3D(chunk.getStartX() - 1 + x, chunk.getStartY() + y + 1, chunk.getStartZ() - 1 + z), 
                                                                                                                 new Float2D(0, 0), 
                                                                                                                 new Float3D(0, 0, 0), 2, 0), 
                                                                                                                 16, 16, 16));
    }
    
    public int getHeightAt(int x, int z) {
        float height1 = 30.0f * perlin.noise(x * 0.01f, z * 0.01f);
	float height2 = 40.0f * perlin.noise(x * 0.04f, z * 0.04f) * perlin.noise(x * 0.002f, z * 0.002f);
	float height3 = 4.0f * perlin.noise(x * 0.08f, z * 0.08f);
        
	return (int) (Math.abs(20 + height1 + height2 + height3));
    }
}