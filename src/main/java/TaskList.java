import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

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
        List<Task> duplicateTasks = findDuplicateTasks(task);
        String message = "Hurray! I have added: " + task + "\n";
        list.add(task);
        storage.saveTasks(toString());

        if (!duplicateTasks.isEmpty()) {
            int count = 1;
            duplicateTasks.add(task);
            message += "FYI, you now have multiple(" + duplicateTasks.size() + ") '"
                    + task.getTask() + "' tasks" +
                    ".\nPlease let me know if you want to delete the duplicates.\n";
            for (Task t : duplicateTasks) {
                message += count++ + ". " + t.toString() + "\n";
            }
        }
        return message;
    }

    private List<Task> findDuplicateTasks(Task task) {
        List<Task> duplicateTasks = new ArrayList<>();
        String name = task.getTask();
        list.stream().forEach((Task t) -> {
            if (t.getTask().equals(name)) {
                duplicateTasks.add(t);
            }
        });

        return duplicateTasks;
    }

    /**
     * Marks a task as done.
     *
     * @param listIndex Index of the task in the list to be marked as done.
     */
    protected String markTaskDone(int listIndex) throws IOException {
        Task task = list.get(listIndex - 1);
        task.markDone();
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
