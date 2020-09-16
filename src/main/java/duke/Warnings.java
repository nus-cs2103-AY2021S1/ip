package duke;

import java.io.IOException;

/**
 * Display different kinds of warning messages when error occurs.
 */
public class Warnings extends Ui {
    /**
     * Displays and returns warning message when invalid done task index is given.
     *
     * @param tasksSize Size of the user's task list.
     * @return System warning message.
     */
    public static String getInvalidDoneTaskIndexMsg(int tasksSize) {
        assert tasksSize >= 0 : "Task size should not be less than 0.";
        String warningMsg = " [• ▂ •]What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'done <Task_Index (Start from 1)>'."
                + "\n\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when invalid ToDoTask input entered.
     *
     * @return System warning message.
     */
    public static String getInvalidToDoMsg() {
        String warningMsg = " [• ▂ •]What? "
                + "\n OOPS!!! The description of a todo cannot be empty."
                + "\n And make sure you follow this format - 'todo <task name>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when invalid DeadlineTask input entered.
     *
     * @return System warning message.
     */
    public static String getInvalidDeadlineMsg() {
        String warningMsg = " [• ▂ •]What? "
                + "\n OOPS!!! The description or/and deadline of a deadline task cannot be empty."
                + "\n And make sure you follow this format - 'deadline <task name> /by "
                + "<task deadline - 'yyyy-MM-dd HH:mm'>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when invalid EventTask input entered.
     *
     * @return System warning message.
     */
    public static String getInvalidEventMsg() {
        String warningMsg = " [• ▂ •]What? "
                + "\n OOPS!!! The description or/and event timing of a event task cannot be empty."
                + "\n And make sure you follow this format - 'event <event name> "
                + "/at <event timing - 'yyyy-MM-dd HH:mm'>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when Parser cannot recognize the input.
     *
     * @return System warning message.
     */
    public static String getInvalidInputMsg() {
        String warningMsg = " [• ▂ •]What? "
                + "\n OOPS!!! I'm sorry, but I don't know what that means."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when invalid delete input entered.
     *
     * @param tasksSize Size of the user's task list.
     * @return System warning message.
     */
    public static String getInvalidDeleteMsg(int tasksSize) {
        assert tasksSize >= 0 : "Task size should not be less than 0.";
        String warningMsg = " [• ▂ •]What? "
                + "\n I couldn't find this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'delete <Task_Index (Start from 1)>'."
                + "\n\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when error occurs while loading data file.
     *
     * @param e Exception message.
     * @return System warning message.
     */
    public static String getInvalidFileInputMsg(IOException e) {
        String warningMsg1 = " [• ▂ •]What? "
                + "\n Invalid data inside data/taskList.txt file..."
                + "\n\n Details:";
        System.out.println(warningMsg1);
        System.err.println(e);
        String warningMsg = warningMsg1 + "\n" + e;
        return warningMsg;
    }

    /**
     * Displays and returns warning message when error occurs while saving into data file.
     *
     * @param e Exception message.
     * @return System warning message.
     */
    public static String getInvalidFileOutputMsg(IOException e) {
        String warningMsg1 = " [• ▂ •]What? "
                + "\n Errors occurred when try to write data into data/taskList.txt file..."
                + "\n\n Details:";
        System.out.println(warningMsg1);
        System.err.println(e);

        String warningMsg = warningMsg1 + "\n" + e;
        return warningMsg;
    }

    /**
     * Displays and returns warning message when error occurs while try to archive a task.
     *
     * @param tasksSize Number of tasks in active TaskList.
     * @return System warning message.
     */
    public static String getInvalidArchiveMsg(int tasksSize) {
        String warningMsg = " [• ▂ •]What? "
                + "\n I couldn't archive this task, please re-enter a valid task index!"
                + "\n And make sure you follow this format - 'archive <Task_Index (Start from 1)>'."
                + "\n\n You have " + tasksSize + " tasks in total."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when error occurs while try to find tasks.
     *
     * @return System warning message.
     */
    public static String getInvalidFindMsg() {
        String warningMsg = " [• ▂ •]What? "
                + "\n Please make sure you follow this format - 'find <Keyword>'."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }

    /**
     * Displays and returns warning message when error occurs while use enters a wrong date-time format.
     *
     * @return System warning message.
     */
    public static String getInvalidDateMsg() {
        String warningMsg = " [• ▂ •]What? "
                + "\n Please make sure you follow this format when enter date - 'yyyy-MM-dd HH:mm'."
                + "\n\n **Type 'help' to see what I can do. |^_^|";
        System.out.println(warningMsg);
        return warningMsg;
    }
}
