package PckgMath.PckgStructure;

import PckgMath.Float3D;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Carlos
 */
public class RayBlockIntersection {

    public static class Intersection implements Comparable<Intersection> {

        private final float _d;
        private final float _t;
        private final Float3D _rayOrigin, _intersectionPoint, _rayDirection;
        private final Float3D _surfaceNormal;
        private final float _blockX, _blockY, _blockZ;

        public Intersection(float blockX, float blockY, float blockZ, Float3D normal, float d, float t, Float3D rayOrigin, Float3D rayDirection, Float3D intersectionPoint) {
            this._d = d;
            this._t = t;
            this._rayOrigin = rayOrigin;
            this._rayDirection = rayDirection;
            this._intersectionPoint = intersectionPoint;
            this._surfaceNormal = normal;
            this._blockX = blockX;
            this._blockY = blockY;
            this._blockZ = blockZ;
        }

        @Override
        public int compareTo(Intersection o) {
            if (o == null) {
                return 0;
            }

            double distance = _t;
            double distance2 = o._t;

            if (distance == distance2) {
                return 0;
            }

            return distance2 > distance ? -1 : 1;
        }

        Float3D getSurfaceNormal() {
            return _surfaceNormal;
        }

        public Float3D calcAdjacentBlockPos() {
            Float3D position = getBlockPosition();
            position.add(getSurfaceNormal());

            return position;
        }

        public Float3D getBlockPosition() {
            return new Float3D(_blockX, _blockY, _blockZ);
        }

        @Override
        public String toString() {
            return String.format("x: %d y: %d z: %d", _blockX, _blockY, _blockZ);
        }

        public float getDistance() {
            return _t;
        }
    }

