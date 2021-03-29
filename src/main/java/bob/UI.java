package bob;

import bob.exception.BobIndexOutOfBoundsException;
import bob.task.Task;

import java.util.Scanner;


/**
 * This class consists of methods which prints out information that may be helpful to the user.
 * It also consists of a single method which accepts user input, which may be passed to Bob's
 * parser.
 */
public class UI {
    /** A scanner which scans user's input */
    private Scanner sc = new Scanner(System.in);

    /**
     * Returns a greeting from Bob to the user.
     */

    public static String greet() {
        String greetings = "Hello! I'm Bob\nWhat can I do for you?";
        return greetings;
    }

    /**
     * Iterates through a TaskList to return all tasks on the TaskList in a message.
     *
     * @param tasks the TaskList to be printed out.
     * @return all tasks on the TaskList in a message.
     * @throws BobIndexOutOfBoundsException if the method tries to retrieve a task with an index not on the list.
     */
    public String printOutList(TaskList tasks) throws BobIndexOutOfBoundsException {
        String list = "";
        for (int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i);
            if (i == tasks.size()) {
                list += i + "." + task.toString();
            } else {
                list += i + "." + task.toString() + "\n";
            }
        }
        if (tasks.isEmpty()) {
            return "There are no tasks in the list at the moment. Feel free to add any.";
        }
        return list;
    }

    /**
     * Returns a message to indicate that a task of a particular index has been deleted from the TaskList.
     *
     * @param tasks the TaskList from which the task has been deleted.
     * @param index the index of the task on the TaskList that has been deleted.
     * @return a message to indicate that a task of a particular index has been deleted from the TaskList.
     * @throws BobIndexOutOfBoundsException if the index of the task to be deleted does not exist on the TaskList.
     */
    public String deleteTask(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        Task task = tasks.get(index);
        int newSize = tasks.size() - 1;
        return "Noted. I have removed the following task:\n\t" + task.toString()
                + "\nThere are now " + newSize + " remaining tasks on the list.";
    }

    /**
     * Returns a message to indicate that a task of a particular index has been marked as done on the TaskList.
     *
     * @param tasks the TaskList from which the task has been marked as done.
     * @param index the index of the task on the TaskList that has been marked as done.
     * @return a message to indicate that a task of a particular index has been marked as done on the TaskList.
     * @throws BobIndexOutOfBoundsException if the index of the task to be marked as done does not exist on the TaskList.
     */
    public String markAsDone(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        Task task = tasks.get(index);
        return "Good job! I have marked this task as done:"
                + "\n\t" + index + "." + task.toString();
    }

    /**
     * Returns a message to indicate that a task has been added to the TaskList.
     *
     * @param task the task that has been added to TaskList.
     * @return a message to indicate that a task has been added to the TaskList.
     */
    public String addTask(Task task) {
        return "Got it! I have added a new task to the list."
                + "\n\tadded: \n\t" + task.toString();
    }

    /**
     * Returns user input.
     *
     * @return user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns an error message.
     *
     * @param error the error message to be printed out.
     * @return an error message.
     */
    public String printError(String error) {
        return error;
    }

    /**
     * Returns a message that may consists of tasks which contains a keyword or phrase.
     *
     * @param tasks Tasks which consists of a keyword or phrase
     * @return a message that may consists of tasks which contains a keyword or phrase.
     * @throws BobIndexOutOfBoundsException if accessing an index of the TaskList which does not exist.
     */
    public String findKeyWord(TaskList tasks) throws BobIndexOutOfBoundsException {
        if (!tasks.isEmpty()) {
            String message = "Here are the tasks in your lists which match: ";
            for (int i = 1; i < tasks.size() + 1; i++) {
                Task task = tasks.get(i);
                message += "\n\t" + i + "." + task.toString();
            }
            return message;
        } else {
            return "None of the tasks in your list match.";
        }
    }

    /**
     * Returns a message to indicate that a Task on the TaskList has been snoozed.
     *
     * @param tasks the TaskList.
     * @param index the index of the task that has been snoozed.
     * @return a message to indicate that a Task on the TaskList has been snoozed.
     * @throws BobIndexOutOfBoundsException if accessing an index of the TaskList which does not exist.
     */
    public String snoozeTask(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        return "Noted. I have updated the task to the following: "
                + "\n\t" + tasks.get(index).toString();
    }

    /**
     * Returns a message to indicate that a task on the TaskList has been rescheduled.
     *
     * @param tasks the TaskList
     * @param index the index of the task that has been rescheduled.
     * @return a message to indicate that a task on the TaskList has been rescheduled.
     * @throws BobIndexOutOfBoundsException if accessing an index of the TaskList which does not exist.
     */
    public String rescheduleTask(TaskList tasks, int index) throws BobIndexOutOfBoundsException {
        return "Noted. I have updated the task to the following: "
                + "\n\t" + tasks.get(index).toString();
    }

    /**
     * Returns a message to indicate that all tasks on the TaskList have been marked as done.
     *
     * @param tasks the TaskList.
     * @return a message to indicate that all tasks on the TaskList have been marked as done.
     */
    public String markAllDone(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks to mark as done.";
        } else {
            return "Noted. I have marked all tasks as done.";
        }
    }

    /**
     * Returns a message to indicate that all tasks on the TaskList have been deleted.
     *
     * @param tasks the TaskList
     * @return a message to indicate that all tasks on the TaskList have been deleted.
     */
    public String deleteAll(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "There are no tasks to delete.";
        } else {
            return "Noted. All tasks have been deleted.";
        }
    }

    /**
     * Returns an exit message.
     * @return an exit message.
     */
    public String exit() {
        return "Bye! I hope to see you soon! \n[This window will close in 3 seconds.]";
    }
}
