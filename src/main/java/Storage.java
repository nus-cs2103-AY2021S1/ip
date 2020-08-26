import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public Storage load() throws EmptyDescriptionException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File duke = new File(this.filePath);
        if (!duke.exists()) {
            try {
                duke.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File f = new File(this.filePath);
        try {
            Scanner s = new Scanner(f);

            String string;
            Task task;

            while (s.hasNext()) {
                string = s.nextLine();
                if (string.contains("[D]")) {
                    int deadline = string.indexOf("(");
                    int space = string.indexOf(" ");
                    int colon = string.indexOf(":");

                    String description = "deadline "
                            + string.substring(space + 1, deadline) + "/"
                            + string.substring(deadline + 1, colon)
                            + string.substring(colon + 1, string.length() - 1);

                    task = new Deadline(description);
                } else if (string.contains("[E]")) {
                    int time = string.indexOf("(");
                    int space = string.indexOf(" ");
                    int colon = string.indexOf(":");

                    String description = "event "
                            + string.substring(space + 1, time) + "/"
                            + string.substring(time + 1, colon)
                            + string.substring(colon + 1, string.length() - 1);

                    task = new Event(description);
                } else {
                    int space = string.indexOf(" ");
                    String description = "todo " + string.substring(7);
                    task = new ToDo(description);
                }
                if (string.contains("[\u2713]")) {
                    task.markAsDone();
                }
                this.tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return this;
    }

    public void updateFile() {
        try {
            FileWriter fw = new FileWriter(this.filePath);

            Iterator i = this.tasks.iterator();

            while (i.hasNext()) {
                fw.write(i.next().toString() + System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
