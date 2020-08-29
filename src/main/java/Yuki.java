import java.util.Scanner;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;
import duke.command.ExitCommand;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * <code>Main</code> starts the entire application by creating a <code>Duke</code> object and checking for existing
 * tasks.
 */
public class Yuki {
    protected static final Ui UI = new Ui();
    protected Storage storage;
    protected TaskList taskList;
    protected boolean isExit;
    private Stage stage;

    Yuki() {
        try {
            this.storage = Storage.createStorage("data/duke.txt");
            if (storage.isNew()) {
                UI.print(UI.fileCreationSuccess());
            } else {
                UI.print(UI.welcome());
            }
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the whole program by taking in input commands.
     * Terminates only when an <Code>ExitCommand</Code> is given.
     */
    public void run() {
        Scanner input = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit && input.hasNextLine()) {
            try {
                String commandMessage = input.nextLine();
                Command c = Parser.parse(commandMessage.trim());
                String s = c.execute(commandMessage, storage, UI, taskList);
                UI.print(s);
                if (c instanceof ExitCommand) {
                    isExit = true;
                    input.close();
                }
            } catch (DukeException e) {
                UI.print(e.getMessage());
            }
        }
    }

    /**
     * Creates a <code>Duke</code> object.
     *
     * @param args array for command-line arguments
     */
    public static void main(String[] args) {
        Yuki yuki = new Yuki();
        yuki.run();
    }

    /**
     * Returns a string containing the text in a specific format.
     *
     * @param text to be included
     * @return a string containing the text in a specific format
     */
    String printFormat(String text) {
        String headerLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        return headerLine + text + "\n" + headerLine;
    }

    /**
     * Generates a response from <code>Duke</code> from user's input.
     *
     * @param input command from user
     * @return string representation of response from <code>Duke</code>
     */
    String getResponse(String input) {
        try {
            input = input.trim();
            Command c = Parser.parse(input);
            String s = c.execute(input, storage, Yuki.UI, taskList);

            if (c instanceof ExitCommand) {

                PauseTransition delay = new PauseTransition(Duration.seconds(3));
                delay.setOnFinished(event -> stage.close());
                delay.play();

            }

            return s;
        } catch (DukeException ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

}
