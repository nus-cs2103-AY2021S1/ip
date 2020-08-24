import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public String filePath;
    public List<Task> tasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    public List<Task> load() throws DukeException {
        File f = new File(filePath);
        if (f.exists()) {
            try {
                Scanner s = new Scanner(f);
                readTask(f, tasks);
            } catch (FileNotFoundException e) {
                new File("data").mkdir();
                throw new DukeException("File not found. Created a file.");
            }
        } else {
            new File("data").mkdir();
            throw new DukeException("File not found. Created a file.");
        }

        return tasks;
    }

    public static void readTask(File f, List<Task> lists) {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String string = s.nextLine();
                if (string.charAt(1) == 'T') {
                    String description = string.substring(string.indexOf(' ') + 1);
                    if (string.charAt(4) == '✓') {
                        lists.add(new ToDo(description, true));
                    } else {
                        lists.add(new ToDo(description));
                    }
                } else if (string.charAt(1) == 'D') {
                    String description = string.substring(string.indexOf(' ') + 1, string.indexOf('('));
                    String by = string.substring(string.indexOf("(by") + 5);
                    if (string.charAt(4) == '✓') {
                        lists.add(new Deadline(description, by, true));
                    } else {
                        lists.add(new Deadline(description, by));
                    }
                } else {
                    String time = string.substring(string.indexOf("(at") + 5);
                    String description = string.substring(string.indexOf(' ') + 1, string.indexOf('('));
                    if (string.charAt(4) == '✓') {
                        lists.add(new Event(description, time, true));
                    } else {
                        lists.add(new Event(description, time));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public static void writeToFile(List<Task> lists) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        String textToAdd = "";

        for (int i = 0; i < lists.size(); i++) {
            Task task = lists.get(i);
            textToAdd = textToAdd + task.toString() + System.lineSeparator();
        }

        fw.write(textToAdd);
        fw.close();
    }
}
