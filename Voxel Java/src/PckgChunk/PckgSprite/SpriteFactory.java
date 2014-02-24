package PckgChunk.PckgSprite;

import PckgChunk.PckgSprite.PckgBody.*;
import PckgChunk.PckgSprite.PckgSpriteMesh.*;

/**
 *
 * @author Carlos
 */
public class SpriteFactory {
    
    //Environment
    public static final int GRASS_GREEN = 0x0001;
    public static final int FLOWER_WHITE = 0x0011;
    public static final int COLUMN_STONE = 0x0021;
    
    //Body
    public static final int BODY_TEST = 0x1001;
    
    public SpriteFactory() {
    }
    
    private static SpriteFactory _i;
    
    public static SpriteFactory getInstance() {
        if(_i==null)
            _i = new SpriteFactory();
        return _i;
    }
    
    public Sprite generateSprite(Sprite sprite) {
        switch(sprite.getSpriteType()) {
            //Environment
            case GRASS_GREEN:
                return Grass.generateSprite1(sprite);
                
            case FLOWER_WHITE:
                return Flower.generateSprite1(sprite);
                
            case COLUMN_STONE:
                return Column.generateSprite1(sprite);
               
            //Body
            case BODY_TEST:
                return Body.generateSprite1(sprite);
                
            default:
                return null;
        }
    }
}
