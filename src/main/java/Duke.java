import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    static TaskList taskList = new TaskList();
    static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public static void main(String[] args) {
        try {
            taskList = Storage.loadFromMem();
        } catch (DukeException e) {
            Ui.printWithLines(e.toString() + "\n");
        }
        Ui.processInput(taskList);
    }

}