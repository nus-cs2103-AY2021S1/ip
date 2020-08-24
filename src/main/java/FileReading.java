import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class FileReading {
    private static List<Task> savedTasks = new ArrayList<>();

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        System.out.println("    _______________________________________________________________________");
        System.out.println("     Here are the current tasks in your file:");

        while (s.hasNext()) {
            String taskSummary = s.nextLine();
            System.out.println("     " + taskSummary);
            FileReading.lineReader(taskSummary);
        }

        System.out.println("    _______________________________________________________________________\n");
    }

    public static void lineReader(String task) {
        String type = task.substring(0, 1);
        String description = "";
        String timeDescription = "";
        boolean isDone = false;

        if (task.substring(4, 5).equals("1")) {
            isDone = true;
        }

        if (type.equals("T")) {
            int end = task.length();
            description = task.substring(8, end);
            Todo newTodo = new Todo(description, isDone);
            FileReading.savedTasks.add(newTodo);

        } else if (type.equals("E")) {
            int end = task.length();
            int start = task.lastIndexOf("|");
            timeDescription = task.substring(start + 1, end);
            String cutTask = task.substring(0, start);
            start = cutTask.lastIndexOf("|");
            end = cutTask.length();
            description = cutTask.substring(start + 2, end);
            Event newEvent = new Event(description, timeDescription, isDone);
            FileReading.savedTasks.add(newEvent);

        } else if (type.equals("D")) {
            int end = task.length();
            int start = task.lastIndexOf("|");
            timeDescription = task.substring(start + 1, end);
            String cutTask = task.substring(0, start);
            start = cutTask.lastIndexOf("|");
            end = cutTask.length();
            description = cutTask.substring(start + 2, end);
            Deadline newDeadline = new Deadline(description, timeDescription, isDone);
            FileReading.savedTasks.add(newDeadline);
        }
    }

    public static List<Task> getTaskList() {
        return FileReading.savedTasks;
    }
}