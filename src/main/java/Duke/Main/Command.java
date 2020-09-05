package duke.main;

import duke.errors.DeadlineException;
import duke.errors.EmptyDescException;
import duke.errors.EventTaskException;
import duke.errors.InvalidCommandException;
import duke.errors.InvalidIndexException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Command.
 */
public class Command {
    /**
     * Todo command string.
     *
     * @param arr     the arr
     * @param comText the com text
     * @param tasks   the tasks
     * @return the string
     * @throws Exception the exception
     */
    public static String todoCommand(String[] arr, String comText, TaskList tasks) throws Exception {
        if (arr.length == 1) {
            throw new EmptyDescException();
        } else {
            Task todo = new Todo(comText, "0");
            tasks.addTask(todo.getStringArr());
            return String.format(
                    "Got it. I've added this task:" +
                            "\n%s\nNow you have %d tasks in the list.", todo,
                    tasks.getSize()
            );
        }
    }

    /**
     * Event command string.
     *
     * @param arr     the arr
     * @param comText the com text
     * @param tasks   the tasks
     * @return the string
     * @throws Exception the exception
     */
    public static String eventCommand(String[] arr, String comText, TaskList tasks) throws Exception {
        int eIdx = comText.lastIndexOf("/at");
        if (arr.length == 1) {
            throw new EmptyDescException();
        } else if (eIdx == -1 || comText.length() == (eIdx + 3)) {
            throw new EventTaskException();
        } else {
            String desc = comText.substring(0, eIdx - 1);
            String at = comText.substring(eIdx + 4, comText.length()).trim();

            LocalDateTime eventDate = Parser.strToDate(at);
            Task event = new Event(desc, "0", eventDate);
            tasks.addTask(event.getStringArr());
            return String.format(
                    "Got it. I've added this task:" +
                            "\n%s\nNow you have %d tasks in the list.", event,
                    tasks.getSize()
            );
        }
    }

    /**
     * Deadline command string.
     *
     * @param arr     the arr
     * @param comText the com text
     * @param tasks   the tasks
     * @return the string
     * @throws Exception the exception
     */
    public static String deadlineCommand(String[] arr, String comText, TaskList tasks) throws Exception {
        int dIdx = comText.lastIndexOf("/by");
        if (arr.length == 1) {
            throw new EmptyDescException();
        } else if (dIdx == -1 || comText.length() == (dIdx + 3)) {
            throw new DeadlineException();
        } else {
            String desc = comText.substring(0, dIdx - 1);
            String by = comText.substring(dIdx + 4, comText.length()).trim();

            LocalDateTime deadlineDate = Parser.strToDate(by);
            Task deadline = new Deadline(desc, "0", deadlineDate);
            tasks.addTask(deadline.getStringArr());
            return String.format(
                    "Got it. I've added this task:" +
                            "\n%s\nNow you have %d tasks in the list.",
                    deadline, tasks.getSize()
            );
        }
    }

    /**
     * Done command string.
     *
     * @param arr     the arr
     * @param comText the com text
     * @param tasks   the tasks
     * @return the string
     * @throws Exception the exception
     */
    public static String doneCommand(String[] arr, String comText, TaskList tasks) throws Exception {
        if (arr.length != 2) {
            throw new InvalidCommandException();
        }
        int doneNum = Integer.parseInt(arr[1]);
        if (doneNum > tasks.getSize() || doneNum < 0) {
            throw new InvalidIndexException();
        } else {
            Task item = tasks.completeTask(doneNum);
            return String.format(
                    "Nice! I've marked this task as done:\n%s",
                    item
            );
        }
    }

    /**
     * Delete command string.
     *
     * @param arr     the arr
     * @param comText the com text
     * @param tasks   the tasks
     * @return the string
     * @throws Exception the exception
     */
    public static String deleteCommand(String[] arr, String comText, TaskList tasks) throws Exception {
        if (arr.length != 2) {
            throw new InvalidCommandException();
        }
        int deleteNum = Integer.parseInt(arr[1]);
        if (deleteNum > tasks.getSize() || deleteNum < 0) {
            throw new InvalidIndexException();
        } else {
            Task item = tasks.deleteTask(deleteNum);
            return String.format(
                    "Noted. I've removed this task:\n%s" + "\nNow you have %d tasks in the list.", item,
                    tasks.getSize()
            );
        }
    }

    /**
     * Find command string.
     *
     * @param comText the com text
     * @param tasks   the tasks
     * @return the string
     * @throws Exception the exception
     */
    public static String findCommand(String comText, TaskList tasks) throws Exception {
        List<Task> foundTasks = tasks.findTasks(comText);
        return String.format(
                "Here are the matching tasks in your list:\n%s",
                Ui.getList(foundTasks)
        );
    }

}
