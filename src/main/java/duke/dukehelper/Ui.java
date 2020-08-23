package duke.dukehelper;

import java.util.ArrayList;
import duke.task.Task;
public class Ui {
    public static void printDialog(String content) {
        System.out.println("    ----------------------------------------");
        System.out.println("    " + content + "\n");
        System.out.println("    ----------------------------------------");
    }
    public static void printStoredTasks(ArrayList<Task> taskStorage) {
        String result = "Here are the tasks in your list:\n    ";
        for(int i= 0; i < taskStorage.size(); i++) {
            result += ((i+1) + "." + taskStorage.get(i).returnStringForm());
            if(i < taskStorage.size() - 1) result += "\n    ";
        }
        printDialog(result);
    }
}
