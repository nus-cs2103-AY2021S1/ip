import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    static TaskList taskList = new TaskList();
    static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public static void main(String[] args) {
        Ui.printWithLines("The time now is: " + LocalDateTime.now().format(dateTimeFormat) + "\n");
        Ui.processInput(taskList);
    }

}