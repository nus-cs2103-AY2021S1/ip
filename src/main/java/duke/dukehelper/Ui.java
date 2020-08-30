package duke.dukehelper;

import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public static String printDialog(String content) {
        String preLine = "----------------------------------------\n";
        String main = content + "\n";
        String subLine = "----------------------------------------\n";
        return preLine + main + subLine;
    }

    /**
     * Prints all tasks
     * @param taskStorage
     */
    public static String printStoredTasks(ArrayList<Task> taskStorage) {
        String result = "Here are the tasks in your list:\n    ";
        for(int i = 0; i < taskStorage.size(); i++) {
            result += ((i + 1) + "." + taskStorage.get(i).returnStringForm());
            if(i < taskStorage.size() - 1) result += "\n    ";
        }
        return printDialog(result);
    }
}
