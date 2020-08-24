package duke.ui;

import duke.tasks.Task;

public class Messenger {
    /**
     * Messages for loading files.
     */
    public static final String FILE_LOADING = "Welcome back! Trying to retrieve where you were last time...";
    public static final String FILE_LOADED = "Great! We have successfully loaded the data. Enjoy~";

    /**
     * Error handling messages
      */
    public static final String INDEX_FORMAT_ERROR = "☹ OOPS!!! Seems you have provided an invalid index :-(";
    public static final String COMMAND_UNRECOGNIZABLE_ERROR = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EMPTY_COMMAND_ERROR = "☹ OOPS!!! The command cannot be empty!";
    public static final String SPELL_ERROR = "☹ OOPS!!! Check if you have spelled correctly!";
    public static final String EMPTY_CONTENT_ERROR = "☹ OOPS!!! Seems you forgot to supply the main content!";
    public static final String EMPTY_TIME_ERROR = "☹ OOPS!!! Seems you forgot to supply the time!\n" +
            "Simply add '/by <time>' for deadline OR '/at <time>' for event behind your command";
    public static final String INDEX_OUT_OF_BOUND_ERROR = "☹ OOPS!!! Seems the index you provided is not in the list.";
    public static final String FILE_NOT_FOUND = "Couldn't find a save file. You may start adding things from start!";
    public static final String DIRECTORY_NOT_FOUND = "Couldn't find the data directory. Creating one for you...";
    public static final String DATE_FORMAT_ERROR = "☹ OOPS!!! The format for your date is incorrect. Use yyyy-MM-dd instead";

    public static String emptyDescriptionError(String command) {
        return String.format("☹ OOPS!!! The description of a %s cannot be empty.", command);
    }

    /**
     * Opening and closing messages
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public static void close() {
        System.out.println("Content Saved! Hope to see you again soon!");
    }

    /**
     * Operations messages
     */
    public static void markAsDoneMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public static void addTaskMessage(Task newTask, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask.toString());
        System.out.println(String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task")));
    }

    public static void deleteTaskMessage(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %s %s in the list.", size, (size > 1 ? "tasks" : "task")));
    }
}
