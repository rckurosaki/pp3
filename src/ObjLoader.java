import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ObjLoader {

    public ObjLoader() {
    }

    protected ArrayList<Face> read_file(String file_path) throws FileNotFoundException, IOException {
        ArrayList<Face> face_list = new ArrayList<>();
        ArrayList<Vertice> vertice_list = new ArrayList<>();

        File file = new File(file_path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {

            if (line.startsWith("v ")) {
                double x = Double.valueOf(line.split(" ")[1]);
                double y = Double.valueOf(line.split(" ")[2]);
                double z = Double.valueOf(line.split(" ")[3]);
                vertice_list.add(new Vertice(x, y, z));
            } else if (line.startsWith("f ")) {
                Vertice v1 = vertice_list.get(Integer.valueOf(line.split(" ")[1]) - 1);
                Vertice v2 = vertice_list.get(Integer.valueOf(line.split(" ")[2]) - 1);
                Vertice v3 = vertice_list.get(Integer.valueOf(line.split(" ")[3]) - 1);
                face_list.add(new Face(v1, v2, v3, Color.BLUE));
            }

        }
        return face_list;
    }
}
