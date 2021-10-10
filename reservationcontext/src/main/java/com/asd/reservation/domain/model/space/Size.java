package main.java.com.asd.reservation.domain.model.space;

public class Size {
    private final float length;
    private final float width;

    public Size(float length, float width) {
        this.length = length;
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }
}
