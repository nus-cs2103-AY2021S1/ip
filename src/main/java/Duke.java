import java.time.format.DateTimeFormatter;

public class Duke {

    protected static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        try {
            taskList = Storage.loadFromMem();
        } catch (DukeException e) {
            Ui.printWithLines(e.toString() + "\n");
        }
        Ui.processInput(taskList);
    }

}
