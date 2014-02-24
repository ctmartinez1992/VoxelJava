package PckgChunk.PckgSprite.PckgSpriteMesh;

import PckgBlock.Block;
import PckgBlock.BlockType;
import PckgChunk.PckgSprite.Sprite;
import PckgChunk.PckgSprite.SpriteConfiguration;
import PckgMath.Float3D;

/**
 *
 * @author Carlos
 */
public class Flower {
    
    public static Sprite generateSprite1(Sprite sprite) {
        SpriteConfiguration sC = sprite.getsC();
        sprite.setStart(sC.getPosition().getX() + sprite.PERFECT_HALF, sC.getPosition().getY(), sC.getPosition().getZ() + sprite.PERFECT_HALF);
        sprite.setCollision(false);
        
        for (int x = 7; x < 9; x++) {
            for (int y = 0; y < 4; y++) {
                for (int z = 7; z < 9; z++) {
                    sprite.blocks[x][y][z] = new Block(new Float3D(x, y, z), true, BlockType.Type.GREEN);
                }
            }
        }
        
        for (int x = 5; x < 11; x++) {
            for (int y = 4; y < 7; y++) {
                for (int z = 5; z < 11; z++) {
                    if ((x==7 || x==8) || (z==7 || z==8))
                        sprite.blocks[x][y][z] = new Block(new Float3D(x, y, z), true, BlockType.Type.WHITE);
                }
            }
        }
        
        for (int x = 7; x < 9; x++) {
            for (int y = 4; y < 7; y++) {
                for (int z = 7; z < 9; z++) {
                    sprite.blocks[x][y][z] = new Block(new Float3D(x, y, z), true, BlockType.Type.YELLOW);
                }
            }
        }
        
        return sprite;
    }
    
}
