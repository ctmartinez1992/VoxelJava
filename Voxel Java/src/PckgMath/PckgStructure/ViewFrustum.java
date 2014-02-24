package PckgMath.PckgStructure;

import PckgMath.Float3D;
import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Carlos
 */
public class ViewFrustum {

    private static final Float3D zero = new Float3D();
    
    private final FrustumPlane[] planes = new FrustumPlane[6];
    
    private final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
    private final FloatBuffer modelling = BufferUtils.createFloatBuffer(16);
    private final FloatBuffer clipping = BufferUtils.createFloatBuffer(16);

    public ViewFrustum() {
        for (int i = 0; i < 6; i++) {
            planes[i] = new FrustumPlane();
        }
    }

    public void updateFrustum() {
        GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, projection);
        GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, modelling);

        clipping.put(0, modelling.get(0) * projection.get(0) + modelling.get(1) * projection.get(4) + modelling.get(2) * projection.get(8) + modelling.get(3) * projection.get(12));
        clipping.put(1, modelling.get(0) * projection.get(1) + modelling.get(1) * projection.get(5) + modelling.get(2) * projection.get(9) + modelling.get(3) * projection.get(13));
        clipping.put(2, modelling.get(0) * projection.get(2) + modelling.get(1) * projection.get(6) + modelling.get(2) * projection.get(10) + modelling.get(3) * projection.get(14));
        clipping.put(3, modelling.get(0) * projection.get(3) + modelling.get(1) * projection.get(7) + modelling.get(2) * projection.get(11) + modelling.get(3) * projection.get(15));

        clipping.put(4, modelling.get(4) * projection.get(0) + modelling.get(5) * projection.get(4) + modelling.get(6) * projection.get(8) + modelling.get(7) * projection.get(12));
        clipping.put(5, modelling.get(4) * projection.get(1) + modelling.get(5) * projection.get(5) + modelling.get(6) * projection.get(9) + modelling.get(7) * projection.get(13));
        clipping.put(6, modelling.get(4) * projection.get(2) + modelling.get(5) * projection.get(6) + modelling.get(6) * projection.get(10) + modelling.get(7) * projection.get(14));
        clipping.put(7, modelling.get(4) * projection.get(3) + modelling.get(5) * projection.get(7) + modelling.get(6) * projection.get(11) + modelling.get(7) * projection.get(15));

        clipping.put(8, modelling.get(8) * projection.get(0) + modelling.get(9) * projection.get(4) + modelling.get(10) * projection.get(8) + modelling.get(11) * projection.get(12));
        clipping.put(9, modelling.get(8) * projection.get(1) + modelling.get(9) * projection.get(5) + modelling.get(10) * projection.get(9) + modelling.get(11) * projection.get(13));
        clipping.put(10, modelling.get(8) * projection.get(2) + modelling.get(9) * projection.get(6) + modelling.get(10) * projection.get(10) + modelling.get(11) * projection.get(14));
        clipping.put(11, modelling.get(8) * projection.get(3) + modelling.get(9) * projection.get(7) + modelling.get(10) * projection.get(11) + modelling.get(11) * projection.get(15));

        clipping.put(12, modelling.get(12) * projection.get(0) + modelling.get(13) * projection.get(4) + modelling.get(14) * projection.get(8) + modelling.get(15) * projection.get(12));
        clipping.put(13, modelling.get(12) * projection.get(1) + modelling.get(13) * projection.get(5) + modelling.get(14) * projection.get(9) + modelling.get(15) * projection.get(13));
        clipping.put(14, modelling.get(12) * projection.get(2) + modelling.get(13) * projection.get(6) + modelling.get(14) * projection.get(10) + modelling.get(15) * projection.get(14));
        clipping.put(15, modelling.get(12) * projection.get(3) + modelling.get(13) * projection.get(7) + modelling.get(14) * projection.get(11) + modelling.get(15) * projection.get(15));

        //Right
        planes[0].setA(clipping.get(3) - clipping.get(0));
        planes[0].setB(clipping.get(7) - clipping.get(4));
        planes[0].setC(clipping.get(11) - clipping.get(8));
        planes[0].setD(clipping.get(15) - clipping.get(12));
        planes[0].normalize();

        //Left
        planes[1].setA(clipping.get(3) + clipping.get(0));
        planes[1].setB(clipping.get(7) + clipping.get(4));
        planes[1].setC(clipping.get(11) + clipping.get(8));
        planes[1].setD(clipping.get(15) + clipping.get(12));
        planes[1].normalize();

        //Bot
        planes[2].setA(clipping.get(3) + clipping.get(1));
        planes[2].setB(clipping.get(7) + clipping.get(5));
        planes[2].setC(clipping.get(11) + clipping.get(9));
        planes[2].setD(clipping.get(15) + clipping.get(13));
        planes[2].normalize();

        //Top
        planes[3].setA(clipping.get(3) - clipping.get(1));
        planes[3].setB(clipping.get(7) - clipping.get(5));
        planes[3].setC(clipping.get(11) - clipping.get(9));
        planes[3].setD(clipping.get(15) - clipping.get(13));
        planes[3].normalize();

        //Far
        planes[4].setA(clipping.get(3) - clipping.get(2));
        planes[4].setB(clipping.get(7) - clipping.get(6));
        planes[4].setC(clipping.get(11) - clipping.get(10));
        planes[4].setD(clipping.get(15) - clipping.get(14));
        planes[4].normalize();

        //Near
        planes[5].setA(clipping.get(3) + clipping.get(2));
        planes[5].setB(clipping.get(7) + clipping.get(6));
        planes[5].setC(clipping.get(11) + clipping.get(10));
        planes[5].setD(clipping.get(15) + clipping.get(14));
        planes[5].normalize();
    }

    public boolean intersects(double x, double y, double z) {
        for (int i = 0; i < 6; i++) {
            if (planes[i].getA() * x + planes[i].getB() * y + planes[i].getC() * z + planes[i].getD() <= 0) {
                return false;
            }
        }
        return true;
    }

