package duke;

import java.util.ArrayList;

/**
 * Prints the relevant messages.
 */
public class Ui {
    /**
     * Prints and returns logo "Welcome" greeting.
     *
     * @return System reply message.
     */
    public static String getLogoMsg() {
        String logo = "|^_^|┛\nWELCOME to DukeBT!";
        System.out.println(logo);
        return logo;
    }

    /**
     * Prints and returns greeting.
     *
     * @return System reply message.
     */
    public static String getGreetingMsg() {
        String greetingMsg = " Hello! I'm DukeBT."
                + "\n What can I do for you?"
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(greetingMsg);
        return greetingMsg;
    }

    /**
     * Prints and returns help message to show all possible commands available.
     *
     * @return System reply message.
     */
    public static String getHelpMsg() {
        String commandList = " ┗( ⊙.⊙ )┛ "
                + "\n These are all the commands you may use:"
                + "\n     1.list -- show all tasks"
                + "\n     2.todo <task name> -- add a task"
                + "\n     3.deadline <task name> /by <task deadline - 'yyyy-MM-dd HH:mm'> "
                + "-- add a task with its deadline"
                + "\n     4.event <event name> /at <event timing - 'yyyy-MM-dd HH:mm'> "
                + "-- add an event with its timing"
                + "\n     5.done <task index> -- mark this task as done"
                + "\n     6.delete <task index> -- mark this task from task list"
                + "\n     7.find <keyword> -- find all tasks in the task list which contains the keyword"
                + "\n     8.archive <task index> -- archive this task"
                + "\n     9.archive all -- archive all tasks"
                + "\n     10.list archive -- show all archived tasks"
                + "\n     11.help -- show all commands"
                + "\n     12.bye -- exit the chatbot";
        System.out.println(commandList);
        return commandList;
    }

    /**
     * Prints and returns bye message to see goodbye to the user.
     *
     * @return System reply message.
     */
    public static String getByeMsg() {
        String byeMsg = " |^_^|┛"
                + "\n Bye. Hope to see you again soon!"
                + "\n Your task list has been saved."
                + "\n It will be automatically reloaded when you come back!";
        System.out.println(byeMsg);
        return byeMsg;
    }

