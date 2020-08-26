package duke;

import duke.task.Task;

public class Ui {


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

    public static void tasks() {
        System.out.println("        Here are the tasks in your list:");
    }

    public static void errorEncounter(Exception e) {
        System.out.println("------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------");
    }

    public static void finsih() {
        System.out.println("        Bye have a good day!");
    }

    public static void deleteMessage(String deleted, int listSize){
        System.out.println("        Noted I've removed this task");
        System.out.println("        " + deleted);
        System.out.println("        you now have " + listSize + " tasks on the list");
    }

    public static void addedTaskMessage(Task task, int listSize) {
        System.out.println("        Got it I have added this task:");
        System.out.println("        " + task.toString());
        System.out.println("        you now have " + listSize + " tasks on the list");
    }

    public static void doneMessage(boolean isDone, String description) {
        System.out.println("        I have marked this as done:");
        System.out.println("        [" + isDone + "] " + description);
    }
}
