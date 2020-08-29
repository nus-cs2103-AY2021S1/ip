package duke.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * This class deals with unit interface of Duke.
 * This includes the messages that Duke will send as well as
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
     *
     * @return user input
     */
    public String readLine() {
        return this.sc.nextLine();
    }

    /**
     * Welcomes the user when the user starts Duke.
     */
    public void welcome() {
        String message = "Hi! My name is Duke.\nWhat do you want me to do?";
        sendMessage(message);
    }

    /**
     * Sends a farewell message to the user when the user exit Duke.
     */
    public void exit() {
        String message = "Bye. Thank you for using me!";
        sendMessage(message);
    }

    /**
     * Returns a message about the specified task has been marked
     * as done.
     *
     * @param task task that was marked as done
     * @return done success message
     */
    public String doneSuccess(Task task) {
        return "Sucessfully marked this task as done:\n" + INDENTATION + task.toString();
    }

    /**
     * Returns a message about the specified task has been deleted.
     * The message will also tells how many tasks left in the list.
     *
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
     * The message will also tells how many tasks left in the list.
     *
     * @param task task that was added
     * @param taskSize the task size after addition
     * @return add success message
     */
    public String addSuccess(Task task, int taskSize) {
        return "Okay. I will add this task:\n" + INDENTATION + task + "\n"
                + "Now you have " + taskSize + " " + (taskSize == 1 ? "task " : "tasks ") + "in the list.";
    }

    /**
     * Returns a message showing all of tasks in the list.
     * The numbering shown in this message is used when the user
     * wants to delete task or mark task as done.
     *
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
     * Returns a message showing all the tasks in the list that happens
     * after the specified date.
     *
     * @param date the specified date by the user
     * @param tasks list of tasks
     * @return message about all the tasks in the list after the specified date
     */
    public String eachTaskAfter(LocalDate date, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the tasks after "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
        int count = 1;
        for (Task task : tasks.getTasks()) {
            if (task instanceof ToDoTask) {
                continue;
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (date.isBefore(deadlineTask.getDate().toLocalDate())) {
                    sb.append(count + ". " + task + "\n");
                    count++;
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (date.isBefore(eventTask.getDate().toLocalDate())) {
                    sb.append(count + ". " + task + "\n");
                    count++;
                }
            }
        }

        return sb.toString().trim();
    }

    /**
     * Returns a message showing all the tasks in the list that happens
     * before or equals to the specified date.
     *
     * @param date the specified date by the user
     * @param tasks list of tasks
     * @return message about all the tasks in the list before
     * or equals the specified date
     */
    public String eachTaskBefore(LocalDate date, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here is the tasks before "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":\n");
        int count = 1;
        for (Task task : tasks.getTasks()) {
            if (task instanceof ToDoTask) {
                continue;
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;
                if (date.isAfter(deadlineTask.getDate().toLocalDate())
                        || date.isEqual(deadlineTask.getDate().toLocalDate())) {
                    sb.append(count + ". " + task + "\n");
                    count++;
                }
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task;
                if (date.isAfter(eventTask.getDate().toLocalDate())
                        || date.isEqual(eventTask.getDate().toLocalDate())) {
                    sb.append(count + ". " + task + "\n");
                    count++;
                }
            }
        }

        return sb.toString().trim();
    }

    /**
     * Returns a message showing all the tasks in the list that contains
     * the specified keyword.
     *
     * @param keyword keyword to find task
     * @param tasks list of tasks
     * @return message about all the task that contains the specified keyword
     */
    public String findTask(String keyword, TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;
        int count = 1;

        for (Task task : tasks.getTasks()) {
            if (task.getTaskName().contains(keyword)) {
                isFound = true;
                sb.append(count + ". " + task + "\n");
                count++;
            }
        }

        if (isFound) {
            return "Here are the matching tasks in your list:\n" + sb.toString().trim();
        } else {
            return "No task found with that keyword!";
        }
    }

    /**
     * Gets the DukeException's message and prints it to the user.
     *
     * @param e exception thrown when running duke
     */
    public void showError(DukeException e) {
        sendMessage(e.getMessage());
    }

    /**
     * Prints the specified message to the user.
     *
     * @param sendMessage message to be sent to the user
     */
    public void sendMessage(String sendMessage) {
        System.out.println(createLine(sendMessage));
    }

    /**
     * Creates a line of equals sign before and after the specified message.
     * The length of the line may change depends on the length of
     * the message.
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
