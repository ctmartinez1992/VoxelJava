package PckgAmbient;

import PckgMath.Float3D;
import PckgUtil.SmartRandom;
import java.util.Random;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Carlos
 */
public class Sky {
    
    private SmartRandom random;
    
    private Float3D color;
    private Float3D position;
    private Float3D size;
    
    private float colorAlpha;
    private float offset;
    
    public Sky(Float3D color, float colorAlpha) {
        random = new SmartRandom(new Random());
        
        this.color = color;
        this.colorAlpha = colorAlpha;
        
        this.position = new Float3D(0, 0, 0);
        this.size = new Float3D(1, 1, 1);
        this.offset = 1.005f;
    }
    
    public void update() {
//        color.set(random.randomFloat(0.39f, 0.41f), random.randomFloat(0.64f, 0.66f), random.randomFloat(0.89f, 0.91f));
//        colorAlpha = random.randomFloat(0.9f, 1.0f);
    }
    
    public void render() {
        glPushMatrix();
        glLoadIdentity();
            glClear(GL_DEPTH_BUFFER_BIT);    
            glDisable(GL_LIGHTING);
            glDisable(GL_BLEND);
            
            glColor4f(color.getX(), color.getY(), color.getZ(), colorAlpha); 

            glPushMatrix();
                glTranslatef(position.getX(),position.getY(),position.getZ());
                glScalef(size.getX(),size.getY(),size.getZ());

                //FRONT
                glBegin(GL_QUADS);
                    glVertex3f(-offset, 1.0f, -offset);
                    glVertex3f(-offset, 1.0f, offset);
                    glVertex3f(offset, 1.0f, offset); 
                    glVertex3f(offset, 1.0f, -offset);
                glEnd();

                //BACK
                glBegin(GL_QUADS);
                    glVertex3f(-offset, -1.0f, -offset);
                    glVertex3f(-offset, -1.0f, offset);
                    glVertex3f(offset, -1.0f, offset); 
                    glVertex3f(offset, -1.0f, -offset);
                glEnd();

                //LEFT
                glBegin(GL_QUADS);
                    glVertex3f(-1.0f, -offset, offset);	
                    glVertex3f(-1.0f, offset, offset); 
                    glVertex3f(-1.0f, offset, -offset);
                    glVertex3f(-1.0f, -offset, -offset);		
                glEnd();

                //RIGHT
                glBegin(GL_QUADS);
                    glVertex3f(1.0f, -offset, offset);	
                    glVertex3f(1.0f, offset, offset); 
                    glVertex3f(1.0f, offset, -offset);
                    glVertex3f(1.0f, -offset, -offset);
                glEnd();

                //TOP
                glBegin(GL_QUADS);	
                    glVertex3f(offset, -offset, 1.0f);
                    glVertex3f(offset, offset, 1.0f);
                    glVertex3f(-offset, offset, 1.0f);
                    glVertex3f(-offset, -offset, 1.0f);
                glEnd();

                //BOT
                glBegin(GL_QUADS);
                    glVertex3f(offset, -offset, -1.0f);
                    glVertex3f(offset, offset, -1.0f);
                    glVertex3f(-offset, offset, -1.0f);
                    glVertex3f(-offset, -offset, -1.0f);
                glEnd();
            glPopMatrix();
            
            glClear(GL_DEPTH_BUFFER_BIT);    
            glEnable(GL_LIGHTING);
            glEnable(GL_BLEND);
        glPopMatrix();
    };
}
