package PckgMath;

/**
 *
 * @author Carlos
 */
public class Circunference {

    private int nTriangles;
    private int cycleTriangles;

    public Circunference(int nTriangles, int cycleTriangles) {
        this.nTriangles = nTriangles;
        this.cycleTriangles = cycleTriangles;
    }

    private Float2D middlePoint(Float2D point1, Float2D point2) {
        return new Float2D((point2.getX() + point1.getX()) / 2, (point2.getY() + point1.getY()) / 2);
    }

    private double distanceBetweenPoints(Float2D point1, Float2D point2) {
        double dx = point2.getX() - point1.getX();
        double dy = point2.getY() - point1.getY();

        return Math.sqrt((dx * dx) + (dy * dy));
    }

    //Direction: true (up/left) - right (down/right)
    public Float2D circunference(Float2D point1, Float2D point2, boolean direction, boolean axisY) {
        double radius = distanceBetweenPoints(point1, point2) / 2;
        Float2D center = point2;

        if (cycleTriangles >= nTriangles) {
            cycleTriangles = 0;
        }

        double angle = (2 * Math.PI) / nTriangles;
        double dx = point2.getX() - center.getX();
        double dy = point2.getY() - center.getY();
        double initialAngle = Math.atan2(dy, dx);
        double currentAngle = angle * cycleTriangles + initialAngle;

        Float2D point = new Float2D();
        point.setX(((float) (center.getX() + radius * Math.cos(currentAngle))) * 2);
        point.setY(((float) (center.getY() + radius * Math.sin(currentAngle))) * 2);

            if (direction) {
                cycleTriangles++;
            } else {
                cycleTriangles--;
            }
            
        if (axisY) {
            if (cycleTriangles >= 400) {
                cycleTriangles--;
            }
            if (cycleTriangles <= 320) {
                cycleTriangles++;
            }
        }

        return point;
    }

    public int getNTriangles() {
        return nTriangles;
    }

    public int getCycleTriangles() {
        return cycleTriangles;
    }
}
