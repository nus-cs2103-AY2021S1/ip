import java.util.List;

public class Duke {

    public static void main(String[] args) throws DukeException {
        List<Task> taskArr = Storage.parseFileContents();
        Ui.initialize(taskArr);
    }
}
