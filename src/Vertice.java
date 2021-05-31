public class Vertice {
    private double x;
    private double y;
    private double z;

    public Vertice(double x, double y, double z) {
        this.x = x * 30;
        this.y = y * 30;
        this.z = z * 30;
    }

    public void setX(double x) {
        this.x = x * 30;
    }

    public void setY(double y) {
        this.y = y * 30;
    }

    public void setZ(double z) {
        this.z = z * 30;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
