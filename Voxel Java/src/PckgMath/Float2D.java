package PckgMath;

/**
 *
 * @author Carlos
 */
public class Float2D {

    private float x, y;

    public Float2D() {
        this(0, 0);
    }

    public Float2D(float x, float y) {
        set(x, y);
    }

    public Float2D(Float2D tmp) {
        this(tmp.x, tmp.y);
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    
    public Float2D getFloat2D(){
        return this;
    }

    public void print() {
        System.out.println("X: "+getX()+" - Y:"+getY());
    }

    public void add(float x, float y) {
        set(getX()+x, getY()+y);
    }

    public void add(Float2D tmp) {
        set(x+tmp.x, y+tmp.y);
    }

    public void sub(float x, float y) {
        set(getX()-x, getY()-y);
    }

    public void sub(Float2D tmp) {
        set(x-tmp.x, y-tmp.y);
    }

    public void scale(float factor) {
        set(x*factor, y*factor);
    }
}
