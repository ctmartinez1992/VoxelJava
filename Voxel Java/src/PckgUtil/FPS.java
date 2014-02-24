package PckgUtil;

/**
 *
 * @author Carlos
 */
public class FPS {

    private final double nanoSecond = 1000000000.0 / 60.0;
    
    private static long lastTime;
    private static long nowTime;
    private long timer;
    
    private double delta = 0;
    
    private int frames = 0;
    private int updates = 0;
    
    private float step;

    public FPS() {}
    
    private static FPS _i;
    
    public static FPS getInstance() {
        if(_i==null)
            _i = new FPS();
        return _i;
    }

    public void init() {
        lastTime = System.nanoTime();
        timer = System.currentTimeMillis();

        delta = 0;
        step = 1;

        frames = updates = 0;
    }

    public void updateTime() {
        nowTime = System.nanoTime();
        step = (float) ((nowTime - lastTime) / nanoSecond);
        delta += step;
        lastTime = getNowTime();
    }

    public void incrementUpdates() {
        updates++;
    }

    public void incrementFrames() {
        frames++;
    }

    public void decrementDelta() {
        delta--;
    }

    public void checkReset() {
        if (System.currentTimeMillis() - getTimer() > 1000) {
            reset();
        }
    }

    private void reset() {
        timer += 1000;
        print();
        updates = frames = 0;
    }
    
    public void print() {
        System.out.println("UPS: "+getUpdates()+" | FPS: "+getFrames());
    }
    
    public static long getLastTime() {
        return lastTime;
    }

    public static long getNowTime() {
        return nowTime;
    }

    public long getTimer() {
        return timer;
    }
    
    public double getDelta() {
        return delta;
    }

    public int getFrames() {
        return frames;
    }

    public int getUpdates() {
        return updates;
    }

    public float getStep() {
        return step;
    }
}