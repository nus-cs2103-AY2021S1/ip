import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Task> initializeTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> loadedTasks = new ArrayList<>();
        while (s.hasNext()) {
            String task = s.nextLine();
            switch (task.charAt(1)) {
                case('T'):
                    if (task.charAt(4) == '✓') {
                        Task newTask = new ToDo(task.substring(7));
                        newTask.setDone();
                        loadedTasks.add(newTask);
                    } else if (task.charAt(4) == '✘') {
                        Task newTask = new ToDo(task.substring(7));
                        loadedTasks.add(newTask);
                    }
                    break;
                case('D'):
                    if (task.charAt(4) == '✓') {
                        int indexOfDeadline = task.indexOf("by:");
                        String date = task.substring(indexOfDeadline + 4, task.length() - 1);
                        String description = task.substring(7, indexOfDeadline - 2);
                        Task newTask = new Deadline(description, date);
                        newTask.setDone();
                        loadedTasks.add(newTask);
                    } else if (task.charAt(4) == '✘') {
                        int indexOfDeadline = task.indexOf("by:");
                        String date = task.substring(indexOfDeadline + 4, task.length() - 1);
                        String description = task.substring(7, indexOfDeadline - 2);
                        Task newTask = new Deadline(description, date);
                        loadedTasks.add(newTask);
                    }
                    break;
                case('E'):
                    if (task.charAt(4) == '✓') {
                        int indexOfEvent = task.indexOf("at:");
                        String date = task.substring(indexOfEvent + 4, task.length() - 1);
                        String description = task.substring(7, indexOfEvent - 2);
                        Task newTask = new Event(description, date);
                        newTask.setDone();
                        loadedTasks.add(newTask);
                    } else if (task.charAt(4) == '✘') {
                        int indexOfEvent = task.indexOf("at:");
                        String date = task.substring(indexOfEvent + 4, task.length() - 1);
                        String description = task.substring(7, indexOfEvent - 2);
                        Task newTask = new Event(description, date);
                        loadedTasks.add(newTask);
                    }
                    break;
            }
        }
        return loadedTasks;
    }
}
