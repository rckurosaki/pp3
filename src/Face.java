import java.awt.*;

public class Face {
    private Vertice v1;
    private Vertice v2;
    private Vertice v3;
    private Color color;

    public Face(Vertice v1, Vertice v2, Vertice v3, Color color) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;
    }

    public Vertice getV1() {
        return v1;
    }

    public Vertice getV2() {
        return v2;
    }

    public Vertice getV3() {
        return v3;
    }

    public Color getColor() {
        return color;
    }

    public void setV1(Vertice v1) {
        this.v1 = v1;
    }

    public void setV2(Vertice v2) {
        this.v2 = v2;
    }

    public void setV3(Vertice v3) {
        this.v3 = v3;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
