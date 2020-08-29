package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeArgumentException;
import duke.exception.DukeExecutionException;
import duke.exception.DukeIoException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;


public class Duke {

    private static final String PATH = "data.txt";

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        try {
            Ui.showWelcome();
            Scanner sc = new Scanner(System.in);
            // TODO: clean up storage exceptions
            Storage storage = new Storage(PATH);
            boolean isExit = false;
            while (!isExit) {
                try {
                    String input = sc.nextLine().trim();
                    Command command = Parser.parseCommand(input);
                    command.execute(storage);
                    isExit = command.shouldExit();
                } catch (DukeArgumentException dae) {
                    Ui.wrapText(dae.getMessage());
                } catch (DukeExecutionException dee) {
                    Ui.wrapText(dee.getMessage());
                }
            }
        } catch (DukeIoException die) {
            Ui.wrapText(die.getMessage());
        }
        Ui.showExit();
    }

}
