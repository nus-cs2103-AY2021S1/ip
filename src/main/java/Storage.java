import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<String> load() throws IOException, DukeException {
        boolean exists = helper();
        if (!exists) {
            throw new DukeException();
        }
        File f = new File(filepath);
        Scanner sc = new Scanner(f);
        ArrayList<String> lst = new ArrayList<>();
        while (sc.hasNext()) {
            lst.add(sc.nextLine());
        }
        sc.close();
        return lst;
    }

    public boolean helper() throws IOException {
        boolean exists = true;
        File f;
        f = new File(filepath.substring(0, filepath.lastIndexOf("/")));
        if (!f.isDirectory()) {
            exists = false;
            f.mkdirs();

        }
        f = new File(filepath);
        if (f.createNewFile()) {
            exists = false;
        }
        return exists;
    }

    public void save(ArrayList<Task> lst) throws IOException {
        File f = new File(filepath);
        new FileWriter(f, false).close();
        FileWriter fw = new FileWriter(f, true);
        for (Task t : lst) {
            fw.write(t.formattedString() + System.lineSeparator());
        }
        fw.close();
    }
}
