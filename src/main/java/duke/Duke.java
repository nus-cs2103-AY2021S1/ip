package duke;
import duke.command.Command;
import duke.task.TaskList;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the Duke chat-bot.
 * Duke is a chat application which helps to keep track of tasks in an interactive manner.
 * @author David Liew
 */


public class Duke {

    TaskList list;
    Storage storage;
    Ui ui;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();

    }

    public Duke() {
        ui = new Ui();
        ui.intro();
        storage = new Storage();
        try {
            list = new TaskList(storage.readFile());
        } catch (IOException e) {
            System.out.println("You have no save tasks");
        }
    }

    public void run() {
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext() && !isExit) {
            String input = sc.nextLine();
            Command c = Parser.parse(input);
            c.execute(list, ui, storage);
            isExit = c.isExit;
            if (isExit) {
                break;
            }
        }
    }


}
