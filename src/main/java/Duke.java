import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.*;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        boolean isFinished = false;

        // TODO: 20/8/20 Improve runtime: keep an internal counter of task (for done.*)
        Ui.initialMessage();
        while (!isFinished) {
            try {
                String echo = sc.nextLine();
                Command command = Parser.parseCommand(echo, tasks);
                command.execute(tasks);
                isFinished = command.setIsFinished();
            } catch (DukeException | ParseException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
