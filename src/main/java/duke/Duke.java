package duke;

import duke.command.Command;
import duke.exception.DukeInputException;
import duke.exception.DukeSaveDataException;
import duke.io.InputHandler;
import duke.io.OutputHandler;

import java.nio.file.Path;

public class Duke {

    private TaskManager taskManager;
    private Ui ui;
    private Path filePath;
    private SaveManager saveManager;

    public Duke() {
        this(Path.of("/data", "data.txt"));
    }

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

    public static void main(String[] args) {
        //initialize duke.Duke with save data and send welcome message
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
