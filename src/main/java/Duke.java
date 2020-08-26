import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        try {
            taskList = Storage.loadFromMem();
        } catch (DukeException e) {
            Ui.printWithLines(e.toString());
        }
        Ui.processInput(taskList);
    }

}