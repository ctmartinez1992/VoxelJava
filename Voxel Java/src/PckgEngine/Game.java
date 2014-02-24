package PckgEngine;

import PckgInput.InputHandler;
import PckgUtil.Configuration;
import PckgUtil.FPS;
import PckgWorld.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.Cylinder;

/**
 *
 * @author Carlos
 */
public class Game {
    
    DisplayMode displayMode;
    private Configuration configuration = Configuration.getInstance();

    private World world;
    private FPS fps;
    
    public Game() {}

    public void Start() {
        configuration.print();
        
        createWindow();
        init();
        run();
    }

    private void createWindow() {
        try {
            DisplayMode availableDisplayModes[] = Display.getAvailableDisplayModes();
            for (int i = 0; i < availableDisplayModes.length; i++) {
                if (availableDisplayModes[i].getWidth() == configuration.getWidth() && availableDisplayModes[i].getHeight() == configuration.getHeight() && availableDisplayModes[i].getBitsPerPixel() == 32) {
                    displayMode = availableDisplayModes[i];
                    break;
                }
            }
            
            Display.setFullscreen(configuration.isFullscreen());
            Display.setDisplayMode(displayMode);
            Display.setLocation(50, 50);
            Display.setTitle("GAME");
            Display.create();
            
            Keyboard.create();
            Mouse.create();
            
            Mouse.setGrabbed(true);
        } catch (LWJGLException ex) {
            System.out.println("Exception "+ ex.toString() +" caught:\nClass: Game\nMethod: createWindow()");
            System.exit(1);
        }
    }

    private void init() {          
        initGL();
        
        world = World.getInstance();
        fps = FPS.getInstance();
        
        InputHandler.init();
    }

    private void initGL() {  
	glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearDepth(1.0);
        
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        
	glEnable(GL_COLOR_MATERIAL);
        glShadeModel(GL_SMOOTH);

        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
    }

    private void run() {
        fps.init();
        
        while (!Display.isCloseRequested()) {
            fps.updateTime();
            
            while (fps.getDelta() >= 1) {
                if (InputHandler.isActionHold(InputHandler.ESCAPE)) {
                    destroy();
                }
                
                world.update();
                
                fps.incrementUpdates();
                fps.decrementDelta();
            }
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
              
            glTranslatef(0.5f, 0, 0.5f);
            world.render();
            
            glPushMatrix();
                glTranslatef(world.getLighting().getPosition().getX(), world.getLighting().getPosition().getY(), world.getLighting().getPosition().getZ());
                Cylinder c = new Cylinder();
                c.draw(1, 1, 1, 20, 20);
            glPopMatrix();
            
            fps.incrementFrames();
            fps.checkReset();
                
            Display.update();
        }
        
        destroy();
    }
    
    private void destroy() {
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
        System.exit(0);
    }
}