    /**
     * Prints and returns confirmation message to show user
     * the deadline task has been added into the task list.
     *
     * @param tasks The overall user's task list.
     * @return System reply message.
     */
    public static String getAddingDeadlineTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = " (^.^)"
                + "\n Got it. I've added this Deadline task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns confirmation message to show user
     * the event task has been added into the task list.
     *
     * @param tasks The overall user's task list.
     * @return System reply message.
     */
    public static String getAddingEventTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = " (^.^)"
                + "\n Got it. I've added this Event task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns confirmation message to show user
     * the to-do task has been added into the task list.
     *
     * @param tasks The overall user's task list.
     * @return System reply message.
     */
    public static String getAddingTodoTaskMsg(ArrayList<Task> tasks) {
        String outputMsg = " (^.^)"
                + "\n Got it. I've added this ToDo task:"
                + "\n     " + tasks.size() + "." + tasks.get(tasks.size() - 1)
                + "\n Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns confirmation message to show user
     * the task task has been marked as done.
     *
     * @param index A series number of the task in the task list.
     * @param tasks The overall user's task list.
     * @return System reply message.
     */
    public static String getDoneTaskMsg(int index, ArrayList<Task> tasks) {
        String outputMsg = " (ﾉﾟ0ﾟ)ﾉ~"
                + "\n Congratulations from DukeBT! You have done 1 task!"
                + "\n The task below has been marked as done:"
                + "\n      Task #" + index + ". " + tasks.get(index - 1);
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns confirmation message to show user
     * the corresponding task has been deleted from the task list.
     *
     * @param index        A series number of the task in the task list.
     * @param taskToDelete Task to be deleted.
     * @param tasks        The overall user's task list.
     * @return System reply message.
     */
    public static String getDeleteTaskMsg(int index, Task taskToDelete, TaskList tasks) {
        String outputMsg = " (ಠ‿↼)"
                + "\n Noted. This task has been removed from your task list:"
                + "\n      Task #" + index + ". " + taskToDelete
                + "\n Now you have " + tasks.getTaskListSize() + " tasks in the list.";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns all tasks stored in the task list.
     *
     * @param tasks The overall user's task list.
     * @return System reply message.
     */
    public static String getAllTasksMsg(ArrayList<Task> tasks) {
        String outputMsg = "";
        if (tasks.isEmpty()) {
            outputMsg = " (⊙ ‿ ⊙)"
                    + "\n Task list is empty, please try to add some tasks first.";
            System.out.println(outputMsg);
            return outputMsg;
        }

        outputMsg = " (⊙ ‿ ⊙)"
                + "\n You have " + tasks.size() + " tasks in total."
                + "\n Here they are:";
        return getAllTasks(tasks, outputMsg);
    }

    /**
     * Prints and returns all archived tasks in archive task list.
     *
     * @param archivedTasks The archived task list.
     * @return System reply message.
     */
    public static String getAllArchivedTasksMsg(ArchivedTaskList archivedTasks) {
        String outputMsg = "";
        if (archivedTasks.getArchivedTaskList().isEmpty()) {
            outputMsg = " (⊙ ‿ ⊙)"
                    + "\n Archived task list is empty, there's no archived task yet."
                    + "\n";
            System.out.println(outputMsg);
            return outputMsg;
        }

        outputMsg = " (⊙ ‿ ⊙)"
                + "\n You have " + archivedTasks.getArchivedTaskList().size()
                + " archived tasks in total."
                + "\n Here they are:";
        System.out.println(outputMsg);
        return getAllTasks(archivedTasks.getArchivedTaskList(), outputMsg);
    }

    /**
     * Prints and returns confirmation message if the task has been archived.
     *
     * @param taskToArchive The task which is going to be archived.
     * @param archivedTasks The ArchivedTaskList of archived task list.
     * @return System reply message.
     */
    public static String getAddingArchiveTaskMsg(Task taskToArchive, ArchivedTaskList archivedTasks) {
        String outputMsg = " (ಠ‿↼)"
                + "\n Noted. This task has been archived from your task list:"
                + "\n      Task " + taskToArchive
                + "\n Now you have " + archivedTasks.getTaskListSize() + " tasks in the archived task list.";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns confirmation message if all task has been archived.
     *
     * @param archivedTasks The ArchivedTaskList of archived task list.
     * @return System reply message.
     */
    public static String getArchiveAllTaskMsg(ArchivedTaskList archivedTasks) {
        String outputMsg = " (ಠ‿↼)"
                + "\n Noted. All tasks have been archived."
                + "\n Now you have " + archivedTasks.getTaskListSize() + " tasks in the archived task list.";
        System.out.println(outputMsg);
        return outputMsg;
    }

    /**
     * Prints and returns out all tasks that match the keyword stored in the task list.
     *
     * @param keyword      A string of keyword to find the matching tasks.
     * @param matchedTasks An ArrayList of all the tasks that match with the keyword.
     * @return System reply message.
     */
    public static String getMatchingTasksMsg(String keyword, ArrayList<Task> matchedTasks) {
        String outputMsg = "";
        if (matchedTasks.isEmpty()) {
            outputMsg = " (⊙ ‿ ⊙)"
                    + "\n There is no matching task with the keyword - '" + keyword + "'.";
            System.out.println(outputMsg);
            return outputMsg;
        }

        outputMsg = " (⊙ ‿ ⊙)"
                + "\n You have " + matchedTasks.size() + " tasks matched with keyword - '"
                + keyword + "'."
                + "\n Here they are:";
        return getAllTasks(matchedTasks, outputMsg);
    }

    private static String getAllTasks(ArrayList<Task> taskList, String outputMsg) {
        for (int i = 0; i < taskList.size(); i++) {
            outputMsg += "\n      " + (i + 1) + ". " + taskList.get(i);
        }

        System.out.println(outputMsg);
        return outputMsg;
    }
}