    public static ArrayList<Intersection> executeIntersection(int x, int y, int z, AABB blockAABB, Float3D rayOrigin, Float3D rayDirection) {

        ArrayList<Intersection> result = new ArrayList<>(2);

        /*
         * Fetch all vertices of the specified block.
         */
        Float3D[] vertices = blockAABB.getVertices();

        /*
         * Generate a new intersection for each side of the block.
         */

        // Front
        Intersection is = executeBlockFaceIntersection(x, y, z, vertices[0], vertices[1], vertices[3], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Back
        is = executeBlockFaceIntersection(x, y, z, vertices[4], vertices[7], vertices[5], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Left
        is = executeBlockFaceIntersection(x, y, z, vertices[4], vertices[0], vertices[7], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Right
        is = executeBlockFaceIntersection(x, y, z, vertices[5], vertices[6], vertices[1], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Top
        is = executeBlockFaceIntersection(x, y, z, vertices[7], vertices[3], vertices[6], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Bottom
        is = executeBlockFaceIntersection(x, y, z, vertices[4], vertices[5], vertices[0], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Sort the intersections by distance to the player
        Collections.sort(result);
        return result;
    }

    public static ArrayList<Intersection> executeIntersection(float x, float y, float z, AABB blockAABB, Float3D rayOrigin, Float3D rayDirection) {

        ArrayList<Intersection> result = new ArrayList<>(2);

        /*
         * Fetch all vertices of the specified block.
         */
        Float3D[] vertices = blockAABB.getVertices();

        /*
         * Generate a new intersection for each side of the block.
         */

        // Front
        Intersection is = executeBlockFaceIntersection(x, y, z, vertices[0], vertices[1], vertices[3], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Back
        is = executeBlockFaceIntersection(x, y, z, vertices[4], vertices[7], vertices[5], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Left
        is = executeBlockFaceIntersection(x, y, z, vertices[4], vertices[0], vertices[7], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Right
        is = executeBlockFaceIntersection(x, y, z, vertices[5], vertices[6], vertices[1], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Top
        is = executeBlockFaceIntersection(x, y, z, vertices[7], vertices[3], vertices[6], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Bottom
        is = executeBlockFaceIntersection(x, y, z, vertices[4], vertices[5], vertices[0], rayOrigin, rayDirection);
        if (is != null) {
            result.add(is);
        }

        // Sort the intersections by distance to the player
        Collections.sort(result);
        return result;
    }

    private static Intersection executeBlockFaceIntersection(int blockX, int blockY, int blockZ, Float3D v0, Float3D v1, Float3D v2, Float3D origin, Float3D ray) {

        // Calculate the plane to intersect with
        Float3D a = new Float3D(v1);
        a.sub(v0);
        Float3D b = new Float3D(v2);
        b.sub(v0);
        Float3D norm = new Float3D();
        norm.cross(a, b);

        float d = -(norm.getX() * v0.getX() + norm.getY() * v0.getY() + norm.getZ() * v0.getZ());

        /**
         * Calculate the distance on the ray, where the intersection occurs.
         */
        float t = -(norm.getX() * origin.getX() + norm.getY() * origin.getY() + norm.getZ() * origin.getZ() + d) / Float3D.dot(ray, norm);

        // No intersection possible
        if (t < 0) {
            return null;
        }

        /**
         * Calc. the point of intersection.
         */
        Float3D intersectPoint = new Float3D((ray.getX() * t), (ray.getY() * t), (ray.getZ() * t));
        intersectPoint.add(origin);

        /**
         * Check if the point lies on block's face.
         */
        if (intersectPoint.getX() >= v0.getX() && intersectPoint.getX() <= Math.max(v1.getX(), v2.getX()) && intersectPoint.getY() >= v0.getY() && intersectPoint.getY() <= Math.max(v1.getY(), v2.getY()) && intersectPoint.getZ() >= v0.getZ() && intersectPoint.getZ() <= Math.max(v1.getZ(), v2.getZ())) {
            return new Intersection(blockX, blockY, blockZ, new Float3D((int) norm.getX(), (int) norm.getY(), (int) norm.getZ()), d, t, origin, ray, intersectPoint);
        }

        // Point of intersection was NOT lying on the block's face
        return null;
    }

    private static Intersection executeBlockFaceIntersection(float blockX, float blockY, float blockZ, Float3D v0, Float3D v1, Float3D v2, Float3D origin, Float3D ray) {

        // Calculate the plane to intersect with
        Float3D a = new Float3D(v1);
        a.sub(v0);
        Float3D b = new Float3D(v2);
        b.sub(v0);
        Float3D norm = new Float3D();
        norm.cross(a, b);

        float d = -(norm.getX() * v0.getX() + norm.getY() * v0.getY() + norm.getZ() * v0.getZ());

        /**
         * Calculate the distance on the ray, where the intersection occurs.
         */
        float t = -(norm.getX() * origin.getX() + norm.getY() * origin.getY() + norm.getZ() * origin.getZ() + d) / Float3D.dot(ray, norm);

        // No intersection possible
        if (t < 0) {
            return null;
        }

        /**
         * Calc. the point of intersection.
         */
        Float3D intersectPoint = new Float3D((ray.getX() * t), (ray.getY() * t), (ray.getZ() * t));
        intersectPoint.add(origin);

        /**
         * Check if the point lies on block's face.
         */
        if (intersectPoint.getX() >= v0.getX() && intersectPoint.getX() <= Math.max(v1.getX(), v2.getX()) && intersectPoint.getY() >= v0.getY() && intersectPoint.getY() <= Math.max(v1.getY(), v2.getY()) && intersectPoint.getZ() >= v0.getZ() && intersectPoint.getZ() <= Math.max(v1.getZ(), v2.getZ())) {
            return new Intersection(blockX, blockY, blockZ, new Float3D((int) norm.getX(), (int) norm.getY(), (int) norm.getZ()), d, t, origin, ray, intersectPoint);
        }

        // Point of intersection was NOT lying on the block's face
        return null;
    }
}
