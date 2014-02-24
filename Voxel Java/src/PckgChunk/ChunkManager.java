package PckgChunk;

import PckgGenerator.ChunkBuilder;
import PckgMath.Calculations;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ChunkManager {

    private List<Chunk> chunkRender;  
    private List<Chunk> chunkSetup; 
    private List<Chunk> chunkLoad; 
    private List<Chunk> chunkUnload; 
    private List<Chunk> chunkVisibility;

    public ChunkManager() {
        chunkRender = new ArrayList<>();
        chunkSetup = new ArrayList<>();
        chunkLoad = new ArrayList<>();
        chunkUnload = new ArrayList<>();
        chunkVisibility = new ArrayList<>();
    }
    
    private static ChunkManager _i;
    
    public static ChunkManager getInstance() {
        if(_i==null)
            _i = new ChunkManager();
        return _i;
    }
    
    public void addChunkRender(Chunk chunk) {
        chunkRender.add(chunk);
    }
    
    public void addChunkSetup(Chunk chunk) {
        chunkSetup.add(chunk);
    }
    
    public void addChunkLoad(Chunk chunk) {
        chunkLoad.add(chunk);
    }
    
    public void addChunkUnload(Chunk chunk) {
        chunkUnload.add(chunk);
    }
    
    public void addChunkVisibility(Chunk chunk) {
        chunkVisibility.add(chunk);
    }
    
    public void update() {
        updateSetup();
        updateRender();
    }
    
    public void render() {
        renderRender();
    }
    
    public void updateRender() {
        for (Chunk chunk : chunkRender) {
            chunk.update();
        }
    }
    
    public void updateSetup() {
        if (!chunkSetup.isEmpty()) {
            Chunk chunk = chunkSetup.get(0);
            
            chunk = ChunkBuilder.getInstance().generateChunk(chunk);
            chunk.buildMesh();
            
            chunkRender.add(chunk);
            chunkSetup.remove(chunk);
        }
    }
    
    public void renderRender() {
        for (Chunk chunk : chunkRender) {
            chunk.render();
        }
    } 
    
    public Chunk getChunkInRenderListWithPlayerPosition(int x, int z) {
        for (Chunk chunk : chunkRender) {
            if (((x >= chunk.getStartX()) && (x <= chunk.getStartX() + 15)) && ((z >= chunk.getStartZ()) && (z <= chunk.getStartZ() + 15))) {
                return chunk;
            }
        }
        
        return null;
    }
    
    public Chunk getChunkInRenderListWithPlayerPosition(float x, float z) {
        for (Chunk chunk : chunkRender) {
            if (((Calculations.floor(x) >= chunk.getStartX()) && (Calculations.floor(x) <= chunk.getStartX() + 15)) && ((Calculations.floor(z) >= chunk.getStartZ()) && (Calculations.floor(z) <= chunk.getStartZ() + 15))) {
                return chunk;
            }
        }
        
        return null;
    }
    
    public Chunk getChunkInRenderListWithWorldCoordinates(int x, int z) {
        for (Chunk chunk : chunkRender) {
            if ((chunk.getStartX() == x) && (chunk.getStartZ() == z)) {
                return chunk;
            }
        }
        
        return null;
    }
    
    public void removeBlock(int x, int y, int z) {
        for (Chunk chunk : chunkRender) {
            if (((Calculations.floor(x) >= chunk.getStartX()) && (Calculations.floor(x) <= chunk.getStartX() + 15)) && ((Calculations.floor(z) >= chunk.getStartZ()) && (Calculations.floor(z) <= chunk.getStartZ() + 15))) {
                while(x>15)
                    x -= 16;
                while(z>15)
                    z -= 16;
                
                if (chunk != null) {
                    if (chunk.blocks[x][y][z] != null) {
                        if (chunk.blocks[x][y][z].isActive()) {
                            chunk.blocks[x][y][z].setActive(false);                       
                            chunk.buildMesh();
                            
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void addBlock(int x, int y, int z) {
        for (Chunk chunk : chunkRender) {
            if (((Calculations.floor(x) >= chunk.getStartX()) && (Calculations.floor(x) <= chunk.getStartX() + 15)) && ((Calculations.floor(z) >= chunk.getStartZ()) && (Calculations.floor(z) <= chunk.getStartZ() + 15))) {
                while(x>15)
                    x -= 16;
                while(z>15)
                    z -= 16;
                
                if (chunk.blocks[x][y][z] != null) {
                    if (!chunk.blocks[x][y][z].isActive()) {
                        chunk.blocks[x][y][z].setActive(true);                       
                        chunk.buildMesh();
                        
                        return;
                    }
                }
            }
        }
    }
}