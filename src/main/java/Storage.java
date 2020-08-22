import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final File f;

    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    public ArrayList<String> load() throws DukeIOException {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                res.add(s);
            }
            return res;
        } catch (FileNotFoundException e) {
            throw new DukeIOException("PROJ_ROOT/data/duke.txt not found!");
        }

    }

    public void save(ArrayList<String> data) throws DukeIOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i != data.size() - 1) {
                sb.append('\n');
            }
        }

        File dataDir = new File("data/");
        if (!dataDir.exists()) {
            dataDir.mkdir(); // if intellij is slow in displaying data dir, right click -> reload from disk
        }

        String textToPrint = sb.toString();
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(textToPrint);
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException(e.getMessage());
        }

    }
}
