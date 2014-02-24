package PckgBlock;

/**
 *
 * @author Carlos
 */
public class BlockType {
    
    public enum Type {
        DIRT,
        GRAY,
        GREEN,
        WHITE,
        YELLOW,
        RED
    }
    
    private Type type;
    
    
    public BlockType(Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return type;
    }
}