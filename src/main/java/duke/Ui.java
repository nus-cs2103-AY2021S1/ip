package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The main class to deal with interactions with the user.
 */
public class Ui {

    /**
     * Displays the introduction to the user indicating that Duke is running.
     */
    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! I'm duke.Duke your friendly neighbourhood chat bot");
        System.out.println("What can i do for you?");
    }

    public static String intro(boolean isChat) {
        String str1 = "Hello from\n";
        String str2 = "Hi! I'm duke.Duke your friendly neighbourhood chat bot\n";
        String str3 = "What can i do for you?";
        return  str1 + str2 + str3;
    }
    /**
     * Displays an indicator that the list is about to be shown
     */
    public static void tasks(TaskList list) {
        System.out.println("        Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list.getList()) {
            System.out.println("        " + counter + "." + t.toString());
            counter++;
        }
    }

    public static String tasks(TaskList list, boolean isChat) {
        String string1 = "Here are the tasks in your list:\n";
        String string2 = "";
        int counter = 1;
        for (Task t : list.getList()) {
            string2 += counter + "." + t.toString() + "\n";
            counter++;
        }
        return string1 + string2;
    }

    /**
     * Displays the error that was encountered as well as 2 lines of separation.
     * @param e
     */
    public static void errorEncounter(Exception e) {
        System.out.println("------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------");
    }


    /**
     * Displays a goodbye message.
     */
    public static void finish() {
        System.out.println("        Bye have a good day!");
    }

    public static String finish(boolean isChat) {
        return "Bye have a good day!";
    }

    /**
     * Displays to the user the task that has been deleted.
     * @param deleted the deleted task
     * @param listSize the size of the list
     */
    public static void deleteMessage(String deleted, int listSize) {
        System.out.println("        Noted I've removed this task");
        System.out.println("        " + deleted);
        System.out.println("        you now have " + listSize + " tasks on the list");
    }

    public static String deleteMessage(String deleted, int listSize, boolean isChat) {
        String string1 = "\nNoted I've removed this task\n";
        String string2 = deleted + "\n";
        String string3 = "you now have " + listSize + " tasks on the list";
        return string1 + string2 + string3;
    }

    /**
     * Displays to the user the task that has been added.
     * @param task the added task
     * @param listSize the size of the list
     */
    public static void addedTaskMessage(Task task, int listSize) {
        System.out.println("        Got it I have added this task:");
        System.out.println("        " + task.toString());
        System.out.println("        you now have " + listSize + " tasks on the list");
    }

    public static String addedTaskMessage(Task task, int listSize, boolean isChat) {
        String string1 = "\nGot it I have added this task:\n";
        String string2 = task.toString() + "\n";
        String string3 = "you now have " + listSize + " tasks on the list";
        return string1 + string2 + string3;
    }

    /**
     * Displays to the user the task that has been completed.
     * @param isDone indicating that the task is done
     * @param description the description of the task
     */
    public static void doneMessage(boolean isDone, String description) {
        System.out.println("        I have marked this as done:");
        System.out.println("        [" + isDone + "] " + description);
    }

    public static String doneMessage(boolean isDone, String description, boolean isChat) {
        String string1 = "\nI have marked this as done:\n";
        String string2 = "[" + isDone + "] " + description + "\n";
        return string1 + string2;
    }

    /**
     * Displays to the user a list of all the tasks which watch the given phrase.
     * @param list a new condensed list which match the phrase
     */
    public static void printFoundTask(TaskList list) {
        System.out.println("        I have found these matching tasks in your list:");
        for (Task t : list.getList()) {
            System.out.println("        " + t.toString());
        }
    }

    public static String printFoundTask(TaskList list, boolean isChat) {
        String string1 = ("I have found these matching tasks in your list:");
        String fullList = "";
        for (Task t : list.getList()) {
            fullList += t.toString() + "\n";
        }
        return fullList;
    }

    public static void printHelpInstructions(String helpTopic) {
        if (helpTopic.equals("deadline")) {
            System.out.println("For deadlines type 'deadline ' + 'your deadline' + '/by' + specific 'timing' ");
            System.out.println("in the format: YYYY-MM-DD");
        } else if (helpTopic.equals("event")) {
            System.out.println("For events type 'event ' + 'your event' + '/at' + specific 'location'");
        }else if (helpTopic.equals("todo")) {
            System.out.println("For todo type 'todo ' + 'your task'");
        } else {
            System.out.println("sorry this is not a valid help command");
        }
    }

    public String printHelpInstructions(String helpTopic, boolean isChat) {
        if (helpTopic.equals("deadline")) {
            String str1 = "For deadlines type 'deadline ' + 'your deadline' + '/by' + specific 'timing'\n";
            String str2 = "in the format: YYYY-MM-DD";
            return str1 + str2;
        } else if (helpTopic.equals("event")) {
            return "For events type 'event ' + 'your event' + '/at' + specific 'location'";
        }else if (helpTopic.equals("todo")) {
            return "For todo type 'todo ' + 'your task'";
        } else {
            return "sorry this is not a valid help command";
        }
    }
}
