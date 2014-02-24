package PckgMath.PckgStructure;

import PckgMath.Float3D;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Carlos
 */
public class AABB {

    private Float3D position;
    private Float3D dimensions;
    private Float3D[] vertices;
    
    public boolean visible;

    public AABB(Float3D position, Float3D dimensions) {
        this.position = position;
        this.dimensions = dimensions;
        
        visible = true;
    }

    public boolean overlaps(AABB otherAABB) {
        if (maxX() < otherAABB.minX() || minX() > otherAABB.maxX()) {
            return false;
        }
        if (maxY() < otherAABB.minY() || minY() > otherAABB.maxY()) {
            return false;
        }
        if (maxZ() < otherAABB.minZ() || minZ() > otherAABB.maxZ()) {
            return false;
        }
        return true;
    }

    public Float3D closestPointInsideAABBToPoint(Float3D point) {
        Float3D closest = new Float3D(point);

        if (point.getX() < minX()) {
            closest.setX(minX());
        }
        if (point.getX() > maxX()) {
            closest.setX(maxX());
        }
        if (point.getY() < minY()) {
            closest.setY(minY());
        }
        if (point.getY() > maxY()) {
            closest.setY(maxY());
        }
        if (point.getZ() < minZ()) {
            closest.setZ(minZ());
        }
        if (point.getZ() > maxZ()) {
            closest.setZ(maxZ());
        }

        return closest;
    }

    public Float3D closestPointOnAABBToPoint(Float3D point, boolean useX, boolean useY, boolean useZ) {
        if (!contains(point)) {
            return closestPointInsideAABBToPoint(point);
        } else {
            Float3D closest = new Float3D(point);
            
            float shortestExit = 1000000.0f;
            int side = -1;

            if (useX && minX() < point.getX() && point.getX() < maxX()) {
                float dist = Math.min(point.getX() - minX(), maxX() - point.getX());
                if (dist < shortestExit) {
                    side = 0;
                    shortestExit = dist;
                }
            }
            if (useY && minY() < point.getY() && point.getY() < maxY()) {
                float dist = Math.min(point.getY() - minY(), maxY() - point.getY());
                if (dist < shortestExit) {
                    side = 1;
                    shortestExit = dist;
                }
            }
            if (useZ && minZ() < point.getZ() && point.getZ() < maxZ()) {
                float dist = Math.min(point.getZ() - minZ(), maxZ() - point.getZ());
                if (dist < shortestExit) {
                    side = 2;
                }
            }

            if (side == 0)                                                      //X
            {
                if (point.getX() > position.getX()) {
                    closest.setX(maxX());
                } else {
                    closest.setX(minX());
                }
            } else if (side == 1)                                               //Y
            {
                if (point.getY() > position.getY()) {
                    closest.setY(maxY());
                } else {
                    closest.setY(minY());
                }
            } else if (side == 2)                                               //Z
            {
                if (point.getZ() > position.getZ()) {
                    closest.setZ(maxZ());
                } else {
                    closest.setZ(minZ());
                }
            }
            return closest;
        }

    }

    public synchronized Float3D[] getVertices() {
        if (vertices == null) {
            vertices = new Float3D[8];

            for (int i = 0; i < 8; ++i) {
                vertices[i] = new Float3D();
            }

            recalcVertices();
        }

        return vertices;
    }

