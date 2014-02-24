package PckgUtil;

import PckgMath.Calculations;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Carlos
 */
public final class PerlinNoise {
    
    private int perlin[] = new int[512];

    public PerlinNoise(long seed) {
        Random rand = new Random(seed);
        perlin = new int[512];
        
        for (int i = 0; i < 256; ++i)
            perlin[i] = i;
        
        shuffleArray(perlin, 0, 256, new SmartRandom(rand));
        
        for (int i = 0; i < 256; ++i)
            perlin[256 + i] = perlin[i];

        System.out.println("Perlin Permutations = " + Arrays.toString(perlin));
    }

    public float noise(float x, float y) {
        int floorX = Calculations.floor(x);
        int floorY = Calculations.floor(y);

        int X = floorX & 255;
        int Y = floorY & 255;
        int Z = 0;
        
        x -= floorX;
        y -= floorY;
        
        float u = fade(x);
        float v = fade(y);
        
        int A = perlin[X] + Y;
        int B = perlin[X + 1] + Y;
        
        int AA = perlin[A] + Z;
        int AB = perlin[A + 1] + Z;
        
        int BA = perlin[B] + Z;
        int BB = perlin[B + 1] + Z;

        return lerp(0, 
                    lerp(v, 
                         lerp(u, 
                              grad(perlin[AA], x, y, 0), 
                              grad(perlin[BA], x - 1, y, 0)), 
                         lerp(u, 
                              grad(perlin[AB], x, y - 1, 0), 
                              grad(perlin[BB], x - 1, y - 1, 0))),
                    lerp(v, 
                         lerp(u, 
                              grad(perlin[AA + 1], x, y, -1), 
                              grad(perlin[BA + 1], x - 1, y, -1)), 
                         lerp(u, 
                              grad(perlin[AB + 1], x, y - 1, -1),
                              grad(perlin[BB + 1], x - 1, y - 1, -1))));

    }

//    public float noise(float x, float y, float z) {
//        int floorX = Calculations.floor(x);
//        int floorY = Calculations.floor(y);
//        int floorZ = Calculations.floor(z);
//
//        int X = floorX & 255;
//        int Y = floorY & 255;
//        int Z = floorZ & 255;
//        
//        x -= floorX;
//        y -= floorY;
//        z -= floorZ;
//        
//        float u = fade(x);
//        float v = fade(y);
//        float w = fade(z);
//        
//        int A = perlin[X] + Y;
//        int B = perlin[X + 1] + Y;
//        
//        int AA = perlin[A] + Z;
//        int AB = perlin[A + 1] + Z;
//        
//        int BA = perlin[B] + Z;
//        int BB = perlin[B + 1] + Z;
//    
//        return lerp(w, 
//                    lerp(v, 
//                         lerp(u, 
//                              grad(perlin[AA], x, y, z), 
//                              grad(perlin[BA], x - 1, y, z)), 
//                         lerp(u, 
//                              grad(perlin[AB], x, y - 1, z), 
//                              grad(perlin[BB], x - 1, y - 1, z))),
//                    lerp(v, 
//                         lerp(u, 
//                              grad(perlin[AA + 1], x, y, z - 1), 
//                              grad(perlin[BA + 1], x - 1, y, z - 1)), 
//                         lerp(u, 
//                              grad(perlin[AB + 1], x, y - 1, z - 1),
//                              grad(perlin[BB + 1], x - 1, y - 1, z - 1))));
//    }

    private static float fade(float t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private static float lerp(float t, float a, float b) {
        return a + t * (b - a);
    }

    private static float grad(int hash, float x, float y, float z) {
        int h = hash & 15;
        float u = h < 8 ? x : y, v = h < 4 ? y : h == 12 || h == 14 ? x : z;
        return ((h & 1) == 0 ? u : -u) + ((h & 2) == 0 ? v : -v);
    }

    private static void shuffleArray(int[] array, int lower, int upper, SmartRandom random) {
        for (int i = lower; i < upper; ++i) {
            int randomIndex = random.randomInt(lower, upper);
            int temp = array[i];
            array[i] = array[randomIndex];
            array[randomIndex] = temp;
        }
    }
}