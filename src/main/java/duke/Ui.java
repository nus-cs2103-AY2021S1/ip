package duke;

import java.util.ArrayList;

public class Ui {
    
    public Ui() {
    }
    
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\n Always at your service, \n" + logo + "\n");
        System.out.println("Your Majesty, I am your loyal Duke.");
        System.out.println("I offer a range of administrative services. Do type 'assist' to see the comprehensive list.");
        System.out.println();
    }
    
    public static void dashedLineBreak() {
        String dashedLine = "- ";
        System.out.println(dashedLine.repeat(32));
    }

    public static void assist() {
        dashedLineBreak();
        System.out.println("Greetings, Your Majesty. \n");
        System.out.println("Use any of the commands on the left to access my quality services:");
        System.out.println("\ttodo [TASK]: Adds a todo to your scroll");
        System.out.println("\tdeadline [TASK] /by [DATE AND/OR TIME]: Adds a deadline to your scroll");
        System.out.println("\tevent [TASK] /on [DATE AND/OR TIME]: Adds an event to your scroll");
        System.out.println("\tscroll: Displays your scroll - your list of tasks");
        System.out.println("\tconquer [NUMBER]: Marks the particular item on your scroll as DONE");
        System.out.println("\tdelete [NUMBER]: Deletes the particular item from your scroll");
        System.out.println("\tfind [KEYWORD]: Returns a list of relevant items on your scroll");
        System.out.println("\tdismiss: This will be my cue to leave.");
        System.out.println();
        System.out.println("Now, how may I serve you?");
        dashedLineBreak();
        System.out.println();
    }
    
    public static void dismiss() {
        dashedLineBreak();
        System.out.println("Your wish is my command, Your Majesty. Till I see you again. \n");
        System.exit(0);
    }
    
    public static void dukeErrorMessage(DukeException e) {
        System.out.println(e.getMessage());
        dashedLineBreak();
        System.out.println();
    }
    
    public static void conqueredMessage(Task t) {
        dashedLineBreak();
        System.out.println("As you wish, Your Majesty. I have marked this as conquered.");
        System.out.println("\t" + t);
        dashedLineBreak();
        System.out.println();
    }
    
    public static void deletedMessage(Task t, int size) {
        dashedLineBreak();
        System.out.println("As you wish, Your Majesty. I have removed this writing.");
        System.out.println("\t" + t);
        System.out.printf("You have %s writing(s) on your scroll as of now. \n", size);
        dashedLineBreak();
        System.out.println();
    }
    
    public static void addedMessage(Task t, int size) {
        dashedLineBreak();
        System.out.println("Your Majesty, I've added the writing:");
        System.out.println("\t" + t);
        System.out.printf("You have %s writing(s) on your scroll as of now. \n",size);
        dashedLineBreak();
        System.out.println();
    }
    
    public static void printAllTasksUi(ArrayList<Task> storedTasks) {
        if (storedTasks.size() == 0) {
            System.out.println("Your scroll is currently empty, Your Majesty.");
        } else {
            dashedLineBreak();
            System.out.println("Your current scroll, Your Majesty:");
            for (Task task : storedTasks) {
                System.out.printf("\t%s.%s", storedTasks.indexOf(task) + 1, task);
                System.out.println();
            }
        }
        dashedLineBreak();
        System.out.println();
    }

    public static void printRelevantTasksUi(ArrayList<Task> storedTasks) {
        if (storedTasks.size() == 0) {
            System.out.println("There doesn't seem to be any relevant writings, Your Majesty.");
        } else {
            dashedLineBreak();
            System.out.println("Here are the relevant writings, Your Majesty:");
            for (Task task : storedTasks) {
                System.out.printf("\t%s.%s", storedTasks.indexOf(task) + 1, task);
                System.out.println();
            }
        }
        dashedLineBreak();
        System.out.println();
    }
}