    public void render() {
        render(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public void render(float r, float g, float b, float a) {
        if (visible) {
            float offset = 0.001f;

            glPushMatrix();
                glLineWidth(5.0f);
                glTranslatef(getPosition().getX(), getPosition().getY(), getPosition().getZ());
                glColor4f(r, g, b, a);

                //Front
                glBegin(GL_LINE_LOOP);
                    glVertex3f(-dimensions.getX() - offset, -dimensions.getY() - offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, -dimensions.getY() - offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, +dimensions.getY() + offset, -dimensions.getZ() - offset);
                    glVertex3f(-dimensions.getX() - offset, +dimensions.getY() + offset, -dimensions.getZ() - offset);
                glEnd();

                //Back
                glBegin(GL_LINE_LOOP);
                    glVertex3f(-dimensions.getX() - offset, -dimensions.getY() - offset, +dimensions.getZ() + offset);
                    glVertex3f(+dimensions.getX() + offset, -dimensions.getY() - offset, +dimensions.getZ() + offset);
                    glVertex3f(+dimensions.getX() + offset, +dimensions.getY() + offset, +dimensions.getZ() + offset);
                    glVertex3f(-dimensions.getX() - offset, +dimensions.getY() + offset, +dimensions.getZ() + offset);
                glEnd();

                //Top
                glBegin(GL_LINE_LOOP);
                    glVertex3f(-dimensions.getX() - offset, -dimensions.getY() - offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, -dimensions.getY() - offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, -dimensions.getY() - offset, +dimensions.getZ() + offset);
                    glVertex3f(-dimensions.getX() - offset, -dimensions.getY() - offset, +dimensions.getZ() + offset);
                glEnd();

                //Bot
                glBegin(GL_LINE_LOOP);
                    glVertex3f(-dimensions.getX() - offset, +dimensions.getY() + offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, +dimensions.getY() + offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, +dimensions.getY() + offset, +dimensions.getZ() + offset);
                    glVertex3f(-dimensions.getX() - offset, +dimensions.getY() + offset, +dimensions.getZ() + offset);
                glEnd();

                //Left
                glBegin(GL_LINE_LOOP);
                    glVertex3f(-dimensions.getX() - offset, -dimensions.getY() - offset, -dimensions.getZ() - offset);
                    glVertex3f(-dimensions.getX() - offset, -dimensions.getY() - offset, +dimensions.getZ() + offset);
                    glVertex3f(-dimensions.getX() - offset, +dimensions.getY() + offset, +dimensions.getZ() + offset);
                    glVertex3f(-dimensions.getX() - offset, +dimensions.getY() + offset, -dimensions.getZ() - offset);
                glEnd();

                //Right
                glBegin(GL_LINE_LOOP);
                    glVertex3f(+dimensions.getX() + offset, -dimensions.getY() - offset, -dimensions.getZ() - offset);
                    glVertex3f(+dimensions.getX() + offset, -dimensions.getY() - offset, +dimensions.getZ() + offset);
                    glVertex3f(+dimensions.getX() + offset, +dimensions.getY() + offset, +dimensions.getZ() + offset);
                    glVertex3f(+dimensions.getX() + offset, +dimensions.getY() + offset, -dimensions.getZ() - offset);
                glEnd();
            glPopMatrix();
        }
    }

    public boolean contains(Float3D point) {
        if (maxX() < point.getX() || minX() > point.getX()) {
            return false;
        }
        if (maxY() < point.getY() || minY() > point.getY()) {
            return false;
        }
        if (maxZ() < point.getZ() || minZ() > point.getZ()) {
            return false;
        }

        return true;
    }

    public float minX() {
        return (getPosition().getX() - dimensions.getX());
    }

    public float minY() {
        return (getPosition().getY() - dimensions.getY());
    }

    public float minZ() {
        return (getPosition().getZ() - dimensions.getZ());
    }

    public float maxX() {
        return (getPosition().getX() + dimensions.getX());
    }

    public float maxY() {
        return (getPosition().getY() + dimensions.getY());
    }

    public float maxZ() {
        return (getPosition().getZ() + dimensions.getZ());
    }

    public Float3D getDimensions() {
        return dimensions;
    }

    public Float3D getPosition() {
        return position;
    }

    public void setPosition(Float3D position) {
        this.position.set(position);
        recalcVertices();
    }

    public void include(AABB aabb) {
        float minX = Math.min(aabb.minX(), minX());
        float maxX = Math.max(aabb.maxX(), maxX());

        float minY = Math.min(aabb.minY(), minY());
        float maxY = Math.max(aabb.maxY(), maxY());

        float minZ = Math.min(aabb.minZ(), minZ());
        float maxZ = Math.max(aabb.maxZ(), maxZ());

        float centerX = (minX + maxX) / 2.0f;
        float centerY = (minY + maxY) / 2.0f;
        float centerZ = (minZ + maxZ) / 2.0f;

        getPosition().set(centerX, centerY, centerZ);

        float width = maxX - minX;
        float height = maxY - minY;
        float depth = maxZ - minZ;

        dimensions.set(width / 2.0f, height / 2.0f, depth / 2.0f);

        recalcVertices();
    }

    public float width() {
        return maxX() - minX();
    }

    public float height() {
        return maxY() - minY();
    }

    public float depth() {
        return maxZ() - minZ();
    }

    public synchronized void recalcVertices() {
        if (vertices != null) {
            // Front
            vertices[0].set(minX(), minY(), maxZ());
            vertices[1].set(maxX(), minY(), maxZ());
            vertices[2].set(maxX(), maxY(), maxZ());
            vertices[3].set(minX(), maxY(), maxZ());
            // Back
            vertices[4].set(minX(), minY(), minZ());
            vertices[5].set(maxX(), minY(), minZ());
            vertices[6].set(maxX(), maxY(), minZ());
            vertices[7].set(minX(), maxY(), minZ());
        }
    }

    public void set(AABB aabb) {
        this.position.set(aabb.getPosition());
        this.dimensions.set(aabb.getDimensions());
        recalcVertices();
    }
}
