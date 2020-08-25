import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            ArrayList<Task> list = new ArrayList<>();
            boolean hasFolder = file.getParentFile().mkdirs();
            boolean hasFile = file.createNewFile();
            if (!hasFile && !hasFolder) {
                Scanner sc = new Scanner(file);
                while (sc.hasNext()) {
                    String task = sc.nextLine();
                    String[] details = task.split(" \\| ");
                    if (details[0].equals("T")) {
                        list.add(new ToDo(details[2]));
                    } else if (details[0].equals("D")) {
                        list.add(new Deadline(details[2], LocalDate.parse(details[3])));
                    } else {
                        list.add(new Event(details[2], LocalDate.parse(details[3])));
                    }
                    if (details[1].equals("1")) {
                        list.get(list.size() - 1).done();
                    }
                }
                sc.close();
            }
            return list;
        } catch (IOException error) {
            throw new DukeException("IOException");
        }
    }

    public void updateDataFile(ArrayList<Task> list) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        PrintWriter print_line = new PrintWriter(writer);
        for (Task task : list) {
            String[] details = new String[4];
            details[2] = task.name;
            if (task instanceof ToDo) {
                details[0] = "T";
            } else if (task instanceof Deadline) {
                details[0] = "D";
                details[3] = ((Deadline) task).by.toString();
            } else {
                details[0] = "E";
                details[3] = ((Event) task).duration.toString();
            }
            if (task.isDone) {
                details[1] = "1";
            } else {
                details[1] = "0";
            }
            String textLine = details[0] + " | " + details[1] + " | " + details[2]
                    + " | " + details[3];
            print_line.printf("%s" + "%n", textLine);
        }
        print_line.close();
    }
}