import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void store(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(".\\data\\duke.txt");
            StringBuilder textToWrite = new StringBuilder();

            for (int i = 1; i <= tasks.getNumOfTasks(); i++) {
                Task t = tasks.retrieve(i);
                String nextEntry =
                        t.getStringType() + " / " +
                        t.isDoneToString() + " / " +
                        t.getDescription() + t.getDate().map(result -> " / " + result).orElse("");

                textToWrite.append(nextEntry).append("\n");
            }
            writer.write(String.valueOf(textToWrite));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>(100);

            while (sc.hasNext()) {
                String nextEntryLine = sc.nextLine();
                String[] nextEntryArray = nextEntryLine.split(" / ", 4);
                String type = nextEntryArray[0];
                String description = nextEntryArray[2];
                int length = nextEntryArray.length;
                boolean isDone = nextEntryArray[1].equals("1");

                Task t;
                if (type.equals("T") && length == 3) {
                    t = new Todo(description);
                    if (isDone) { t.markAsDone(); }
                    tasks.add(t);

                } else if (type.equals("D") && length == 4) {
                    String by = nextEntryArray[3];
                    t = new Deadline(description, by);
                    tasks.add(t);

                } else if (type.equals("E") && length == 4) {
                    String at = nextEntryArray[3];
                    t = new Event(description, at);
                    tasks.add(t);
                } else {
                    throw new DukeException("Check duke.txt storage file integrity:");
                }
            }
            return tasks;

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(100);
        }
    }
}
