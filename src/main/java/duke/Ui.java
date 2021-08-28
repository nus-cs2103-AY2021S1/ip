package duke;

import java.util.ArrayList;

/**
 * Ui class handles all the interactions with user of the Duke
 * chatbot. Ui object will print directly to the output.
 */
public class Ui {

    private final String SERVANT_SPEAK = "Duke:\n";
    private final String USER_PROMPT = "Your Command Sire:";

    /**
     * Returns an indicator that the following messages
     * are from Duke.
     *
     * @return String.
     */
    public String getServantSpeak() {
        return SERVANT_SPEAK;
    }

    /**
     * Returns a message to prompt the user for his/her input.
     *
     * @return String.
     */
    public String getUserPrompt() {
        return USER_PROMPT;
    }

    /**
     * Prints a welcome message every time the chat bot is activated.
     *
     * @return String.
     */
    public String welcomeMessage() {
        // Introduction at the beginning of the chat
        String response = "Greetings my Liege.\n"
                + "Why have you summoned me?\n\n"
                + "You may type \"help\" for a list"
                + " of available commands.\n";
        return response;
    }

    /**
     * Prints all the available commands that the user can input.
     * This includes the format of the command so as to be accepted as a command.
     *
     * @return String.
     */
    public String availableCommands() {
        String response = "These are your available commands my Lord:\n\n"
                + "help - Show list of available commands\n"
                + "todo <Description of task>"
                + " - To add a normal to do task\n"
                + "deadline <Description of task>"
                + " /by <Date in YYYY-MM-DD> <Time in hh:mm>"
                + " - To add a task with a deadline\n"
                + "event <Description of event>"
                + " /at <Date in YYYY-MM-DD> <Time in hh:mm>"
                + " - To add an event\n"
                + "list all/todos/deadlines/events"
                + " - List all <task type> tasks\n"
                + "list all/todos/deadlines/events done/not done"
                + " - List <task type> tasks that are <done/not done>\n"
                + "find <keyword> - Find tasks based on keyword input\n"
                + "done <task number> - Mark task as done\n"
                + "delete <task number> - Delete task from list\n";
        return response;
    }

    /**
     * Prints a message to indicate that a task has been added to the task list.
     * Prints back to the console the task that was added to the task list.
     *
     * @param task task that was added to the task list.
     * @param size size of task list.
     * @return String.
     */
    public String taskAddedMessage(Task task, int size) {
        assert (size >= 0);
        String response = "As you wish Sire. I have added the task:\n       "
                + task.toString() + "\n"
                + "Now you have " + size
                + " tasks in the list.\n";
        return response;
    }

    /**
     * Prints a message to indicate that the task has been marked as done.
     * Prints back to the console the task that was marked as done.
     *
     * @param task task that was marked as done.
     * @return String.
     */
    public String markAsDoneMessage(Task task) {
        String response = "As you wish Sire. I have marked this task as done:\n"
                + "       " + task.toString();
        return response;
    }

    /**
     * Prints a message to indicate that the task has been deleted.
     * Prints back to the console the task that was removed from the task list.
     *
     * @param task task that was removed from the task list.
     * @return String.
     */
    public String taskDeletedMessage(Task task) {
        String response = "As you wish Sire. I removed this task:\n"
                + "       " + task.toString();
        return response;
    }

    /**
     * Prints all the task in the task list.
     *
     * @param userTasks This is the task list to be printed.
     * @return String.
     */
    public String allTasksToString(ArrayList<Task> userTasks) {
        String response = "Here are your tasks your Majesty:\n"
                + new TaskList(userTasks).toString();
        return response;
    }

    /**
     * Prints all the tasks in the task list
     * that were marked as done.
     *
     * @param userTasksDone This is the list of tasks to be printed.
     * @return String.
     */
    public String allTasksDoneToString(ArrayList<Task> userTasksDone) {
        String response = "Here are all the tasks that you have"
                + " done your Majesty:\n"
                + new TaskList(userTasksDone).toString();
        return response;
    }

    /**
     * Prints all the tasks in the task list
     * that were not marked as done.
     *
     * @param userTasksNotDone This is the list of tasks to be printed.
     * @return String.
     */
    public String allTasksNotDoneToString(ArrayList<Task> userTasksNotDone) {
        String response = "Here are all the tasks that you have"
                + " not done your Majesty:\n"
                + new TaskList(userTasksNotDone).toString();
        return response;
    }

