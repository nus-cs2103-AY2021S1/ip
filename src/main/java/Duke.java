import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

public class Duke {
    private final Scanner SC = new Scanner(System.in);
    private final static Path DUKE_DATA_FILE_PATH = Paths.get("data", "duke.txt");
    private final static Path DUKE_DATA_DIR_PATH = Paths.get("data");

    enum Commands {
        EXIT("BYE"), DELETE("DELETE "), LIST("LIST"),
        DONE("DONE "), TODO("TODO "), EVENT("EVENT "),
        DEADLINE("DEADLINE "), DELETEALL("DELETE ALL"), HELP("HELP"),
        FIND("FIND ");
        private final String str;
        Commands(String str){
            this.str = str;
        }
        public String getString() {
            return this.str;
        }
    }

    private final String VERSION_NUMBER = "1.1.0";
    private String logo;
    private String introMessage;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(Path filePath, Path dirPath) {
        parser = new Parser(SC);
        introMessage = "I'm Deuk, nice to meet you" + Ui.NEW_LINE + Ui.PADDING +
                "How can I be of service today?" + Ui.NEW_LINE + Ui.PADDING +
                "Enter \"help\" to see the list of available commands";

        logo = "     _____             _    \n"
                        + "    |  __ \\           | |   \n"
                        + "    | |  | | ___ _   _| | __\n"
                        + "    | |  | |/ _ \\ | | | |/ /\n"
                        + "    | |__| |  __/ |_| |   < \n"
                        + "    |_____/ \\___|\\__,_|_|\\_\\  v" + VERSION_NUMBER;

        this.storage = new Storage(filePath, dirPath);
        try {
            this.tasks = new TaskList(storage.loadTasksFromDisk());
        } catch (DukeDataFolderException ex) {
            Ui.printError(ex.getMessage());
            this.storage.createDukeDataFolder();
            this.tasks = new TaskList();
        } catch (DukeException ex) {
            Ui.printError(ex.getMessage());
            this.tasks = new TaskList();
        } catch (FileNotFoundException ex) {
            Ui.printError("Missing Deuk Data File!" + Ui.NEW_LINE + Ui.PADDING +
                    "Creating new Deuk Data File...");
            this.storage.createDukeDataFile();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts the Deuk programme and takes in commands through standard input.
     */
    public void init() {
        Ui.sayHello(this.logo, this.introMessage);
        while (this.parser.hasNext()) {
            this.parser.parseLine();
            boolean isByeCommandInvoked = this.parser.executeCommand(this.storage, this.tasks);
            if (isByeCommandInvoked) {
                break;
            }
        }
    }

    private void echoBack(String message) {
        Ui.printVerbal(message);
    }

    public static void main(String[] args) {
        new Duke(DUKE_DATA_FILE_PATH, DUKE_DATA_DIR_PATH).init();
    }
}
