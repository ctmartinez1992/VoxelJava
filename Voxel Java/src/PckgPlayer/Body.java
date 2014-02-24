package PckgPlayer;

import PckgChunk.PckgSprite.Sprite;
import PckgChunk.PckgSprite.SpriteConfiguration;
import PckgChunk.PckgSprite.SpriteFactory;
import PckgChunk.PckgSprite.SpriteManager;
import PckgMath.Float2D;
import PckgMath.Float3D;

/**
 *
 * @author Carlos
 */
public class Body {
    
    public Sprite body;
    
    public Body() {
        body = new Sprite(SpriteFactory.BODY_TEST, new SpriteConfiguration(new Float3D(10, 12, 10), new Float2D(0, 0), new Float3D(0, 0, 0), 1, 0), 8, 32, 8);
        SpriteManager.getInstance().addSpriteSetup(body);
    }
    
    public void update() {
        
    }
    
    public void render() {
        body.render();
    }
}
