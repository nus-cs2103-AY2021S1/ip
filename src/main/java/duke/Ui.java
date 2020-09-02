package duke;

import java.util.ArrayList;

/**
 * Prints the relevant messages.
 */
public class Ui {
    /**
     * Prints logo "Welcome" greeting.
     */
    public static String logoMsg() {
        String logo = " ___       __   _______   ___       ________  "
                + "________  _____ ______   _______      \n"
                + "|\\  \\     |\\  \\|\\  ___ \\ |\\  \\     "
                + "|\\   ____\\|\\   __  \\|\\   _ \\  _   \\|\\  ___ \\     \n"
                + "\\ \\  \\    \\ \\  \\ \\   __/|\\ \\  \\    \\ \\  \\___|"
                + "\\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\   __/|    \n"
                + " \\ \\  \\  __\\ \\  \\ \\  \\_|/_\\ \\  \\    \\ \\  \\    "
                + "\\ \\  \\\\\\  \\ \\  \\\\|__| \\  \\ \\  \\_|/__  \n"
                + "  \\ \\  \\|\\__\\_\\  \\ \\  \\_|\\ \\ \\  \\____\\ \\  \\____"
                + "\\ \\  \\\\\\  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\ \n"
                + "   \\ \\____________\\ \\_______\\ \\_______\\ \\_______"
                + "\\ \\_______\\ \\__\\    \\ \\__\\ \\_______\\\n"
                + "    \\|____________|\\|_______|\\|_______|\\|_______|\\|_______"
                + "|\\|__|     \\|__|\\|_______|"
                + "\n\n WELCOME to DukeBT!";
        System.out.println(logo);
        return logo;
    }

    /**
     * Prints greeting.
     */
    public static String greetingMsg() {
        String greetingMsg = "\n___________________________________________________________"
                + "\n |°‿°|"
                + "\n Hello! I'm DukeBT."
                + "\n What can I do for you?"
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n___________________________________________________________\n";
        System.out.println(greetingMsg);
        return greetingMsg;
    }

    /**
     * Prints help message to show all possible commands available.
     */
    public static String helpMsg() {
        String commandList = "**************************************************************"
                + "\n ┗( ⊙.⊙ )┛ "
                + "\n These are all the commands you may use:"
                + "\n     1.list -- show all tasks"
                + "\n     2.todo <task name> -- add a task"
                + "\n     3.deadline <task name> /by <task deadline> -- add a task with its deadline"
                + "\n     4.event <event name> /at <event timing> -- add an event with its timing"
                + "\n     5.done <task index> -- mark this task as done"
                + "\n     6.delete <task index> -- mark this task from task list"
                + "\n     7.find <keyword> -- find all tasks in the task list which contains the keyword"
                + "\n     8.help -- show all commands"
                + "\n     9.bye -- exit the chatbot"
                + "\n**************************************************************\n";
        System.out.println(commandList);
        return commandList;
    }

    /**
     * Prints bye message to see goodbye to the user.
     */
    public static String byeMsg() {
        String byeMsg = "\n___________________________________________________________"
                + "\n |^_^|┛"
                + "\n Bye. Hope to see you again soon!"
                + "\n Your task list has been saved."
                + "\n It will be automatically reloaded when you come back!"
                + "\n___________________________________________________________\n";
        System.out.println(byeMsg);
        return byeMsg;
    }

    /**
     * Prints confirmation message to show user the deadline task has been added into the task list.
     *
     * @param tasks The overall user's task list.
     */
    public static String addDeadlineTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this Deadline task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints confirmation message to show user the event task has been added into the task list.
     *
     * @param tasks The overall user's task list.
     */
    public static String addEventTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this Event task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints confirmation message to show user the to-do task has been added into the task list.
     *
     * @param tasks The overall user's task list.
     */
    public static String addTodoTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = "\n___________________________________________________________"
                + "\n (^.^)"
                + "\n Got it. I've added this ToDo task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints confirmation message to show user the task task has been marked as done.
     *
     * @param index A series number of the task in the task list.
     * @param tasks The overall user's task list.
     */
    public static String doneTaskMsg(int index, ArrayList<Task> tasks) {
        String outputMsg = "___________________________________________________________"
                + "\n (ﾉﾟ0ﾟ)ﾉ~"
                + "\n Congratulations from DukeBT! You have done 1 task!"
                + "\n The task below has been marked as done:"
                + "\n      Task #" + index + ". " + tasks.get(index - 1)
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints confirmation message to show user the corresponding task has been deleted from the task list.
     *
     * @param index A series number of the task in the task list.
     * @param taskToDelete Task to be deleted.
     * @param tasks
     * @return
     */
    public static String deleteTaskMsg(int index, Task taskToDelete, TaskList tasks) {
        String outputMsg = "___________________________________________________________"
                + "\n (ಠ‿↼)"
                + "\n Noted. This task has been removed from your task list:"
                + "\n      Task #" + index + ". " + taskToDelete
                + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list."
                + "\n___________________________________________________________\n";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints out all tasks stored in the task list.
     *
     * @param tasks The overall user's task list.
     */
    public static String getAllTasksMsg(ArrayList<Task> tasks) {
        String outputMsg = "";
        if (tasks.isEmpty()) {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n Task list is empty, please try to add some tasks first."
                    + "\n___________________________________________________________\n";
        } else {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n You have " + tasks.size() + " tasks in total."
                    + "\n Here they are:";
            for (int i = 0; i < tasks.size(); i++) {
                outputMsg += "\n      " + (i + 1) + ". " + tasks.get(i);
            }
            outputMsg += "\n___________________________________________________________\n";
        }

        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints out all tasks that match the keyword stored in the task list.
     *
     * @param keyword A string of keyword to find the matching tasks.
     * @param matchedTasks An ArrayList of all the tasks that match with the keyword.
     */
    public static String findMatchingTasks(String keyword, ArrayList<Task> matchedTasks) {
        String outputMsg = "";
        if (matchedTasks.isEmpty()) {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n There is no matching task with the keyword - '" + keyword + "'."
                    + "\n___________________________________________________________\n";
        } else {
            outputMsg = "___________________________________________________________"
                    + "\n (⊙ ‿ ⊙)"
                    + "\n You have " + matchedTasks.size() + " tasks matched with keyword - '"
                    + keyword + "'."
                    + "\n Here they are:";
            for (int i = 0; i < matchedTasks.size(); i++) {
                outputMsg += "\n      " + (i + 1) + ". " + matchedTasks.get(i);
            }
            outputMsg += "\n___________________________________________________________\n";
        }

        System.out.println(outputMsg);
        return outputMsg;
    }
}
