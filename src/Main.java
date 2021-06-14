import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        // Get obj from file
        ObjLoader fr = new ObjLoader();
        ArrayList<Face> face_list = fr.read_file("files/coarseTri.rockerArm.obj"); //coarseTri.rockerArm.obj

        // Create new window with sliders
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JSlider slider = new JSlider(0, 360, 180);
        pane.add(slider, BorderLayout.SOUTH);

        JSlider pSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pSlider, BorderLayout.EAST);

        // Starts process to render object in the new window
        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.DARK_GRAY);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Vertical object movement
                double heading = Math.toRadians(slider.getValue());
                Matriz hTransform = new Matriz(new double[]{
                        Math.cos(heading), 0, -Math.sin(heading),
                        0, 1, 0,
                        Math.sin(heading), 0, Math.cos(heading)
                });

                // Horizontal object movement
                double pitch = Math.toRadians(pSlider.getValue());
                Matriz pTransform = new Matriz(new double[]{
                        1, 0, 0,
                        0, Math.cos(pitch), Math.sin(pitch),
                        0, -Math.sin(pitch), Math.cos(pitch)
                });

                Matriz transform = hTransform.multiply(pTransform);

                // Rendering object
                BufferedImage img =
                    new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);

                for (Face f : face_list) {
                    Vertice v1 = transform.transform(f.getV1());
                    Vertice v2 = transform.transform(f.getV2());
                    Vertice v3 = transform.transform(f.getV3());

                    v1.x += getWidth() / 2.0;
                    v1.y += getHeight() / 2.0;
                    v2.x += getWidth() / 2.0;
                    v2.y += getHeight() / 2.0;
                    v3.x += getWidth() / 2.0;
                    v3.y += getHeight() / 2.0;

                    // Compute rectangular bounds for faces
                    int minX = (int) Math.max(0, Math.ceil(Math.min(v1.x, Math.min(v2.x, v3.x))));
                    int maxX = (int) Math.min(img.getWidth() - 1, Math.floor(Math.max(v1.x, Math.max(v2.x, v3.x))));
                    int minY = (int) Math.max(0, Math.ceil(Math.min(v1.y, Math.min(v2.y, v3.y))));
                    int maxY = (int) Math.min(img.getHeight() - 1, Math.floor(Math.max(v1.y, Math.max(v2.y, v3.y))));

                    double faceArea =
                            (v1.y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - v1.x);

                    for (int y = minY; y <= maxY; y++) {
                        for (int x = minX; x <= maxX; x++) {
                            double b1 =
                                    ((y - v3.y) * (v2.x - v3.x) + (v2.y - v3.y) * (v3.x - x)) / faceArea;
                            double b2 =
                                    ((y - v1.y) * (v3.x - v1.x) + (v3.y - v1.y) * (v1.x - x)) / faceArea;
                            double b3 =
                                    ((y - v2.y) * (v1.x - v2.x) + (v1.y - v2.y) * (v2.x - x)) / faceArea;
                            if (b1 >= 0 && b1 <= 1 && b2 >= 0 && b2 <= 1 && b3 >= 0 && b3 <= 1) {
                                img.setRGB(x, y, f.color.getRGB());
                            }
                        }
                    }
                }
                g2.drawImage(img, 0, 0, null);
            }
        };

        pane.add(renderPanel, BorderLayout.CENTER);

        slider.addChangeListener(e -> renderPanel.repaint());
        pSlider.addChangeListener(e -> renderPanel.repaint());

        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
}
