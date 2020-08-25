import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" \\| ");
                Task t;

                switch (line[0]) {
                    case "T":
                        t = new Todo(line[2]);
                        break;
                    case "D":
                        t = new Deadline(line[2], line[3]);
                        break;
                    case "E":
                        t = new Event(line[2], line[3]);
                        break;
                    default:
                        throw new DukeException("Failed to load tasks, check file for syntax errors");
                }

                if (line[1].equals("1")) {
                    t.setDone();
                }
                list.add(t);
            }
            sc.close();
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("File Not Found.");
        }
    }

    public void saveFile(TaskList list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : list.getList()) {
                fw.write(t.toFile());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Save to File Failed.");
        }
    }
}
