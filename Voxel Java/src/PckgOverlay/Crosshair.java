package PckgOverlay;

import PckgUtil.Configuration;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Carlos
 */
public class Crosshair {

    private float lineWidth;
    private int crossSize;
    private int crossHole;
    private int listCrossHandler;
    
    private int width, height;

    public Crosshair() {
        lineWidth = 2.5f;
        crossSize = 10;
        crossHole = 5;
        listCrossHandler = 0;
        
        width = Configuration.getInstance().getWidth();
        height = Configuration.getInstance().getHeight();
    }

    public void render() {
        if (listCrossHandler == 0) {
            listCrossHandler = glGenLists(1);
            glNewList(listCrossHandler, GL_COMPILE_AND_EXECUTE);

            glLineWidth(lineWidth);
            glColor4f(0.0f, 0.0f, 0.0f, 1.0f);
            
            glBegin(GL_LINES);
                glVertex3f(width / 2f - crossSize - crossHole, height / 2f, 0);
                glVertex3f(width / 2f - crossHole, height / 2f, 0);

                glVertex3f(width / 2f + crossSize + crossHole, height / 2f, 0);
                glVertex3f(width / 2f + crossHole, height / 2f, 0);

                glVertex3f(width / 2f, height / 2f - crossSize - crossHole, 0);
                glVertex3f(width / 2f, height / 2f - crossHole, 0);

                glVertex3f(width / 2f, height / 2f + crossSize + crossHole, 0);
                glVertex3f(width / 2f, height / 2f + crossHole, 0);
            glEnd();
            glEndList();
        } else {
            glCallList(listCrossHandler);
        }
    }
}
