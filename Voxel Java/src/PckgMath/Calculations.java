package PckgMath;

/**
 *
 * @author Carlos
 */
public class Calculations {

    public static int floor(float value) {
        int i = (int) value;
        return value >= (float) i ? i : i - 1;
    }

    public static int floor(double value) {
        int i = (int) value;
        return value >= (double) i ? i : i - 1;
    }

    public static int ceil(float value) {
        return floor(value) + 1;
    }

    public static int round(double value) {
        return floor(value + 0.5d);
    }

    public static int round(float value) {
        return floor(value + 0.5f);
    }

    public static int pow(int base, int potency) {
        if (potency == 0) {
            return 1;
        }
        return base * pow(base, potency - 1);
    }

    public static float simplifyRadians(float radians) {
        while (radians <= -Math.PI) {
            radians += Math.PI * 2;
        }
        while (radians > Math.PI) {
            radians -= Math.PI * 2;
        }
        return radians;
    }

    public static float simplifyDegrees(float degrees) {
        while (degrees <= -180.0f) {
            degrees += 360.0f;
        }
        while (degrees > 180.0f) {
            degrees -= 360.0f;
        }
        return degrees;
    }

    public static int floorDivision(int i, int divisor) {
        return floor(((float) i) / divisor);
    }

    public static float clamp(float value, float min, float max) {
        return value < min ? min : value > max ? max : value;
    }

    public static int clamp(int value, int min, int max) {
        return value < min ? min : value > max ? max : value;
    }
}
