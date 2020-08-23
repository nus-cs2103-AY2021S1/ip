import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final Path path;
    private static final String FILE_PATH = "./data/tasks.txt";

    public Storage() {
        path = Paths.get(FILE_PATH);
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();
    }


    public void save(TaskList list) {
        try {
            int counter = list.getNumberOfTask();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task t : list.getList()) {
                String taskString = t.toFileString();
                fw.write(taskString);
                counter --;
                if (counter > 0) {
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> list = new ArrayList<>();
            File file = new File(FILE_PATH);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                Task task = null;
                String[] taskStringSplit = sc.nextLine().split(" \\| ", 3);
                String type = taskStringSplit[0];
                String isDoneString = taskStringSplit[1];
                String content = taskStringSplit[2];
                String[] contentSplit = content.split(" \\| ", 2);
                String description = "";
                String time = "";
                if (contentSplit.length == 2) {
                    description = contentSplit[0];
                    time = contentSplit[1];
                }
                boolean isDone = false;
                if (isDoneString.equals("1")) {
                    isDone = true;
                }
                switch (type) {
                    case "T":
                        task = new Todo(content, isDone);
                        break;
                    case "D":
                        task = new Deadline(description, isDone, time);
                        break;
                    case "E":
                        task = new Event(description, isDone, time);
                        break;
                }
                list.add(task);
            }
            return list;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

}
