package PckgChunk.PckgSprite.PckgBody;

import PckgBlock.Block;
import PckgBlock.BlockType;
import PckgChunk.PckgSprite.Sprite;
import PckgChunk.PckgSprite.SpriteConfiguration;
import PckgMath.Float3D;

/**
 *
 * @author Carlos
 */
public class Body {
    
    //TO DO
    //TO DO
    //TO DO
    //TO DO
    //TO DO
    public static Sprite generateSprite1(Sprite sprite) {
        SpriteConfiguration sC = sprite.getsC();
        sprite.setStart(sC.getPosition().getX() + sprite.PERFECT_HALF, sC.getPosition().getY(), sC.getPosition().getZ() + sprite.PERFECT_HALF);
        sprite.setCollision(true);
        
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 32; y++) {
                for (int z = 0; z < 8; z++) {
                    sprite.blocks[x][y][z] = new Block(new Float3D(x, y, z), true, BlockType.Type.GRAY);
                }
            }
        }
        
        return sprite;
    }
}
