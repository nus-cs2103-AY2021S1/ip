package luoyi.duke.ui;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.task.TaskList;

public class Ui {
    public static void greet() {
        System.out.print(TextFormatter.LOGO);
        System.out.print(TextFormatter
                .getFormattedText(Message.WELCOME.toString()));
    }

    public static void bye() {
        System.out.print(TextFormatter
                .getFormattedText(Message.FAREWELL.toString()));
    }

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
