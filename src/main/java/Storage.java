import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Storage {
    private static String filePath;
    private static List<Task> savedTasks;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.savedTasks = new ArrayList<>();
    }

    public static void loadFileContents() throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String taskSummary = s.nextLine();
            Storage.lineReader(taskSummary);
        }
    }

    public static void lineReader(String task) {
        String type = task.substring(1, 2);
        String description = "";
        String timeDescription = "";
        boolean isDone = false;

        if (task.substring(4, 5).equals("1")) {
            isDone = true;
        }

        if (type.equals("T")) {
            int end = task.length();
            description = task.substring(7, end);
            Todo newTodo = new Todo(description, isDone);
            Storage.savedTasks.add(newTodo);

        } else if (type.equals("E")) {
            int start = task.indexOf("(");
            int end = task.lastIndexOf(")");
            timeDescription = task.substring(start + 5, end);
            description = task.substring(7, start);
            Event newEvent = new Event(description, timeDescription, isDone);
            Storage.savedTasks.add(newEvent);

        } else if (type.equals("D")) {
            int start = task.indexOf("(");
            int end = task.lastIndexOf(")");
            timeDescription = task.substring(start + 5, end);
            description = task.substring(7, start);
            Deadline newDeadline = new Deadline(description, timeDescription, isDone);
            Storage.savedTasks.add(newDeadline);
        }
    }

    public static List<Task> getTaskList() {
        return Storage.savedTasks;
    }

    public static void writeToFile(List<Task> tasksToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int totalTasks = tasksToWrite.size();

        for (int i = 0; i < totalTasks; i++) {
            Task writeTask = tasksToWrite.get(i);
            String taskString = writeTask.toString();
            String replacement1 = taskString.replaceAll("✓", "1");
            String replacement2 = replacement1.replaceAll("✘", "0");
            fw.write(replacement2 + "\n");
        }
        fw.close();
    }
}