package duke;

import java.io.IOException;

/**
 * Displays different kinds of warning messages when error occurs.
 */
public class Warnings extends Ui {
    private static final String DIVIDER_WARNING = "###########################";
    /**
     * Display warning message when invalid done task index is given.
     *
     * @param tasksSize Size of the user's task list.
     */
    public static String invalidDoneTaskIndex(int tasksSize) {
        String warningMsg = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'done <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n **Type 'help' to see what I can do. |^_^|"
                + "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Display warning message when invalid ToDoTask input entered.
     */
    public static String invalidToDo() {
        String warningMsg = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! The description of a todo cannot be empty."
                + "\n And make sure you follow this format - 'todo <task name>'."
                + "\n **Type 'help' to see what I can do. |^_^|"
                + "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Display warning message when invalid DeadlineTask input entered.
     */
    public static String invalidDeadline() {
        String warningMsg = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! The description or/and deadline of a deadline task cannot be empty."
                + "\n And make sure you follow this format - 'deadline <task name> /by <task deadline>'."
                + "\n **Type 'help' to see what I can do. |^_^|"
                + "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Display warning message when invalid EventTask input entered.
     */
    public static String invalidEvent() {
        String warningMsg = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! The description or/and event timing of a event task cannot be empty."
                + "\n And make sure you follow this format - 'event <event name> /at <event timing>'."
                + "\n **Type 'help' to see what I can do. |^_^|"
                + "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Display warning message when Parser cannot recognize the input.
     */
    public static String invalidInput() {
        String warningMsg = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! I'm sorry, but I don't know what that means."
                + "\n **Type 'help' to see what I can do. |^_^|"
                + "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Display warning message when invalid delete input entered.
     *
     * @param tasksSize Size of the user's task list.
     */
    public static String invalidDelete(int tasksSize) {
        String warningMsg = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'delete <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n **Type 'help' to see what I can do. |^_^|"
                + "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Display warning message when error occurs while loading data file.
     *
     * @param e Exception message.
     */
    public static String invalidFileInput(IOException e) {
        String warningMsg1 = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n Invalid data inside data/taskList.txt file..."
                + "\n Details:";
        System.out.println(warningMsg1);
        System.err.println(e);
        String warningMsg2 = "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg2);
        String warningMsg = warningMsg1 + "\n" + e + warningMsg2;
        return warningMsg;
    }

    /**
     * Display warning message when error occurs while saving into data file.
     *
     * @param e Exception message.
     */
    public static String invalidFileOutput(IOException e) {
        String warningMsg1 = DIVIDER_WARNING
                + "\n [• ▂ •]What? "
                + "\n Errors occurred when try to write data into data/taskList.txt file..."
                + "\n Details:";
        System.out.println(warningMsg1);
        System.err.println(e);
        String warningMsg2 = "\n" + DIVIDER_WARNING + "\n";
        System.out.println(warningMsg2);

        String warningMsg = warningMsg1 + "\n" + e + warningMsg2;
        return warningMsg;
    }

}
