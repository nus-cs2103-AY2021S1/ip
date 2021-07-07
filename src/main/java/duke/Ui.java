package duke;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The UI class to deal with interactions with the user, regarding messages displayed.
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
        System.out.println("If you're feeling lost try type 'help'");
    }

    /**
     * Displays the introduction to the user indicating that Duke is running for GUI.
     * @return the introduction.
     */
    public static String intro(boolean isChat) {
        String str1 = "Hi! I'm BrawlyDuke!\n";
        String str2 = "I am your personal assistant cowboy here to keep track of tasks\n";
        String str3 = "What can i do for you?\n";
        String str4 = "The 3 main features you can keep track of are todo, deadline, task \n";
        String str5 = "If you're feeling lost try type 'help'";
        return str1 + str2 + str3 + str4 + str5;
    }
    /**
     * Displays an indicator that the list is about to be shown.
     */
    public static void tasks(TaskList list) {
        System.out.println("        Here are the tasks in your list:");
        int counter = 1;
        for (Task t : list.getList()) {
            System.out.println("        " + counter + "." + t.toString());
            counter++;
        }
    }

    /**
     * Displays an indicator that the list is about to be shown for GUI.
     * @return the task added.
     */
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

    /**
     * Displays a goodbye message for GUI.
     * @return the goodbye message.
     */
    public static String finish(boolean isChat) {
        String str1 = "Bye have a good day! \n";
        String str2 = "application will close soon";
        return str1 + str2;
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

    /**
     * Displays to the user the task that has been deleted for GUI.
     * @param deleted the deleted task
     * @param listSize the size of the list
     */
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

    /**
     * Displays to the user the task that has been added for GUI.
     * @param task the added task
     * @param listSize the size of the list
     * @return the added task.
     */
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

    /**
     * Displays to the user the task that has been completed.
     * @param isDone indicating that the task is done
     * @param description the description of the task
     * @return the done message.
     */
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

    /**
     * Displays to the user a list of all the tasks which watch the given phrase.
     * @param list a new condensed list which match the phrase
     * @return the tasks found
     */
    public static String printFoundTask(TaskList list, boolean isChat) {
        String string1 = ("I have found these matching tasks in your list:");
        String fullList = "";
        for (Task t : list.getList()) {
            fullList += t.toString() + "\n";
        }
        return fullList;
    }

    /**
     * Prints instructions for the user on how to add different tasks.
     * @param helpTopic the specific topic the user needs help on
     */
    public static void printHelpInstructions(String helpTopic) {
        if (helpTopic.equals("deadline")) {
            System.out.println("For deadlines type 'deadline ' + 'your deadline' + '/by' + specific 'timing' ");
            System.out.println("in the format: YYYY-MM-DD");
        } else if (helpTopic.equals("event")) {
            System.out.println("For events type 'event ' + 'your event' + '/at' + specific 'location'");
        } else if (helpTopic.equals("todo")) {
            System.out.println("For todo type 'todo ' + 'your task'");
        } else {
            System.out.println("sorry this is not a valid help command");
        }
    }

    /**
     * Prints instructions for the user on how to add different tasks.
     * @param helpTopic the specific topic the user needs help on
     * @return the help instructions
     */
    public static String printHelpInstructions(String helpTopic, boolean isChat) {
        if (helpTopic.equals("deadline")) {
            String str1 = "For deadlines type 'deadline ' + 'your deadline' + '/by' + specific 'timing'\n";
            String str2 = "in the format: YYYY-MM-DD";
            return str1 + str2;
        } else if (helpTopic.equals("event")) {
            return "For events type 'event ' + 'your event' + '/at' + specific 'location'";
        } else if (helpTopic.equals("todo")) {
            return "For todo type 'todo ' + 'your task'";
        } else {
            return "sorry this is not a valid help command";
        }
    }

    /**
     * A general help method to show the user if they forget the commands
     */
    public static void generalHelp() {
        System.out.println("Here are all the available commands:");
        System.out.println("todo <description>");
        System.out.println("deadline <description> /by YYYY-MM-DD");
        System.out.println("event <description> /at <location>");
        System.out.println("list");
        System.out.println("delete <task Index>");
        System.out.println("done <task Index>");
        System.out.println("find <task key word>");
        System.out.println("bye");
    }

    /**
     * A general help method to show the user if they forget the commands
     */
    public static String generalHelp(boolean isChat) {
        String str1 = "- Here are all the available commands: \n";
        String str2 = "- todo <description> \n";
        String str3 = "- deadline <description> /by YYYY-MM-DD \n";
        String str4 = "- event <description> /at <location> \n";
        String str5 = "- list \n";
        String str6 = "- delete <task Index> \n";
        String str7 = "- done <task Index> \n";
        String str8 = "- find <task key word> \n";
        String str9 = "- bye \n";
        return str1 + str2 + str3 + str4 + str5 + str6 + str7 + str8 + str9;
    }

    /**
     * A message to be displayed to user if the enter an invalid command that BrawlyDuke does not recognise
     */
    public static void invalidCommandMessage() {
        System.out.println("I'm sorry, but I don't know what that means");
        System.out.println("perhaps you can try type 'help'");
    }

    /**
     * A message to be displayed to user if the enter an invalid command that BrawlyDuke does not recognise
     */
    public static String invalidCommandMessage(boolean isChat) {
        String str1 = "I'm sorry, but I don't know what that means \n";
        String str2 = "You can try type 'help'";
        return str1 + str2;
    }

    /**
     * A message to indicate that the user input an invalid index.
     * @return
     */
    public static String invalidIndexMessage() {
        return "Sorry the index is out of the range, please enter a valid index!";
    }

    /**
     * A message to indicate that the user input an invalid date not in the proper format.
     * @return
     */
    public static String invalidDateMessage() {
        String str1 = "Sorry the date entered was in the wrong format \n";
        String str2 = "Please try enter date in format YYYY-MM-DD";
        return str1 + str2;
    }
}
