package duke;

import java.util.ArrayList;

/**
 * Represents a Ui object.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Displays welcome message.
     * 
     * @return Welcome message string.
     */
    public static String welcome() {
        StringBuilder response = new StringBuilder();
        String greeting = "Your Majesty, I am your loyal Duke.\n ";
        response.append(greeting);
        String instruction = "I offer a range of administrative services. Type 'assist' to see the comprehensive list.\n";
        response.append(instruction);
        return response.toString();
    }

    /**
     * Displays list of possible user commands.
     * 
     * @return List of commands.
     */
    public static String assist() {
        StringBuilder response = new StringBuilder();
        String greeting = "Greetings, Your Majesty. Use any of these commands to access my quality services:\n";
        response.append(greeting);
        String todoHelp = "1. todo [TASK]: Adds a todo to your scroll\n";
        String deadlineHelp = "2. deadline [TASK] /by [DATE AND/OR TIME]: Adds a deadline to your scroll\n";
        String eventHelp = "3. event [TASK] /on [DATE AND/OR TIME]: Adds an event to your scroll\n";
        String scrollHelp = "4. scroll: Displays your scroll - your list of tasks\n";
        String conquerHelp = "5. conquer [NUMBER]: Marks the particular item on your scroll as DONE\n";
        String deleteHelp = "6. delete [NUMBER]: Deletes the particular item from your scroll\n";
        String findHelp = "7. find [KEYWORD]: Returns a list of relevant items on your scroll\n";
        String scheduleHelp = "8. schedule [DATE]: Returns a list of items on this date\n";
        String assistHelp = "9. assist: Displays a list of available commands.\n";
        String dismissHelp = "10. dismiss: This will be my cue to leave.\n";
        String ending = "Now, how may I serve you?\n";
        
        response.append(todoHelp);
        response.append(deadlineHelp);
        response.append(eventHelp);
        response.append(scrollHelp);
        response.append(conquerHelp);
        response.append(deleteHelp);
        response.append(findHelp);
        response.append(scheduleHelp);
        response.append(assistHelp);
        response.append(dismissHelp);
        
        response.append(ending);
        return response.toString();
    }

    /**
     * Displays goodbye message.
     * 
     * @return Goodbye message.
     */
    public static String dismiss() {
        return "Your wish is my command, Your Majesty. Till I see you again. \n";
    }

    /**
     * Displays error message.
     * 
     * @param e DukeException.
     * @return Error message.         
     */
    public static String dukeErrorMessage(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Displays marked task as done message.
     * 
     * @param t Task that has been marked as done.
     * @return Marked as done message.
     */
    public static String conqueredMessage(Task t) {
        assert (t != null);
        String response = "As you wish, Your Majesty. I have marked this as conquered.\n";
        String conqueredTask = t.toString() + "\n";
        return response + conqueredTask;
    }

    /**
     * Displays deleted task message.
     * 
     * @param t Task that has been deleted.
     * @param size Size of current list of stored tasks.
     * @return Deleted task message.
     */
    public static String deletedMessage(Task t, int size) {
        assert (t != null);
        String response = "As you wish, Your Majesty. I have removed this writing.\n";
        String deletedTask = t.toString() + "\n";
        String information = String.format("You have %s writing(s) on your scroll as of now. \n", size);
        return response + deletedTask + information;
    }

    /**
     * Displays added task message.
     * 
     * @param t Task that has been added.
     * @param size Size of current list of stored tasks.
     * @return Added task message.
     */
    public static String addedMessage(Task t, int size) {
        assert (t != null);
        String response = "Your Majesty, I've added the writing:\n";
        String addedTask = t.toString() + "\n";
        String information = String.format("You have %s writing(s) on your scroll as of now. \n", size);
        return response + addedTask + information;
    }

    /**
     * Prints a list of tasks.
     * 
     * @param storedTasks List of tasks to be printed.
     * @return Printed list of tasks.
     */
    public static String printListOfTasksUi(ArrayList<Task> storedTasks) {
        assert (storedTasks != null);
        if (storedTasks.size() == 0) {
            return "I have come up on empty, Your Majesty.";
        } 
        StringBuilder response = new StringBuilder();
        response.append("As requested, Your Majesty:\n");
        for (Task task : storedTasks) {
            response.append(String.format("%s.%s\n", storedTasks.indexOf(task) + 1, task));
        }
        return response.toString();
    }

    /**
     * Prints a list of tasks.
     *
     * @return Invalid command message.
     */
    public static String printInvalidCommmandErrorMessage() {
        return "I'm afraid I don't understand that command, Your Majesty.";
    }

}
