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
     */
    public static String welcome() {
        String footer= "Your Majesty, I am your loyal Duke.\n " +
                "I offer a range of administrative services. Do type 'assist' to see the comprehensive list.\n";
        return footer + printDashedLineBreak();
    }

    /**
     * Displays a dashed line break that is 32 dashes long.
     */
    public static String printDashedLineBreak() {
        String dashedLine = "- ";
        return dashedLine.repeat(33);
    }

    /**
     * Displays list of possible user commands.
     */
    public static String assist() {
        String greeting = "Greetings, Your Majesty.\n " +
                "Use any of these commands to access my quality services:\n";
        String commandList = "1. todo [TASK]: Adds a todo to your scroll\n" +
                    "2. deadline [TASK] /by [DATE AND/OR TIME]: Adds a deadline to your scroll\n" + 
                        "3. event [TASK] /on [DATE AND/OR TIME]: Adds an event to your scroll\n" +
                            "4. scroll: Displays your scroll - your list of tasks\n" +
                                "5. conquer [NUMBER]: Marks the particular item on your scroll as DONE\n" +
                                    "6. delete [NUMBER]: Deletes the particular item from your scroll\n" +
                                        "7. find [KEYWORD]: Returns a list of relevant items on your scroll\n" +
                                            "8. schedule [DATE]: Returns a list of items on this date\n" +
                                                "9. dismiss: This will be my cue to leave.\n";
        String ending = "Now, how may I serve you?\n";
        return greeting + commandList + ending;
    }

    /**
     * Displays goodbye message.
     */
    public static String dismiss() {
        return "Your wish is my command, Your Majesty. Till I see you again. \n";
    }

    /**
     * Displays error message.
     * @param e DukeException.
     */
    public static String dukeErrorMessage(DukeException e) {
        return e.getMessage() + "\n";
    }

    /**
     * Displays marked task as done message.
     * @param t Task that has been marked as done.
     */
    public static String conqueredMessage(Task t) {
        assert (t != null);
        String response = "As you wish, Your Majesty. I have marked this as conquered.\n";
        String conqueredTask = t.toString() + "\n";
        return response + conqueredTask;
    }

    /**
     * Displays deleted task message.
     * @param t Task that has been deleted.
     * @param size Size of current list of stored tasks.
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
     * @param t Task that has been added.
     * @param size Size of current list of stored tasks.
     */
    public static String addedMessage(Task t, int size) {
        assert (t != null);
        String response = "Your Majesty, I've added the writing:\n";
        String addedTask = t.toString() + "\n";
        String information = String.format("You have %s writing(s) on your scroll as of now. \n", size);
        return response + addedTask + information;
    }
    
    public static String printAllTasksUi(ArrayList<Task> storedTasks) {
        assert (storedTasks != null);
        if (storedTasks.size() == 0) {
            return "Your scroll is currently empty, Your Majesty.";
        } 
        StringBuilder response = new StringBuilder();
        response.append("Your scroll as requested, Your Majesty:\n");
        for (Task task : storedTasks) {
            response.append(String.format("%s.%s\n", storedTasks.indexOf(task) + 1, task));
        }
        return response.toString();
    }
    
    public static String printRelevantTasksUi(ArrayList<Task> relevantTasks) {
        assert (relevantTasks != null);
        if (relevantTasks.size() == 0) {
            return "There doesn't seem to be any relevant writings, Your Majesty.";
        }
        StringBuilder response = new StringBuilder();
        response.append("These are the relevant writings, Your Majesty:\n");
        for (Task task : relevantTasks) {
            response.append(String.format("%s.%s\n", relevantTasks.indexOf(task) + 1, task));
        }
        return response.toString();
    }
    
    public static String printWrongInputErrorMessage() {
        return "I'm afraid I don't understand that command, Your Majesty.";
    }
    
    public static String printScheduleForDate(ArrayList<Task> scheduledTasks) {
        assert (scheduledTasks != null);
        if (scheduledTasks.size() == 0) {
            return "You have no writings for this date, Your Majesty.";
        }
        StringBuilder response = new StringBuilder();
        response.append("Here are the writings you requested, Your Majesty:\n");
        for (Task task : scheduledTasks) {
            response.append(String.format("%s.%s\n", scheduledTasks.indexOf(task) + 1, task));
        }
        return response.toString();
    }
}
