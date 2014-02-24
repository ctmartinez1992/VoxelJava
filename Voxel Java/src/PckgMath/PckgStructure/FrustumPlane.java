package PckgMath.PckgStructure;

/**
 *
 * @author Carlos
 */
public class FrustumPlane {

    private double a, b, c, d;
    
    public FrustumPlane() {
        
    }

    public FrustumPlane(double a, double b, double c, double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public void normalize() {
        double t = Math.sqrt(a * a + b * b + c * c);
        a /= t;
        b /= t;
        c /= t;
        d /= t;
    }

    public void setA(double a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public void setC(double c) {
        this.c = c;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }
}
