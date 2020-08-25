package luoyi.duke.ui;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.task.TaskList;

/**
 * Ui class to encapsulate output operations.
 */
public class Ui {
    /**
     * Prints greeting message.
     */
    public static void greet() {
        System.out.print(TextFormatter.LOGO);
        System.out.print(TextFormatter
                .getFormattedText(Message.WELCOME.toString()));
    }

    /**
     * Prints farewell message.
     */
    public static void bye() {
        System.out.print(TextFormatter
                .getFormattedText(Message.FAREWELL.toString()));
    }

    /**
     * Prints a list of tasks.
     *
     * @param list List of task.
     */
    public static void displayTasks(TaskList list) {
        if (list.size() == 0) {
            System.out.print(TextFormatter.getFormattedText(
                    Message.ERR_NO_TASK_IN_LIST.toString()));
        } else {
            StringBuilder sb = new StringBuilder(Message.LIST.toString());
            for (int i = 0; i < list.size(); i++) {
                sb.append(" ").append(i + 1).append(". ")
                        .append(list.get(i).toString()).append("\n");
            }
            System.out.print(TextFormatter.getFormattedText(sb.toString()));
        }
    }

    /**
     * Display a list of tasks based a list of indexes.
     *
     * @param list List of tasks.
     * @param listIndex List of task indexes.
     * @param date Date which was used to filter the list.
     */
    public static void displayTasks(TaskList list, int[] listIndex, String date) {
        if (listIndex.length == 0) {
            System.out.print(TextFormatter.getFormattedText(
                    Message.ERR_NO_MATCHING_TASK.toString()));
        } else {
            StringBuilder sb = new StringBuilder("Here are the task on " + date + ":\n");
            for (int index : listIndex) {
                sb.append(" ").append(index + 1).append(". ")
                        .append(list.get(index).toString()).append("\n");
            }
            System.out.print(TextFormatter.getFormattedText(sb.toString()));
        }
    }
}
