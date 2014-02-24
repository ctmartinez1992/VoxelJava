package PckgChunk.PckgSprite;

import PckgChunk.Chunk;
import PckgChunk.ChunkManager;
import PckgMath.Calculations;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class SpriteManager {    
    
    private List<Sprite> spriteRender;  
    private List<Sprite> spriteSetup;
    
    public SpriteManager() {
        spriteRender = new ArrayList<>();
        spriteSetup = new ArrayList<>();
    }
    
    private static SpriteManager _i;
    
    public static SpriteManager getInstance() {
        if(_i==null)
            _i = new SpriteManager();
        return _i;
    }
    
    public void addSpriteRender(Sprite sprite) {
        spriteRender.add(sprite);
    }
    
    public void addSpriteSetup(Sprite sprite) {
        spriteSetup.add(sprite);
    }
    
    public void update() {
        updateSetup();
        updateRender();
    }
    
    public void render() {
        renderRender();
    }
    
    public void updateRender() {
        for (Sprite sprite : spriteRender) {
            sprite.update();
        }
    }
    
    public void updateSetup() {
        if (!spriteSetup.isEmpty()) {
            Sprite sprite = spriteSetup.get(0);
            
            sprite = SpriteFactory.getInstance().generateSprite(sprite);
            sprite.buildMesh();
        
            Chunk chunk = ChunkManager.getInstance().getChunkInRenderListWithPlayerPosition(sprite.getStartX() + 1, sprite.getStartZ() + 1);
            chunk.sprites.add(sprite);
            
            spriteRender.add(sprite);
            spriteSetup.remove(sprite);
        }
    }
    
    public void renderRender() {
        for (Sprite sprite : spriteRender) {
            sprite.render();
        }
    }
    
    public Sprite getSpriteInRenderListWithPlayerPosition(int x, int z) {
        for (Sprite sprite : spriteRender) {
            if (((x >= sprite.getStartX()) && (x <= sprite.getStartX() + 15)) && ((z >= sprite.getStartZ()) && (z <= sprite.getStartZ() + 15))) {
                return sprite;
            }
        }
        
        return null;
    }
    
    public Sprite getSpriteInRenderListWithPlayerPosition(float x, float y, float z) {
        for (Sprite sprite : spriteRender) {
            if (((Calculations.floor(x) >= sprite.getStartX()) && (Calculations.floor(x) <= sprite.getStartX() + 15)) 
             && ((Calculations.floor(z) >= sprite.getStartZ()) && (Calculations.floor(z) <= sprite.getStartZ() + 15))
             && ((Calculations.floor(z) >= sprite.getStartZ()) && (Calculations.floor(z) <= sprite.getStartZ() + 15))) {
                return sprite;
            }
        }
        
        return null;
    }
    
    public Sprite getSpriteInRenderListWithWorldCoordinates(int x, int z) {
        for (Sprite sprite : spriteRender) {
            if ((sprite.getStartX() == x) && (sprite.getStartZ() == z)) {
                return sprite;
            }
        }
        
        return null;
    }
    
    public void removeBlock(int x, int y, int z) {
        for (Sprite sprite : spriteRender) {
            if (((Calculations.floor(x) >= sprite.getStartX()) && (Calculations.floor(x) <= sprite.getStartX() + 15)) && ((Calculations.floor(z) >= sprite.getStartZ()) && (Calculations.floor(z) <= sprite.getStartZ() + 15))) {
                while(x>sprite.SPRITE_SIZE_X)
                    x -= sprite.SPRITE_SIZE_X;
                while(y>sprite.SPRITE_SIZE_Y)
                    y -= sprite.SPRITE_SIZE_Y;
                while(z>sprite.SPRITE_SIZE_Z)
                    z -= sprite.SPRITE_SIZE_Z;
                
                if (x < 0 || x >= sprite.SPRITE_SIZE_X || y < 0 || y >= sprite.SPRITE_SIZE_Y || z < 0 || z >= sprite.SPRITE_SIZE_Z) {
                    return;
                }
                
                if (sprite != null) {
                    if (sprite.blocks[x][y][z] != null) {
                        if (sprite.blocks[x][y][z].isActive()) {
                            sprite.blocks[x][y][z].setActive(false);                       
                            sprite.buildMesh();
                            
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void addBlock(int x, int y, int z) {
        for (Sprite sprite : spriteRender) {
            if (((Calculations.floor(x) >= sprite.getStartX()) && (Calculations.floor(x) <= sprite.getStartX() + 15)) && ((Calculations.floor(z) >= sprite.getStartZ()) && (Calculations.floor(z) <= sprite.getStartZ() + 15))) {
                while(x>sprite.SPRITE_SIZE_X)
                    x -= sprite.SPRITE_SIZE_X;
                while(y>sprite.SPRITE_SIZE_Y)
                    y -= sprite.SPRITE_SIZE_Y;
                while(z>sprite.SPRITE_SIZE_Z)
                    z -= sprite.SPRITE_SIZE_Z;
                
                if (x < 0 || x >= sprite.SPRITE_SIZE_X || y < 0 || y >= sprite.SPRITE_SIZE_Y || z < 0 || z >= sprite.SPRITE_SIZE_Z) {
                    return;
                }
                
                if (sprite.blocks[x][y][z] != null) {
                    if (!sprite.blocks[x][y][z].isActive()) {
                        sprite.blocks[x][y][z].setActive(true);                       
                        sprite.buildMesh();
                        
                        return;
                    }
                }
            }
        }
    }
}
