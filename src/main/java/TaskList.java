import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list of tasks to keep track of.
 * A task is represented as a Task object.
 */
public class TaskList {

    private List<Task> list = new ArrayList<>();
    private Storage storage;

    TaskList(Storage storage) throws FileNotFoundException {
        this.storage = storage;
        Scanner scanner = storage.load();
        Parser parser = new Parser();
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            Task task = parser.parseFileData(taskString);
            list.add(task);
        }
    }

    /**
     * Adds task to the task list and saves it.
     *
     * @param task Task to be added.
     */
    protected String addTask(Task task) throws IOException {
        list.add(task);
        //System.out.println("Added: " + task + "\n");
        storage.saveTasks(toString());
        return "Added: " + task + "\n";
    }

    /**
     * Marks a task as done.
     *
     * @param listIndex Index of the task in the list to be marked as done.
     */
    protected String markTaskDone(int listIndex) throws IOException {
            Task task = list.get(listIndex - 1);
            task.markDone();
            //System.out.printf("Hurray! %s is now done.\n", task.getTask());
            //System.out.println(task + "\n");
            storage.saveTasks(toString());
            return String.format("Hurray! %s is now done.\n%s\n", task.getTask(), task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param listIndex Index of the task in the list to be marked deleted.
     */
    protected String deleteTask(int listIndex) throws IOException {
            Task task = list.get(listIndex - 1);
            list.remove(listIndex - 1);
            //System.out.printf("Okay %s has been deleted.\n", task.getTask());
            //System.out.println(task);
            //System.out.println("You now have " + list.size() + " tasks.\n");
            storage.saveTasks(toString());
            return String.format("Okay %s has been deleted.\n", task.getTask())
                    + task + "\n"
                    + "You now have " + list.size() + " tasks.\n";
    }

    /**
     * Finds tasks that match a specified keyword.
     *
     * @param keyword The keyword to be matched.
     */
    protected String findTask(String keyword) {
        String uiMessage = "Here are the matching tasks I could find:\n";
        int count = 1;
        for (Task task : list) {
            String description = task.getTask();
            if (description.contains(keyword)) {
                uiMessage += count++ + "." + task.toString() + "\n";
            }
        }
        if (count == 1) {
            uiMessage = "Sorry, I could not find any matching tasks :(\n";
        }
        return uiMessage;
    }

    @Override
    public String toString() {
        String message = "";
        int count = 1;
        for (Task task : list) {
            message += String.format("%d.%s\n"
                    , count++, task);
        }
        return message;
    }
}
