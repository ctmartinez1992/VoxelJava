package PckgWorld;

import PckgAmbient.Fog;
import PckgAmbient.Lighting;
import PckgAmbient.Sky;
import PckgChunk.Chunk;
import PckgChunk.ChunkManager;
import PckgChunk.PckgSprite.SpriteManager;
import PckgMath.Float3D;
import PckgPlayer.Player;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Carlos
 */
public class World {

    private ChunkManager chunkManager;
    private SpriteManager spriteManager;
    private Player player;
    private Sky sky;
    private Fog fog;
    private Lighting lighting;

    public World() {
        chunkManager = ChunkManager.getInstance();
        spriteManager = SpriteManager.getInstance();
        player = new Player(5, 20, 5);
        sky = new Sky(new Float3D(0.4f, 0.65f, 0.9f), 1.0f);
        fog = new Fog();
        lighting = new Lighting();

        createTestWorld();
    }
    
    private static World _i;
    
    public static World getInstance() {
        if(_i==null)
            _i = new World();
        return _i;
    }

    private void createTestWorld() {        
        for (int x = 0; x < 48; x+=16) {
            for (int z = 0; z < 48; z+=16) {
                chunkManager.addChunkSetup(new Chunk(x, 0, z, true));
            }
        }
    }

    public void update() {
        lighting.update();
        sky.update();
        
        chunkManager.update();
        spriteManager.update();
        
        player.update();
    }

    public void render() {
        sky.render();
        
        chunkManager.render();
        spriteManager.render();
        
        player.render();
    }
    
    public void setFog(Fog fog) {
        this.fog = fog;
    }

    public void setLighting(Lighting lighting) {
        this.lighting = lighting;
    }

    public Fog getFog() {
        return fog;
    }

    public Lighting getLighting() {
        return lighting;
    }
}