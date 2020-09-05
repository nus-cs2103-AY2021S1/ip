package duke;

import java.util.ArrayList;

/**
 * TaskList class contains the task list and all operations that modify task in the list.
 */
public class TaskList {

    /** User's list of tasks */
    protected static ArrayList<Task> tasks;

    /**
     * Initialises user's list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task into user's list of tasks.
     *
     * @param task
     */

    public static String addTask(Task task) {
        tasks.add(task);
//        String str = "_____________________________________________________"
//                + "\nGot it. I've added this task:"
//                + "\n" + task
//                + "\nNow you have " + tasks.size() + " task(s) in the list."
//                + "\n_____________________________________________________\n";
        String str = "Got it. I've added this task:"
                + "\n" + task
                + "\nNow you have " + tasks.size() + " task(s) in the list.";
        return str;
    }

   /**
    * Adds task from listOfTasks.txt into user's list of tasks.
    *
    * @param task
    */
   public static void addStoredTask(Task task) {
       tasks.add(task);
   }

    /**
     * Prints user's list of tasks.
     *
     * @throws DukeException
     */
    public static String getListOfTasks() throws DukeException {
        String str = "";
        if (tasks.isEmpty()) {
            try {
                throw new DukeException("", DukeExceptionType.EMPTY_LIST);
            } catch (DukeException e) {
                System.err.println(e);
                str += e;
            }
        } else {
//            String lst = "_____________________________________________________"
//                    + "\nHere are the tasks in your list:";
            String lst = "Here are the tasks in your list:";
            for (int i = 0; i < tasks.size(); i++) {
                int index = i + 1;
                lst += "\n" + index + ". " + tasks.get(i);
            }
//            lst += "\n_____________________________________________________\n";
            str = lst;
        }
        System.out.println(str);
        return str;
    }

    /**
     * Marks a task as done when user completes it.
     *
     * @param index
     */
    public static String done(Integer index) {
        tasks.get(index - 1).markAsDone();
//        String str = "_____________________________________________________"
////                + "\nNice! I've marked this task as done:\n      "
////                + tasks.get(index - 1)
////                + "\n_____________________________________________________\n";
        String str = "Nice! I've marked this task as done:\n      "
                + tasks.get(index - 1);
        return str;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index
     */
    public static String delete(Integer index) {
//        String str = "_____________________________________________________"
//                + "\nNoted. I've removed this task:\n      "
//                + tasks.remove(index - 1)
//                + "\n    Now you have " + tasks.size() + " task(s) in the list."
//                + "\n_____________________________________________________\n";
        String str = "Noted. I've removed this task:\n      "
                + tasks.remove(index - 1)
                + "\n    Now you have " + tasks.size() + " task(s) in the list.";
        return str;
    }

    /**
     * Finds matching tasks based on keyword provided by user.
     *
     * @param keyword
     */
    public static String findTasks(String keyword) {
        String str = "";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            try {
                throw new DukeException("", DukeExceptionType.NO_MATCHING_FOUND);
            } catch (DukeException e) {
                str += e;
            }
        } else {
            int index = 1;
//            str += "_____________________________________________________"
////                    + "\nHere are the matching tasks in your list:";
            str += "Here are the matching tasks in your list:";
            for (Task task : matchingTasks) {
                str += "\n" + index + ". " + task;
                index++;
            }
//            str += "\n_____________________________________________________\n";
        }
        return str;
    }
}
