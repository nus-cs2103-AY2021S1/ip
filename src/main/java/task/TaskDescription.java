package task;

import java.util.List;

import task.tasks.Task;

/**
 * Renders description of a task.
 */
public class TaskDescription {
    /**
     * Horizontal line to assist in formatting Kim Jong Duke's CLI responses.
     */
    private static final String divider = "\t____________________________________________________________\n";
    /**
     * Renders all outstanding tasks.
     *
     * @param tasks Outstanding tasks.
     */
    public static String showAllTask(List<Task> tasks) {
        if (tasks.size() == 0) {
            return "List of tasks is empty.";
        }
        String result = "";
        System.out.println(divider);
        for (int i = 0; i < tasks.size(); i++) {
            int serialNumber = i + 1;
            Task task = tasks.get(i);
            System.out.println("\t" + serialNumber + "." + task);
            result += "" + serialNumber + "." + task + "\n";
        }
        System.out.println(divider);
        return result;
    }

    /**
     * Renders successful addition of task to outstanding tasks.
     *
     * @param tasks   Outstanding tasks.
     * @param newTask Added task.
     */
    public static String addedTaskDescription(List<Task> tasks, Task newTask) {
        System.out.println(divider);
        System.out.println("\tGot it. I've added this task:\n");
        System.out.println("\t\t" + newTask + "\n");
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.\n");
        System.out.println(divider);
        return "Got it. I've added this task:\n"
                + "" + newTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Renders successful marked completion of task.
     *
     * @param doneTask Completed task.
     */
    public static String doneTaskDescription(Task doneTask) {
        System.out.println(divider);
        System.out.println("\tNice! I've marked this task as done:\n");
        System.out.println("\t" + doneTask);
        System.out.println(divider);
        return "Nice! I've marked this task as done:\n"
                + "" + doneTask;
    }

    /**
     * Renders message that task has already been marked as completed (ie [Y]).
     */
    public static String taskAlreadyMarkedDone() {
        System.out.println("This task has already been completed.\n");
        return "This task has already been completed.\n";
    }

    /**
     * Renders successful deletion of task from outstanding tasks.
     *
     * @param tasks       Outstanding tasks.
     * @param deletedTask Deleted task.
     */
    public static String deletedTaskDescription(List<Task> tasks, Task deletedTask) {
        System.out.println(divider);
        System.out.println("\tNoted. I've removed this task:\n");
        System.out.println("\t\t" + deletedTask + "\n");
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.\n");
        System.out.println(divider);
        return "Noted. I've removed this task:\n"
                + "\t" + deletedTask + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Renders tasks that user searched for.
     *
     * @param searchedTasks Searched tasks.
     */
    public static String searchedTaskDescription(List<Task> searchedTasks) {
        if (searchedTasks.size() > 0) { // Items found with find command
            String result = "";
            System.out.println(divider);
            System.out.println("\tHere are the matching tasks in your list:\n");
            result += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < searchedTasks.size(); i++) {
                int serialNumber = i + 1;
                Task task = searchedTasks.get(i);
                System.out.println("\t" + serialNumber + "." + task);
                result += "" + serialNumber + "." + task + "\n";
            }
            System.out.println(divider);
            return result;
        } else if (searchedTasks.size() == 0) { // No items found with find command
            System.out.println(divider);
            System.out.println("\tNo items with the finding keyword specified found in list.\n");
            System.out.println(divider);
            return "No items with the finding keyword specified found in list.\n";
        } else {
            System.err.println("Something went wrong while searching for tasks. Exiting system.");
            int statusCode = -1;
            System.exit(statusCode);
            return "";
        }
    }
}
