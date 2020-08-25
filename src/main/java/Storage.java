import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {

    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }


    public List<Task> load() throws DukeException {
        List<Task> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] t = sc.nextLine().split(",");

                switch (t[0]) {
                case "T":
                    list.add(new Todo(t[2], t[1].equals("1")));
                    break;
                case "D":
                    list.add(new Deadline(t[2], t[3], t[1].equals("1")));
                    break;
                case "E":
                    list.add(new Event(t[2], t[3], t[1].equals("1")));
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

    public void update(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t: list) {
                String toWrite = String.format("%s,%s", t.getType(), (t.getDone() ? "1" : "0"));
                String desc = t.getDescription();
                if ("DE".contains(t.getType())) {
                    String[] descSplit = desc.split(" / ");
                    toWrite += "," + descSplit[descSplit.length - 2];
                    toWrite += "," + descSplit[descSplit.length - 1];
                } else {
                    toWrite += "," + desc;
                }
                fw.write(toWrite + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