    /**
     * Prints all the to do tasks in the task list.
     *
     * @param userToDos This is the list of to do to do tasks to be printed.
     * @return String.
     */
    public String toDosToString(ArrayList<Task> userToDos) {
        String response = "Here are all your to do tasks your Majesty:\n"
                + new TaskList(userToDos).toString();
        return response;
    }

    /**
     * Prints all the to do tasks in the task list
     * that were marked as done.
     *
     * @param userToDosDone This is the list of tasks to be printed.
     * @return String.
     */
    public String toDosDoneToString(ArrayList<Task> userToDosDone) {
        String response = "Here are all the to do tasks that you have"
                + " done your Majesty:\n"
                + new TaskList(userToDosDone).toString();
        return response;
    }

    /**
     * Prints all the to do tasks in the task list
     * that were not marked as done.
     *
     * @param userToDosNotDone This is the list of tasks to be printed.
     * @return String.
     */
    public String toDosNotDoneToString(ArrayList<Task> userToDosNotDone) {
        String response = "Here are all the to do tasks that you have"
                + " not done your Majesty:\n"
                + new TaskList(userToDosNotDone).toString();
        return response;
    }

    /**
     * Prints all the deadlines in the task list.
     *
     * @param userDeadlines This is the list of deadlines to be printed.
     * @return String.
     */
    public String deadlinesToString(ArrayList<Task> userDeadlines) {
        String response = "Here are all your tasks with a deadline your Majesty:\n"
                + new TaskList(userDeadlines).toString();
        return response;
    }

    /**
     * Prints all the deadlines in the task list
     * that were marked as done.
     *
     * @param userDeadlinesDone This is the list of deadlines to be printed.
     * @return String.
     */
    public String deadlinesDoneToString(ArrayList<Task> userDeadlinesDone) {
        String response = "Here are all your tasks with a deadline that you have"
                + " done your Majesty:\n"
                + new TaskList(userDeadlinesDone).toString();
        return response;
    }

    /**
     * Prints all the deadlines in the task list
     * that were not marked as done.
     *
     * @param userDeadlinesNotDone This is the list of deadlines to be printed.
     * @return String.
     */
    public String deadlinesNotDoneToString(ArrayList<Task> userDeadlinesNotDone) {
        String response = "Here are all your tasks with a deadline that you have"
                + " not done your Majesty:\n"
                + new TaskList(userDeadlinesNotDone).toString();
        return response;
    }

    /**
     * Prints all the events in the task list.
     *
     * @param userEvents This is the list of events to be printed.
     * @return String.
     */
    public String eventsToString(ArrayList<Task> userEvents) {
        String response = "Here are all your events your Majesty:\n"
                + new TaskList(userEvents).toString();
        return response;
    }

    /**
     * Prints all the events in the task list
     * that were marked as done.
     *
     * @param userEventsDone This is the list of events to be printed.
     * @return String.
     */
    public String eventsDoneToString(ArrayList<Task> userEventsDone) {
        String response = "Here are all your events that you have"
                + " done your Majesty:\n"
                + new TaskList(userEventsDone).toString();
        return response;
    }

    /**
     * Prints all the events in the task list
     * that were not marked as done.
     *
     * @param userEventsNotDone This is the list of events to be printed.
     * @return String.
     */
    public String eventsNotDoneToString(ArrayList<Task> userEventsNotDone) {
        String response = "Here are all your events that you have"
                + " not done your Majesty:\n"
                + new TaskList(userEventsNotDone).toString();
        return response;
    }

    /**
     * Prints all the tasks in the task list
     * that have been filtered according to given keyword.
     *
     * @param filteredTasks This is the list of tasks to be printed.
     * @param keyword given keyword used by filter.
     * @return String.
     */
    public String filteredTasksByKeywordToString(ArrayList<Task> filteredTasks, String keyword) {
        String response = "Here are all the tasks containing "
                + keyword + " your Majesty:\n"
                + new TaskList(filteredTasks).toString();
        return response;
    }

    /**
     * Prints a message to the console when the user ends the chat with Duke.
     *
     * @return String.
     */
    public String byeMessage() {
        String response = "It was a pleasure to serve you my Liege.\n"
                + "Till next time.";
        return response;
    }

    /**
     * Prints out the error message generated by the DukeException object.
     *
     * @param ex This is the DukeException object created by the error.
     * @return String.
     */
    public String errorMessage(DukeException ex) {
        return ex.toString();
    }
}
