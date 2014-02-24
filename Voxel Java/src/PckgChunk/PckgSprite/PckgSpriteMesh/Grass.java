package PckgChunk.PckgSprite.PckgSpriteMesh;

import PckgBlock.Block;
import PckgBlock.BlockType;
import PckgChunk.PckgSprite.Sprite;
import PckgChunk.PckgSprite.SpriteConfiguration;
import PckgMath.Float3D;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Grass {
    
    public static Sprite generateSprite1(Sprite sprite) {
        SpriteConfiguration sC = sprite.getsC();
        sprite.setStart(sC.getPosition().getX() + sprite.PERFECT_HALF, sC.getPosition().getY(), sC.getPosition().getZ() + sprite.PERFECT_HALF);
        sprite.setCollision(true);
        
        Random random = new Random();
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 4; y++) {
                for (int z = 0; z < 8; z++) {
                    if (random.nextFloat() > 0.25f)
                        sprite.blocks[x][y][z] = new Block(new Float3D(x, y, z), true, BlockType.Type.GREEN);
                }
            }
        }
        
        return sprite;
    }
}
