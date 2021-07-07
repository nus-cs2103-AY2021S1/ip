package duke;

import java.util.stream.Stream;

/**
 * Class to initiate the Ui object. Contains various methods to handle different duke.commands.
 */
public class Ui {

    /**
     * Formats the message.
     *
     * @param words Words to be concatenated.
     * @return String formatted message of the given words.
     */
    private String messageFormatter(String ... words) {
        StringBuilder string = new StringBuilder();
        Stream<String> stream = Stream.of(words);
        stream.forEachOrdered(string::append);
        string.append("\n");
        return string.toString();
    }

    /**
     * Prints the welcome message.
     *
     * @param taskList Prints out the current tasks in list.
     */
    public String welcomeMessage(String taskList) {
        return messageFormatter("Hello! I'm Duke! Welcome back!\n",
                "Here are the tasks in your list:\n",
                taskList, "\nInput \"help\" to see a list of commands.");
    }

    public String byeMessage() {
        return messageFormatter("Bye. Hope to see you again soon!");
    }

    public String listMessage(String taskList) {
        return messageFormatter("Here are the tasks in your list:\n", taskList);
    }

    public String errorMessage(String error) {
        return messageFormatter(error);
    }

    /**
     * Prints the done message.
     *
     * @param message Prints out the current task that is completed.
     */
    public String markAsDoneMessage(String message) {
        return messageFormatter("Nice! I've marked this task as done:\n",
                message);
    }

    /**
     * Prints the delete message.
     *
     * @param task Prints out the current deleted task.
     * @param size Number of tasks in the list.
     */
    public String deleteMessage(String task, int size) {
        return messageFormatter("Noted. I've removed this task:\n",
                task, "\nNow you have ", Integer.toString(size), " tasks in the list.");
    }

    /**
     * Prints the the added task message.
     *
     * @param task Prints out the added task in list.
     * @param size Number of tasks in the list.
     */
    public String taskMessage(String task, int size) {
        return messageFormatter("Got it. I've added this task:\n", task,
                "\nNow you have ", Integer.toString(size), " tasks in the list.");
    }

    /**
     * Prints the task that suits the input keyword message.
     *
     * @param taskList Prints out the task that suits the input keyword.
     * @param size Number of tasks in the list.
     */
    public String findTaskMessage(String taskList, int size) {
        if (size == 0) {
            return messageFormatter("There are not matching task in your list!");
        } else {
            return messageFormatter("There are ", Integer.toString(size),
                    " matching tasks in your list:\n", taskList);
        }
    }

    /**
     * Prints the list of commands available in the duke program.
     */
    public String helpMessage() {
        return messageFormatter("Here is the list of available commands:\n",
                "todo <task> : Adds a todo task to your task list.\n",
                "event <task> /at <YYYY-MM-DD HH:MM> : Adds a event with a date to your task list.\n",
                "deadline <task> /at <YYYY-MM-DD HH:MM> : Adds a deadline task with a date to your task list.\n",
                "done <number> : Marks the task at the input position as done\n",
                "delete <number> : Delete the task at the input number position.\n",
                "reminder <number> : Shows the list of task within the next input number of days.\n",
                "find <keyword> : Finds and shows a list of commands which contains the input keyword.\n",
                "list : Displays the list of items in your task list.\n",
                "help : Displays the list of available commands in duke.\n",
                "bye : Exits the duke bot.\n"
                );
    }

    /**
     * Prints the list of task or deadline withen the input date from now.
     */
    public String reminderMessage(int numberOfDays, String taskInString, int size) {
        if (size == 0) {
            return messageFormatter("There are no task that is due within ",
                    Integer.toString(size), " days.");
        } else {
            return messageFormatter("Here is a list of task that is due within ",
                    Integer.toString(numberOfDays), " days:\n", taskInString);
        }
    }
}
