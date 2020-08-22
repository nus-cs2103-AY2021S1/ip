import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException {
        List<Task> taskArr = Storage.parseFileContents();
        Bot.initialize(taskArr);
    }
}
