public class Matriz {
    double[] values;

    public Matriz(double[] values) {
        this.values = values;
    }

    Matriz multiply(Matriz m) {
        double[] mValues = m.getValues();
        double[] result = new double[9];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                for (int i = 0; i < 3; i++) {
                    result[row * 3 + col] += this.values[row * 3 + i] * mValues[i * 3 + col];
                }
            }
        }

        return new Matriz(result);
    }

    Vertice transform(Vertice v) {
        double x = v.getX() * values[0] + v.getY() * values[3] + v.getZ() * values[6];
        double y = v.getX() * values[1] + v.getY() * values[4] + v.getZ() * values[7];
        double z = v.getX() * values[2] + v.getY() * values[5] + v.getZ() * values[8];

        return new Vertice(x, y, z);
    }

    public double[] getValues() {
        return values;
    }
}
