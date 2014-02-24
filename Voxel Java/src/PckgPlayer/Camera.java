package PckgPlayer;

import PckgMath.Float3D;
import PckgUtil.Configuration;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

public class Camera {

    private Float3D position;
    private Float3D lookDirection;
    private Float3D up;
    private Float3D right;
    private float bobbing;
    private float x, y, z;
    
    private float rotX, rotY;
    
    private float fov;

    public Camera() {
        position = new Float3D();
        lookDirection = new Float3D();
        up = new Float3D(0, 1, 0);
        right = new Float3D();
        fov = 60;
    }

    public void lookThrough(float viewingDistance) {
        glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            GLU.gluPerspective(fov, Configuration.getInstance().getAspectRatio(), 0.1f, viewingDistance);

        glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            GLU.gluLookAt(x,
                          y + Math.abs(bobbing) * 3.5f, 
                          z, 
                          x + lookDirection.getX(), 
                          y + lookDirection.getY() + Math.abs(bobbing) * 3.5f, 
                          z + lookDirection.getZ(), 
                          up.getX() + right.getX(), 
                          up.getY() + right.getY(), 
                          up.getZ() + right.getZ());
    }

    public void setPosition(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.position.set(x, y, z);
    }

    public void setRotation(float rotX, float rotY, float bobbing) {
        this.rotX = rotX;
        this.rotY = rotY;

        this.lookDirection.set((float) Math.cos(rotY), (float) Math.tan(rotX), (float) -Math.sin(rotY));
        this.right.cross(lookDirection, up);
        this.right.scale(bobbing * 0.6f);

        this.bobbing = bobbing;
    }

    public float getRotX() {
        return rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Float3D getPosition() {
        return position;
    }

    public Float3D getLookDirection() {
        return lookDirection;
    }
}
