package PckgUtil;

import PckgMath.Float3D;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public class SmartRandom {

    private Random random;

    public SmartRandom(Random random) {
        this.random = random;
    }

    public int randomInt(int lower, int upper) {
        return lower + random.nextInt(upper - lower);
    }

    public int randomInt(int upper) {
        return random.nextInt(upper);
    }

    public float randomFloat(float lower, float upper) {
        return lower + random.nextFloat() * (upper - lower);
    }

    public float randomFloat(float upper) {
        return random.nextFloat() * upper;
    }

    public long randomLong() {
        return random.nextLong();
    }

    public boolean randomBoolean() {
        return random.nextBoolean();
    }
    
    //do a probability system for random colors
    //do a probability system for random colors
    //do a probability system for random colors
    //do a probability system for random colors
    //do a probability system for random colors
    //do a probability system for random colors
    public Float3D randomColors(Float3D colorSet[]) {
        Float3D tmp;
        float chance = random.nextFloat();
        
        if (chance > 0.7f) {
            tmp = new Float3D(colorSet[0].getX() / 255.0f, colorSet[0].getY() / 255.0f, colorSet[0].getZ() / 255.0f);
        } else if (chance > 0.6f) {
            tmp = new Float3D(colorSet[1].getX() / 255.0f, colorSet[1].getY() / 255.0f, colorSet[1].getZ() / 255.0f);
        } else if (chance > 0.5f) {
            tmp = new Float3D(colorSet[2].getX() / 255.0f, colorSet[2].getY() / 255.0f, colorSet[2].getZ() / 255.0f);
        } else if (chance > 0.4f) {
            tmp = new Float3D(colorSet[3].getX() / 255.0f, colorSet[3].getY() / 255.0f, colorSet[3].getZ() / 255.0f);
        } else if (chance > 0.3f) {
            tmp = new Float3D(colorSet[4].getX() / 255.0f, colorSet[4].getY() / 255.0f, colorSet[4].getZ() / 255.0f);
        } else if (chance > 0.25f) {
            tmp = new Float3D(colorSet[5].getX() / 255.0f, colorSet[5].getY() / 255.0f, colorSet[5].getZ() / 255.0f);
        } else if (chance > 0.2f) {
            tmp = new Float3D(colorSet[6].getX() / 255.0f, colorSet[6].getY() / 255.0f, colorSet[6].getZ() / 255.0f);
        } else if (chance > 0.15f) {
            tmp = new Float3D(colorSet[7].getX() / 255.0f, colorSet[7].getY() / 255.0f, colorSet[7].getZ() / 255.0f);
        } else if (chance > 0.1f) {
            tmp = new Float3D(colorSet[8].getX() / 255.0f, colorSet[8].getY() / 255.0f, colorSet[8].getZ() / 255.0f);
        } else if (chance > 0.05f) {
            tmp = new Float3D(colorSet[9].getX() / 255.0f, colorSet[9].getY() / 255.0f, colorSet[9].getZ() / 255.0f);
        } else {
            tmp = new Float3D(colorSet[0].getX() / 255.0f, colorSet[0].getY() / 255.0f, colorSet[0].getZ() / 255.0f);
        }
        
        return tmp;
    }
}
