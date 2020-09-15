package duke.ui;

import java.io.IOException;
import java.time.LocalDate;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    /**
     * Returns a string representing the task list in a human readable format
     *
     * @param tasks task list of tasks to be printed
     * @return a string representing the task list in a human readable format
     */
    public static String getListString(TaskList tasks) {
        StringBuilder output = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            output.append(String.format("%d. %s%n", index, task));
            index++;
        }
        return output.toString();
    }

    /**
     * Returns a string representing the list of the tasks that matches the specified date in the task
     * list in a human readable format
     *
     * @param tasks task list of tasks
     * @param date  of the tasks to be found in the output string
     * @return a string representing the list of the tasks that matches the specified date in the task
     * list
     */
    public static String getListDate(TaskList tasks, LocalDate date) {
        StringBuilder output = new StringBuilder();
        int index = 1;
        for (Task task : tasks) {
            if (task.getDate().isPresent() && task.getDate().get().isEqual(date)) {
                output.append(String.format("%d. %s%n", index, task));
                index++;
            }
        }
        return output.toString();
    }

    public static String getGreetings() {
        return
            "Hi here are the list of commands I understand."
                + "\n"
                + "`list` - List all tasks\n"
                + "\n"
                + "`date <date>` - List all tasks on a date\n"
                + "\n"
                + " `todo <task_name>` - Adds a todo task\n"
                + " `event `\n"
                + " \n"
                + " `deadline <task_name> /by <date>` - Add a deadline task to be completed by `<date>`\n"
                + " \n"
                + "`event <task_name> /at <date>` - Add an event task that is scheduled for `<date>`\n"
                + "\n"
                + "`done <task_index>` - Marks the task at the task index as done\n"
                + "\n"
                + "`delete <task_index>` - Deletes the task at the task index \n"
                + "\n"
                + "`find <keywords>` - Finds all the tasks in the task list that has the keywords \n"
                + "\n"
                + "`tag <task_index> <tag_name>` - Adds a tag to the task specified\n"
                + "\n"
                + "`bye` - Shuts down the program\n"
                + "\n";
    }

    public static void showList(TaskList tasks) {
        System.out.print(Ui.getListString(tasks));
    }

    public static void showListDate(TaskList tasks, LocalDate date) {
        System.out.println(Ui.getListDate(tasks, date));
    }

    public static void showGreetings() {
        System.out.println(Ui.getGreetings());
    }

    public static void showBye() {
        System.out.println("Bye! Hope to never see you again!");
    }

    public static void showDone(Task task) {
        System.out.printf("Good job. This task is marked as done:\n %s%n", task);
    }

    public static void showDeleted(Task task) {
        System.out.printf("Noted. I've remove this task:\n %s%n", task);
    }

    public static void showTagged(Task task) {
        System.out.printf("Noted. I've tagged this task:\n %s%n", task);
    }

    public static void showAddTask(Task task) {
        System.out.printf("Got it. I've added this task:%n %s%n", task);
    }

    public static void showError(String message) {
        System.out.println(message);
    }

    public static void showUnexpectedError(String message) {
        System.out.println("Unexpected Error:\n" + message);
    }

    public static DukeException commandInvalidException() {
        return new DukeException("This command is invalid.");
    }

    public static DukeException taskDescriptionEmptyException(CommandType type) {
        return new DukeException(String.format("The description of a %s cannot be empty.", type));
    }

    public static DukeException taskDateEmptyException(CommandType type) {
        return new DukeException(
            String.format("Error! The description of a %s is missing a date.", type));
    }

    public static DukeException taskIndexDescriptionFormatException(CommandType type, String descriptionFormat) {
        return new DukeException(
            String
                .format("Input format is wrong. Please make sure it is `%s <task-index> %s`", type, descriptionFormat));
    }

    public static DukeException taskIndexFormatException(CommandType type) {
        return new DukeException(
            String.format("Input format is wrong. Please make sure it is `%s <task-index>`", type));
    }

    public static DukeException taskDateFormatException() {
        return new DukeException(
            "Date format is wrong. It should be yyyy-mm-dd and within possible date ranges");
    }

    public static DukeException taskIndexOutOfBoundsException(int index, TaskList tasks) {
        StringBuilder message =
                new StringBuilder(String.format("Task at index %d doesn't exist%n", index));
        if (tasks.size() == 0) {
            message.append("\nThere are no tasks currently.");
        } else {
            message.append(
                    String.format(
                            "There are %d tasks currently. Please a number between 1 and %d inclusive.",
                            tasks.size(), tasks.size()));
        }

        return new DukeException(message.toString());
    }

    public static DukeException ioException(IOException e) {
        return new DukeException("Something is wrong with the input:\n" + e.getMessage());
    }
}
