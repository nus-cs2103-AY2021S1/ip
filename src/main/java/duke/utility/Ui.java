package duke.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import duke.exception.DukeException;
import duke.task.DateTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * This class deals with unit interface of Duke. This includes the messages that Duke will send as well as
 * reading the user input.
 */
public class Ui {
    private static final String INDENTATION = "  ";
    private Scanner sc;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the user input as a string.
     * @return user input
     */
    public String readLine() {
        return this.sc.nextLine();
    }

    /**
     * Welcomes the user when the user starts Duke.
     */
    public String welcome() {
        return "Hi! My name is Duke.\nWhat do you want me to do?";
    }

    /**
     * Sends a farewell message to the user when the user exit Duke.
     */
    public String exit() {
        return "Bye. Thank you for using me!";
    }

    /**
     * Returns a message about the specified task has been marked as done.
     * @param task task that was marked as done
     * @return done success message
     */
    public String doneSuccess(Task task) {
        return "Successfully marked this task as done:\n" + INDENTATION + task.toString();
    }

    /**
     * Returns a message about the specified task has been deleted.
     * @param task task that was deleted
     * @param taskSize the task size after deletion
     * @return delete success message
     */
    public String deleteSuccess(Task task, int taskSize) {
        return "Okay. I will delete this task:\n" + INDENTATION + task + "\n"
                + "Now you have " + taskSize + " " + (taskSize == 1 ? "task " : "tasks ") + "in the list.";
    }

    /**
     * Returns a message about the specified task has been added.
     * @param task task that was added
     * @param taskSize the task size after addition
     * @return add success message
     */
    public String addSuccess(Task task, int taskSize) {
        return "Okay. I will add this task:\n" + INDENTATION + task + "\n"
                + "Now you have " + taskSize + " " + (taskSize == 1 ? "task " : "tasks ") + "in the list.";
    }

    public String changeDateSuccess(Task task) {
        return "Successfully change this task's date:\n" + INDENTATION + task;
    }

    /**
     * Returns a message showing all of tasks in the list.
     * @param tasks TaskList to be shown
     * @return message about all the tasks in the list
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }

        return sb.toString().trim();
    }

    /**
     * Returns a message showing all the tasks in the list that happens after the specified date.
     * @param date the specified date by the user
     * @param tasks list of tasks
     * @return message about all the tasks in the list after the specified date
     */
    public String showTaskAfter(LocalDate date, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);

        tasks.getTasks()
                .stream()
                .filter((task) -> {
                    if (task instanceof ToDoTask) {
                        return false;
                    } else if (task instanceof DateTask) {
                        DateTask dateTask = (DateTask) task;
                        return date.isBefore(dateTask.getDate().toLocalDate());
                    }

                    return false;
                })
                .forEach((task) -> sb.append(index.getAndIncrement() + ". " + task + "\n"));

        if (sb.length() == 0) {
            return "There is no task after this date!";
        }

        return "Here is the tasks after " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n"
                + sb.toString().trim();
    }

    /**
     * Returns a message showing all the tasks in the list that happens before or equals to the specified date.
     * @param date the specified date by the user
     * @param tasks list of tasks
     * @return message about all the tasks in the list before
     * or equals the specified date
     */
    public String showTaskBefore(LocalDate date, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);

        tasks.getTasks()
                .stream()
                .filter((task) -> {
                    if (task instanceof ToDoTask) {
                        return false;
                    } else if (task instanceof DateTask) {
                        DateTask dateTask = (DateTask) task;
                        return date.isAfter(dateTask.getDate().toLocalDate())
                                || date.isEqual(dateTask.getDate().toLocalDate());
                    }

                    return false;
                })
                .forEach((task) -> sb.append(index.getAndIncrement() + ". " + task + "\n"));

        if (sb.length() == 0) {
            return "There is no task before this date!";
        }

        return "Here is the tasks before " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n"
                + sb.toString().trim();
    }

    /**
     * Returns a message showing all the tasks in the list that contains the specified keyword.
     * @param keyword keyword to find task
     * @param tasks list of tasks
     * @return message about all the task that contains the specified keyword
     */
    public String findTask(String keyword, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);

        tasks.getTasks()
                .stream()
                .filter((task) -> task.getTaskName().contains(keyword))
                .forEach((task) -> sb.append(index.getAndIncrement() + ". " + task + "\n"));

        if (sb.length() == 0) {
            return "No task found with that keyword!";
        }

        return "Here are the matching tasks in your list:\n" + sb.toString().trim();
    }

    /**
     * Gets the DukeException's message and prints it to the user.
     * @param e exception thrown when running duke
     */
    public void showError(DukeException e) {
        sendMessage(e.getMessage());
    }

    /**
     * Prints the specified message to the user.
     * @param sendMessage message to be sent to the user
     */
    public void sendMessage(String sendMessage) {
        System.out.println(createLine(sendMessage));
    }

    /**
     * Creates a line of equals sign before and after the specified message.
     * The length of the line may change depends on the length of the message.
     *
     * @param message the specified message
     * @return message with line before and after it
     */
    public String createLine(String message) {
        Scanner sc = new Scanner(message);
        String equalSign = "=";
        int width = 75;
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            String textLine = INDENTATION + sc.nextLine();
            sb.append(textLine + "\n");
            width = Math.max(width, textLine.length() + 2);
        }

        String line = equalSign.repeat(width);

        return line + "\n" + sb.toString() + line;
    }
}
