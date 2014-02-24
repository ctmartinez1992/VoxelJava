package PckgInput;

import java.util.HashMap;
import java.util.Map;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Carlos
 */
public class InputHandler {

    private static Map<Integer, Boolean> mapUseMouse = new HashMap<>();
    private static Map<Integer, Integer> mapKeys = new HashMap<>();
    
    //Mouse
    public static final int MOUSE_LEFT = 1;
    public static final int MOUSE_RIGHT = 2;
    public static final int MOUSE_WHEEL = 3;
    
    //Game Control
    public static final int ESCAPE = 51;
    
    //Movement
    public static final int MOVE_FORWARD = 11;
    public static final int MOVE_BACK = 12;
    public static final int MOVE_LEFT = 13;
    public static final int MOVE_RIGHT = 14;
    public static final int RUN = 15;
    public static final int WALK = 16;
    
    //Action
    public static final int JUMP = 21;
    public static final int CROUCH = 22;
    public static final int DESCEND = 23;
    
    //Debug
    public static final int FLY = 41;

    public static void init() {
        try {
            Keyboard.create();
            Mouse.create();
            
            initKeys();
        } catch (LWJGLException ex) {
            System.out.println("Exception " + ex.toString() + " caught:\nClass: InputHandler\nMethod: init()");
            System.exit(1);
        }
    }

    private static void initKeys() {
        //Mouse
        configureAction(MOUSE_LEFT, true, 0);
        configureAction(MOUSE_RIGHT, true, 1);
        configureAction(MOUSE_WHEEL, true, 2);
        
        //Game Control
        configureAction(ESCAPE, false, Keyboard.KEY_ESCAPE);
        
        //Movement
        configureAction(MOVE_FORWARD, false, Keyboard.KEY_W);
        configureAction(MOVE_BACK, false, Keyboard.KEY_S);
        configureAction(MOVE_LEFT, false, Keyboard.KEY_A);
        configureAction(MOVE_RIGHT, false, Keyboard.KEY_D);
        configureAction(RUN, false, Keyboard.KEY_LSHIFT);
        configureAction(WALK, false, Keyboard.KEY_LMENU);
        
        //Action
        configureAction(JUMP, false, Keyboard.KEY_SPACE);
        configureAction(CROUCH, false, Keyboard.KEY_LCONTROL);
        configureAction(DESCEND, false, Keyboard.KEY_X);
        
        //Debug
        configureAction(FLY, false, Keyboard.KEY_F);
    }

    public static void configureAction(int action, boolean mouse, int button) {
        mapUseMouse.put(action, mouse);
        mapKeys.put(action, button);
    }

    public static boolean isActionHold(int action) {
        boolean mouse = mapUseMouse.get(action);
        int button = mapKeys.get(action);
        if (mouse) {
            return Mouse.isButtonDown(button);
        } else {
            return Keyboard.isKeyDown(button);
        }
    }

    public static boolean isCurrentEvent(int action, boolean mouse) {
        return (mouse) ? isCurrentMouseEvent(action) : isCurrentKeyboardEvent(action);
    }

    public static boolean isCurrentKeyboardEvent(int action) {
        boolean keyboard = isKeyboard(action);
        if (!keyboard || !Keyboard.getEventKeyState())
            return false;
        
        return (Keyboard.getEventKey() == getButton(action));
    }

    public static boolean isCurrentMouseEvent(int action) {
        boolean mouse = isMouse(action);
        if (!mouse || !Mouse.getEventButtonState())
            return false;
        
        return (Mouse.getEventButton() == getButton(action));
    }

    public static boolean isKeyboard(int action) {
        return !mapUseMouse.get(action);
    }

    public static boolean isMouse(int action) {
        return mapUseMouse.get(action);
    }

    public static int getButton(int action) {
        return mapKeys.get(action);
    }
}