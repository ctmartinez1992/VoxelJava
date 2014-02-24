package PckgEntity;

/**
 *
 * @author Carlos
 */
public class EntityStats {
    
    //Movement
    public float movementSpeedForward;
    public float movementSpeedBackward;
    public float movementSpeedSide;
    public float movementSpeedRun;
    public float movementSpeedWalk;
    public float movementSpeedCrouch;
    
    //Mouse
    public float rayCastLength;
    public float smashTime;
    public float buildTime;
    
    //Body
    public float eyeHeight;
    public float playerHeight;
    
    //Stat
    
    public EntityStats() {
        movementSpeedForward = 2.0f;
        movementSpeedBackward = 0.75f;
        movementSpeedSide = 1.0f;
        movementSpeedRun = 2.5f;
        movementSpeedWalk = 0.8f;
        movementSpeedCrouch = 0.9f;
        
        rayCastLength = 5.0f;
        smashTime = 0.4f;
        buildTime = 0.2f;
        
        eyeHeight = 1.2f;
        playerHeight = 1.4f;
    }
    
    public EntityStats(float[] movementSpeed, float[] mouse, float[] stature) {
        movementSpeedForward = movementSpeed[0];
        movementSpeedBackward = movementSpeed[1];
        movementSpeedSide = movementSpeed[2];
        movementSpeedRun = movementSpeed[3];
        movementSpeedWalk = movementSpeed[4];
        movementSpeedCrouch = movementSpeed[5];
        
        rayCastLength = mouse[0];
        smashTime = mouse[1];
        buildTime = mouse[2];
        
        eyeHeight = stature[0];
        playerHeight = stature[1];
    }
}
