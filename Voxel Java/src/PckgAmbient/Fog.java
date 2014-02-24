package PckgAmbient;

import PckgMath.Float3D;
import PckgUtil.Configuration;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.NVFogDistance;

/**
 *
 * @author Carlos
 */
public class Fog {

    private Float3D fogColor;
    
    public Fog() {
        fogColor = new Float3D(0.65f, 0.65f, 0.80f);
        
        createFog();
    }
    
    public void createFog() {
        ByteBuffer tmpBB = ByteBuffer.allocateDirect(16).order(ByteOrder.nativeOrder());
        float tmpColor[] = {getFogColor().getX(), getFogColor().getY(), getFogColor().getZ(), 1.0f};
        
        glFogi(GL_FOG_MODE, GL_LINEAR);
        glFog(GL_FOG_COLOR, (FloatBuffer)tmpBB.asFloatBuffer().put(tmpColor).flip());
        glHint(GL_FOG_HINT, GL_NICEST);
        glFogf(GL_FOG_START, Configuration.getInstance().getViewDistance());
        glFogf(GL_FOG_END, Configuration.getInstance().getFogDensity());
	glFogi(NVFogDistance.GL_FOG_DISTANCE_MODE_NV, NVFogDistance.GL_EYE_RADIAL_NV);
        
        enableFog();
    }
    
    public void enableFog() {
        glEnable(GL_FOG);
    }
    
    public void disableFog() {
        glDisable(GL_FOG);
    }

    public void setFogColor(Float3D fogColor) {
        this.fogColor = fogColor;
    }

    public Float3D getFogColor() {
        return fogColor;
    }
}
