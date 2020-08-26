package duke;

import java.io.IOException;

/**
 *
 */
public class Warnings extends Ui {
    /**
     *
     * @param tasksSize
     */
    public static void invalidDoneTaskIndex(int tasksSize) {
        String warningMsg = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'done <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    /**
     *
     */
    public static void invalidToDo() {
        String warningMsg = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! The description of a todo cannot be empty."
                + "\n And make sure you follow this format - 'todo <task name>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    /**
     *
     */
    public static void invalidDeadline() {
        String warningMsg = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! The description or/and deadline of a deadline task cannot be empty."
                + "\n And make sure you follow this format - 'deadline <task name> /by <task deadline>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    /**
     *
     */
    public static void invalidEvent() {
        String warningMsg = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! The description or/and event timing of a event task cannot be empty."
                + "\n And make sure you follow this format - 'event <event name> /at <event timing>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    /**
     *
     */
    public static void invalidInput() {
        String warningMsg = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n OOPS!!! I'm sorry, but I don't know what that means."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    /**
     *
     * @param tasksSize
     */
    public static void invalidDelete(int tasksSize) {
        String warningMsg = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'delete <Task_Index>'."
                + "\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|"
                + "\n################################################################";
        System.out.println(warningMsg);
    }

    /**
     *
     * @param e
     */
    public static void invalidFileInput(IOException e) {
        String warningMsg1 = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n Invalid data inside data/taskList.txt file..."
                + "\n Details:";
        System.out.println(warningMsg1);
        System.err.println(e);
        String warningMsg2 = "\n################################################################";
        System.out.println(warningMsg2);
    }

    /**
     *
     * @param e
     */
    public static void invalidFileOutput(IOException e) {
        String warningMsg1 = "################################################################"
                + "\n [• ▂ •]What? "
                + "\n Errors occurred when try to write data into data/taskList.txt file..."
                + "\n Details:";
        System.out.println(warningMsg1);
        System.err.println(e);
        String warningMsg2 = "\n################################################################";
        System.out.println(warningMsg2);
    }
}
