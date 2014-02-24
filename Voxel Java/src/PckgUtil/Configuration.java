package PckgUtil;

import java.io.*;

/**
 *
 * @author Carlos
 */
public class Configuration {

    private int width;
    private int height;
    private int viewDistance;
    private int fogDensity;
    
    private float aspectRatio;
    private float fov;
    private float mouseSensitivity;
    
    private boolean fullscreen;

    public Configuration() {
        loadFromFile();
        
        fogDensity += viewDistance;
        aspectRatio = (float)(width/height);
    }
    
    private static Configuration _i;
    
    public static Configuration getInstance() {
        if(_i==null)
            _i = new Configuration();
        return _i;
    }

    private void loadFromFile() {
        File file = new File("C:/Users/Carlos/Desktop/UnfinishedGames/Minecraft/Configuration/Configuration.txt");

        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line;
            while ((line = bReader.readLine()) != null) {
                String[] split = line.split("=");
                String configuration = split[0];
                String content = split[1];

                switch (configuration) {
                    case "width":
                        setWidth(Integer.parseInt(content));
                        break;
                    case "height":
                        setHeight(Integer.parseInt(content));
                        break;
                    case "view_distance":
                        setViewDistance(Integer.parseInt(content));
                        break;
                    case "fog_density":
                        setFogDensity(Integer.parseInt(content));
                        break;
                    case "fov":
                        setFov(Float.parseFloat(content));
                        break;
                    case "fullscreen":
                        setFullscreen(Boolean.parseBoolean(content));
                        break;
                    case "mouse_sensitivity":
                        setMouseSensitivity(Float.parseFloat(content));
                        break;
                }
            }
        } catch(IOException | NumberFormatException ex) {
            System.out.println("Exception "+ ex.toString() +" caught:\nClass: Configuration\nMethod: loadFromFile()");
            System.exit(1);
        }
    }
    
    public void print() {
        System.out.println("\n---Actual Configuration---\n\nResolution: "+width+"x"+height+""
                + "\nFOV: "+fov+"\nFullscreen: "+fullscreen+"\n");
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public void setFogDensity(int fogDensity) {
        this.fogDensity = fogDensity;
    }
    
    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }
    
    public void setFov(float fov) {
        this.fov = fov;
    }
    
    public void setFullscreen(boolean fullscreen) {
        this.fullscreen = fullscreen;
    }

    public void setMouseSensitivity(float mouseSensitivity) {
        this.mouseSensitivity = mouseSensitivity;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public int getFogDensity() {
        return fogDensity;
    }

    public float getAspectRatio() {
        return aspectRatio;
    }

    public float getFov() {
        return fov;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public float getMouseSensitivity() {
        return mouseSensitivity;
    }
}
