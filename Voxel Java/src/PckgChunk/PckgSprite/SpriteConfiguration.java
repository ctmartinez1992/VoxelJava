package PckgChunk.PckgSprite;

import PckgMath.Float2D;
import PckgMath.Float3D;

/**
 *
 * @author Carlos
 */
public class SpriteConfiguration {
    
    private Float3D position;
    private Float2D center;
    
    private Float3D translation;
    private float scale;
    private float rotation;
    
    public SpriteConfiguration(Float3D position, Float2D center, Float3D translation, float scale, float rotation) {
        this.position = position;
        this.scale = scale;
        this.translation = translation;
        this.center = center;
        this.rotation = rotation;
    }

    public void setPosition(Float3D position) {
        this.position = position;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void settranslation(Float3D translation) {
        this.translation = translation;
    }

    public void setCenter(Float2D center) {
        this.center = center;
    }

    public void setRotationY(float rotationY) {
        this.rotation = rotationY;
    }
    
    public Float3D getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }
    
    public Float3D getTranslation() {
        return translation;
    }

    public Float2D getCenter() {
        return center;
    }

    public float getRotationY() {
        return rotation;
    }
}