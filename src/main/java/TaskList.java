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
    protected void addTask(Task task) throws IOException {
        list.add(task);
        System.out.println("Added: " + task + "\n");
        storage.saveTasks(toString());
    }

    /**
     * Marks a task as done.
     *
     * @param listIndex Index of the task in the list to be marked as done.
     */
    protected void markTaskDone(int listIndex) throws IOException {
            Task task = list.get(listIndex - 1);
            task.markDone();
            System.out.printf("Hurray! %s is now done.\n", task.getTask());
            System.out.println(task + "\n");
            storage.saveTasks(toString());
    }

    /**
     * Deletes a task from the list.
     *
     * @param listIndex Index of the task in the list to be marked deleted.
     */
    protected void deleteTask(int listIndex) throws IOException {
            Task task = list.get(listIndex - 1);
            list.remove(listIndex - 1);
            System.out.printf("Okay %s has been deleted.\n", task.getTask());
            System.out.println(task);
            System.out.println("You now have " + list.size() + " tasks.\n");
            storage.saveTasks(toString());
    }

    /**
     * Finds tasks that match a specified keyword.
     *
     * @param keyword The keyword to be matched.
     */
    protected void findTask(String keyword) {
        String result = "Here are the matching tasks I could find:\n";
        int count = 1;
        for (Task task : list) {
            String description = task.getTask();
            if (description.contains(keyword)) {
                result += count++ + "." + task.toString() + "\n";
            }
        }
        if (count == 1) {
            System.out.println("Sorry, I could not find any matching tasks :(\n");
        } else {
            System.out.println(result);
        }
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