//    public boolean intersects(AABB aabb) {
//
//        if (aabb == null) {
//            return false;
//        }
//
//        Vec3f[] aabbVertices = aabb.getVertices();
//        Vec3f rp = zero; // Reference Point for rendereing
//
//        for (int i = 0; i < 6; i++) {
//            if (planes[i].getA() * (aabbVertices[0].x() - rp.x()) + planes[i].getB() * (aabbVertices[0].y() - rp.y()) + planes[i].getC() * (aabbVertices[0].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[1].x() - rp.x()) + planes[i].getB() * (aabbVertices[1].y() - rp.y()) + planes[i].getC() * (aabbVertices[1].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[2].x() - rp.x()) + planes[i].getB() * (aabbVertices[2].y() - rp.y()) + planes[i].getC() * (aabbVertices[2].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[3].x() - rp.x()) + planes[i].getB() * (aabbVertices[3].y() - rp.y()) + planes[i].getC() * (aabbVertices[3].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[4].x() - rp.x()) + planes[i].getB() * (aabbVertices[4].y() - rp.y()) + planes[i].getC() * (aabbVertices[4].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[5].x() - rp.x()) + planes[i].getB() * (aabbVertices[5].y() - rp.y()) + planes[i].getC() * (aabbVertices[5].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[6].x() - rp.x()) + planes[i].getB() * (aabbVertices[6].y() - rp.y()) + planes[i].getC() * (aabbVertices[6].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            if (planes[i].getA() * (aabbVertices[7].x() - rp.x()) + planes[i].getB() * (aabbVertices[7].y() - rp.y()) + planes[i].getC() * (aabbVertices[7].z() - rp.z()) + planes[i].getD() > 0) {
//                continue;
//            }
//            return false;
//        }
//        return true;
//    }
}
