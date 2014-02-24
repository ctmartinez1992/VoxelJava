package PckgBlock;

import PckgMath.Float3D;
import PckgUtil.SmartRandom;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Block {
    
    private Float3D position;
    private Float3D color;
    private SmartRandom random;
    
    private BlockType.Type blockType;

    private boolean active;

    public Block() {
        this.blockType = null;
        
        random = null;
        this.position = new Float3D();
        this.active = false;
    }

    public Block(Float3D position, boolean active, BlockType.Type blockType) {
        this.blockType = blockType;
        
        random = new SmartRandom(new Random());
        this.position = position;
        this.active = active;
        
        createRandomColor();
    }
    
    private void createRandomColor() {
        switch(blockType) {
            case DIRT:                
                setColor(random.randomColors(BlockColorSet.getInstance().getDirtColorSet()));
                break;
                
            case GRAY:                
                setColor(random.randomColors(BlockColorSet.getInstance().getGrayColorSet()));
                break;
                
            case GREEN:                
                setColor(random.randomColors(BlockColorSet.getInstance().getGreenColorSet()));
                break;
                
            case WHITE:                
                setColor(random.randomColors(BlockColorSet.getInstance().getWhiteColorSet()));
                break;
                
            case YELLOW:                
                setColor(random.randomColors(BlockColorSet.getInstance().getYellowColorSet()));
                break;
                
            case RED:                
                setColor(random.randomColors(BlockColorSet.getInstance().getRedColorSet()));
                break;
                
            default:
                break;
        }
    }
    
    public void setPosition(Float3D position) {
        this.position = position;
    }
    
    public void setColor(Float3D color) {
        this.color = color;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setBlockType(BlockType.Type blockType) {
        this.blockType = blockType;
    }
    
    public Float3D getPosition() {
        return position;
    }
    
    public Float3D getColor() {
        return color;
    }

    public boolean isActive() {
        return active;
    }

    public BlockType.Type getBlockType() {
        return blockType;
    }
}
