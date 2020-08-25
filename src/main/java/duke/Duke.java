package duke;

import duke.command.Command;
import duke.exception.DukeInputException;
import duke.exception.DukeSaveDataException;
import duke.io.InputHandler;
import duke.io.OutputHandler;

import java.nio.file.Path;

/** Contains main software logic loop and initialization logic.*/
public class Duke {

    /** TaskManager object that contains all the tasks in Duke's memory */
    private TaskManager taskManager;

    /** Ui object that handles all display and input from user */
    private Ui ui;

    /** Path object that contains the relative directory path to the save file */
    private Path filePath;

    /** SaveManager object that handles all saving and loading of save data on load or on exit */
    private SaveManager saveManager;

    /**
     * Constructs new Duke object with default save file directory.
     * Defaults save file directory to "/data/data.txt".
     */
    public Duke() {
        this(Path.of("/data", "data.txt"));
    }

    /**
     * Constructs new Duke object with custom save file directory.
     *
     * @param filePath Path object representing save file directory.
     */
    public Duke(Path filePath) {
        this.filePath = filePath;
        this.ui = new Ui(new InputHandler(), new OutputHandler());
        this.saveManager = new SaveManager(this.filePath);
        try {
            this.taskManager = saveManager.load();
        } catch (DukeSaveDataException e) {
            this.ui.displayException(e);
            this.taskManager = new TaskManager();
        }

    }

    /**
     * Activates main software loop, accepting and processing commands.
     * Parser parses inputs to commands which are then executed by duke.
     * Exceptions trigger an error message for users without ending the program.
     * Main loop only ends on parsing a "bye" command from user.
     * If unable to save, app will exit without saving.
     */
    public void run() {

        while(true) {

            try {
                Command command = Parser.parse(this.ui.readCommand());
                command.execute(this.ui, this.taskManager, this.saveManager);
                if (command.isByeCommand()) {
                    break;
                }
            } catch (DukeInputException e) {
                ui.displayException(e);
            }

        }

    }

    /**
     * Initializes software.
     *
     * @param args No args required.
     */
    public static void main(String[] args) {
        //initialize Duke with save data and send welcome message
        Duke duke = new Duke(Path.of("data/data.txt"));
        duke.ui.displayGreet();

        //input loop
        duke.run();

        /*testing code to check if printout to savefile worked
        try {
            Scanner sc2 = new Scanner(new File("/data/data.txt"));
            while (sc2.hasNext()) {
                System.out.println(sc2.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

         */

    }
}
