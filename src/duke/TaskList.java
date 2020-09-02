package duke;

import java.util.ArrayList;

/**
 * TaskList class is a specific class that contains the task storage, a
 * static variable to collect user inputs, and manipulates it accordingly.
 */
public class TaskList {
    /**
     * The arraylist to store all user input tasks.
     */
    public static ArrayList<Task> taskStorage = new ArrayList<>();

    /**
     * Calls the modify function to write to taskStorage.
     *
     * @param input  description string of the task
     * @param type   a string to indicate the type/class of the task
     * @param byOrAt a date and time object to specify the timing of the task
     */
    public static void write(String input, String type, DateAndTime byOrAt) {

        modify(input, type, byOrAt, taskStorage);

    }

    /**
     * A helper function to write to the taskStorage.
     *
     * @param input       description string of the task
     * @param type        a string to indicate the type/class of the task
     * @param byOrAt      a date and time object to specify the timing of the task
     * @param taskStorage the static class variable for storage purpose
     */
    private static void modify(String input, String type, DateAndTime byOrAt, ArrayList<Task> taskStorage) {
        Task toBeAdded;
        if (type.equals("todo")) {
            toBeAdded = new ToDo(input);
            taskStorage.add(toBeAdded);
        } else if (type.equals("deadline")) {
            toBeAdded = new Deadline(input, byOrAt);
            taskStorage.add(toBeAdded);
        } else {
            toBeAdded = new Event(input, byOrAt);
            taskStorage.add(toBeAdded);
        }
        System.out.println("Got it. I've added this task: \n" + toBeAdded);
        System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");
    }

    /**
     * Reads(prints) tasks stored in taskStorage.
     */
    public static void read() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskStorage.size(); i++) {
            System.out.println((i + 1) + "." + taskStorage.get(i));
        }
    }

    /**
     * Deletes a task at a specified position in taskStorage.
     *
     * @param ref an integer to specify the position/numbering of the task
     */
    public static void delete(int ref) {
        if (ref >= taskStorage.size()) {
            System.out.println("I am afraid that it is not possible to delete an unknown task.");
        } else {
            Task temp = taskStorage.get(ref);
            taskStorage.remove(ref);
            System.out.println("Noted. I've removed this task:\n " +
                    temp + "\nNow you have " + taskStorage.size() + " tasks in the list.");
        }
    }

    /**
     * Finds all tasks in taskStorage which contains the specified feature.
     *
     * @param feature a string description of the tasks to find
     */
    public static void find(String feature) {

        System.out.println("Here are the matching tasks in your list:");
        for (Task task : TaskList.taskStorage) {
            if (task.description.contains(feature)) {
                System.out.println(task);
            }
        }

    }


}
