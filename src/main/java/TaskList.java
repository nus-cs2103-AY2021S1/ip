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
     * @return a string notification of successful saving
     */
    public static String write(String input, String type, DateAndTime byOrAt) {

        return modify(input, type, byOrAt, taskStorage);

    }

    /**
     * A helper function to write to the taskStorage.
     *
     * @param input       description string of the task
     * @param type        a string to indicate the type/class of the task
     * @param byOrAt      a date and time object to specify the timing of the task
     * @param taskStorage the static class variable for storage purpose
     */
    private static String modify(String input, String type, DateAndTime byOrAt, ArrayList<Task> taskStorage) {
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
        return "Got it. I've added this task: \n" + toBeAdded + "\n" +
                "Now you have " + taskStorage.size() + " tasks in the list.";
    }

    /**
     * Reads(prints) tasks stored in taskStorage.
     */
    public static String read() {
        String starter = "Here are the tasks in your list:\n";
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskStorage.size(); i++) {
            content.append(i + 1).append(".").append(taskStorage.get(i)).append("\n");
        }
        return starter + content;
    }

    /**
     * Deletes a task at a specified position in taskStorage.
     *
     * @param ref an integer to specify the position/numbering of the task
     */
    public static String delete(int ref) {
        if (ref >= taskStorage.size()) {
            return ("I am afraid that it is not possible to delete an unknown task.");
        } else {

            assert taskStorage.get(ref) != null;

            Task temp = taskStorage.get(ref);
            taskStorage.remove(ref);
            return ("Noted. I've removed this task:\n " +
                    temp + "\nNow you have " + taskStorage.size() + " tasks in the list.");
        }
    }

    /**
     * Finds all tasks in taskStorage which contains the specified feature.
     *
     * @param feature a string description of the tasks to find
     */
    public static String find(String feature) {

        String starter = "Here are the matching tasks in your list:\n";
        StringBuilder content = new StringBuilder();

        for (Task task : TaskList.taskStorage) {
            if (task.description.contains(feature)) {
                content.append(task).append("\n");
            }
        }
        return starter + content;
    }

    /**
     * Check through the tasklist to spot any deadline tasks.
     * If there are multiple deadlines, the nearest will be returned.
     *
     * @return a string representation of the most urgent deadline task
     */
    public static String remind() {

        ArrayList<Deadline> mostUrgent = new ArrayList<>();

        for (Task task : TaskList.taskStorage) {

            if (task instanceof Deadline) {

                if (mostUrgent.isEmpty()) {
                    mostUrgent.add((Deadline) task);
                }

                if (((Deadline) task).compareTo(mostUrgent.get(0)) < 0) {
                    mostUrgent.remove(0);
                    mostUrgent.add((Deadline) task);
                }

                if (((Deadline) task).compareTo(mostUrgent.get(0)) == 0) {
                    if (!task.description.equals(mostUrgent.get(0).description)) {
                        mostUrgent.add((Deadline) task);
                    }
                }
            }
        }

        if (mostUrgent.isEmpty()) {
            return ("Nice ! No upcoming deadline :). ");
        }

        String starter = "Here is the nearest deadline(s) in your list: \n";
        StringBuilder content = new StringBuilder();

        for (Deadline deadline : mostUrgent) {
            content.append(deadline).append("\n");
        }

        return starter + content;
    }

}
