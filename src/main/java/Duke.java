import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        Ui.processInput(taskList);
    }

}