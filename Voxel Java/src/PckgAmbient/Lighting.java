package PckgAmbient;

import PckgMath.Float3D;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Carlos
 */
public class Lighting {    
    
    private Float3D ambientLight;
    private Float3D diffuseLight;
    private Float3D specularLight;    
    
    private Float3D ambientMaterial;
    private Float3D diffuseMaterial;
    private Float3D specularMaterial;
    private Float3D shininessMaterial;
    
    private Float3D position;
    
    public Lighting() {
        ambientLight = new Float3D(0.1f, 0.1f, 0.1f);
        diffuseLight = new Float3D(0.7f, 0.7f, 0.7f);
        specularLight = new Float3D(1.0f, 1.0f, 1.0f);
        
        ambientMaterial = new Float3D(0.4f, 0.4f, 0.4f);
        diffuseMaterial = new Float3D(0.2f, 0.6f, 0.9f);
        specularMaterial = new Float3D(0.0f, 0.0f, 0.0f);
        shininessMaterial = new Float3D(0.0f, 0.0f, 0.0f);
        
        position = new Float3D(5.0f, 30.0f, 5.0f);
        
        createLight();
    }
    
    private void createLight() {
        ByteBuffer tmpBB = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder());
        float tmp[] = {ambientLight.getX(), ambientLight.getY(), ambientLight.getZ(), 1.0f};
            glLight(GL_LIGHT0, GL_AMBIENT, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());
         
        tmpBB.clear();
        tmp[0] = diffuseLight.getX(); tmp[1] = diffuseLight.getY(); tmp[2] = diffuseLight.getZ(); tmp[3] = 1.0f;
            glLight(GL_LIGHT0, GL_DIFFUSE, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());  
         
        tmpBB.clear();
        tmp[0] = specularLight.getX(); tmp[1] = specularLight.getY(); tmp[2] = specularLight.getZ(); tmp[3] = 1.0f;
            glLight(GL_LIGHT0, GL_SPECULAR, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());  
        
        tmpBB.clear();
        tmp[0] = position.getX(); tmp[1] = position.getY(); tmp[2] = position.getZ(); tmp[3] = 1.0f;
            glLight(GL_LIGHT0, GL_POSITION, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip()); 
        
        tmpBB.clear();
        tmp[0] = ambientMaterial.getX(); tmp[1] = ambientMaterial.getY(); tmp[2] = ambientMaterial.getZ(); tmp[3] = 1.0f;
            glMaterial(GL_FRONT_AND_BACK, GL_AMBIENT, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());
        
        tmpBB.clear();
        tmp[0] = diffuseMaterial.getX(); tmp[1] = diffuseMaterial.getY(); tmp[2] = diffuseMaterial.getZ(); tmp[3] = 1.0f;
            glMaterial(GL_FRONT_AND_BACK, GL_DIFFUSE, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());
        
        tmpBB.clear();
        tmp[0] = specularMaterial.getX(); tmp[1] = specularMaterial.getY(); tmp[2] = specularMaterial.getZ(); tmp[3] = 1.0f;
            glMaterial(GL_FRONT_AND_BACK, GL_SPECULAR, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());
        
        tmpBB.clear();
        tmp[0] = shininessMaterial.getX(); tmp[1] = shininessMaterial.getY(); tmp[2] = shininessMaterial.getZ(); tmp[3] = 1.0f;
            glMaterial(GL_FRONT_AND_BACK, GL_SHININESS, (FloatBuffer)tmpBB.asFloatBuffer().put(tmp).flip());
        
        glEnable(GL_LIGHTING);
        
        enableLight();
    }
    
    public void update() {
        if (Keyboard.isKeyDown(Keyboard.KEY_Y))
            position.setX(position.getX()+0.1f);
        if (Keyboard.isKeyDown(Keyboard.KEY_H))
            position.setX(position.getX()-0.1f);
        if (Keyboard.isKeyDown(Keyboard.KEY_U))
            position.setY(position.getY()+0.1f);
        if (Keyboard.isKeyDown(Keyboard.KEY_J))
            position.setY(position.getY()-0.1f);
        if (Keyboard.isKeyDown(Keyboard.KEY_I))
            position.setZ(position.getZ()+0.1f);
        if (Keyboard.isKeyDown(Keyboard.KEY_K))
            position.setZ(position.getZ()-0.1f);
        
        createLight();
    }
    
    public void enableLight() {
        glEnable(GL_LIGHT0); 
    }
    
    public void disableLight() {
        glDisable(GL_LIGHT0); 
    }

    public void setAmbientLight(Float3D ambientLight) {
        this.ambientLight = ambientLight;
    }

    public void setDiffuseLight(Float3D diffuseLight) {
        this.diffuseLight = diffuseLight;
    }

    public void setSpecularLight(Float3D specularLight) {
        this.specularLight = specularLight;
    }
    
    public void setPosition(Float3D position) {
        this.position = position;
    }

    public Float3D getAmbientLight() {
        return ambientLight;
    }

    public Float3D getDiffuseLight() {
        return diffuseLight;
    }

    public Float3D getSpecularLight() {
        return specularLight;
    }

    public Float3D getPosition() {
        return position;
    }
}
