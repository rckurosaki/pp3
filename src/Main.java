import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        // Get obj from file
        ObjLoader fr = new ObjLoader();
        ArrayList<Face> face_list = fr.read_file("files/coarseTri.rockerArm.obj"); //coarseTri.rockerArm.obj

        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        JSlider slider = new JSlider(0, 360, 180);
        pane.add(slider, BorderLayout.SOUTH);

        JSlider pSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pSlider, BorderLayout.EAST);

        JPanel renderPanel = new JPanel() {
            public void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.DARK_GRAY);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // vertical movement
                double heading = Math.toRadians(slider.getValue());
                Matriz hTransform = new Matriz(new double[]{
                        Math.cos(heading), 0, -Math.sin(heading),
                        0, 1, 0,
                        Math.sin(heading), 0, Math.cos(heading)
                });

                // horizontal movement
                double pitch = Math.toRadians((pSlider.getValue()));
                Matriz pTransform = new Matriz(new double[]{
                        1, 0, 0,
                        0, Math.cos(pitch), Math.sin(pitch),
                        0, -Math.sin(pitch), Math.cos(pitch)
                });

                Matriz transform = hTransform.multiply(pTransform);

                // rendering here

                g2.translate(getWidth() / 2, getHeight() / 2);
                g2.setColor(Color.WHITE);


                for (Face f : face_list) {
                    Vertice v1 = transform.transform(f.getV1());
                    Vertice v2 = transform.transform(f.getV2());
                    Vertice v3 = transform.transform(f.getV3());

                    Path2D path = new Path2D.Double();
                    path.moveTo(v1.getX(), v1.getY());
                    path.lineTo(v2.getX(), v2.getY());
                    path.lineTo(v3.getX(), v3.getY());
                    path.closePath();
                    g2.draw(path);

                }

            }
        };

        pane.add(renderPanel, BorderLayout.CENTER);

        slider.addChangeListener(e -> renderPanel.repaint());
        pSlider.addChangeListener(e -> renderPanel.repaint());

        frame.setSize(1000, 1000);
        frame.setVisible(true);

    }

}
