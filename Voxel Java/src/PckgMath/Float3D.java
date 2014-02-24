package PckgMath;

/**
 *
 * @author Carlos
 */
public class Float3D {

    private float x, y, z;

    public Float3D() {
        this(0, 0, 0);
    }

    public Float3D(float x, float y, float z) {
        set(x, y, z);
    }

    public Float3D(Float3D tmp) {
        this(tmp.x, tmp.y, tmp.z);
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void set(Float3D tmp) {
        this.x=tmp.x;
        this.y=tmp.y;
        this.z=tmp.z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Float3D getFloat3D() {
        return this;
    }

    public void print() {
        System.out.println("X: " + getX() + " - Y:" + getY() + " - Z:" + getZ());
    }

    public void add(float x, float y, float z) {
        set(getX() + x, getY() + y, getZ() + z);
    }

    public void add(Float3D tmp) {
        set(x + tmp.x, y + tmp.y, z + tmp.z);
    }

    public void sub(float x, float y, float z) {
        set(getX() - x, getY() - y, getZ() - z);
    }

    public void sub(Float3D tmp) {
        set(x - tmp.x, y - tmp.y, z - tmp.z);
    }

    public void scale(float factor) {
        set(x * factor, y * factor, z * factor);
    }	
    
    public void normalise() {
        float length = length();
        scale(1.0f / length);
    }

    public float length() {
        return (float) Math.sqrt(lengthSquared());
    }

    public float lengthSquared() {
        return (x * x) + (y * y) + (z * z);
    }	
    
    public void addFactor(Float3D tmp, float factor) {
        set(x + tmp.getX() * factor, y + tmp.getY() * factor, z + tmp.getZ() * factor);
    }	
    
    public void cross(Float3D a, Float3D b) {
        set(a.y * b.z - a.z * b.y, a.z * b.x - a.x * b.z, a.x * b.y - a.y * b.x);
    }

    public static float dot(Float3D a, Float3D b) {
        return a.x * b.x + a.y * b.y + a.z * b.z;
    }
}