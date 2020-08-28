import duke.Command;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;



public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        // Storage storage = new Storage();
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
