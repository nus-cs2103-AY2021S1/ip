import exception.DukeException;
import javafx.application.Platform;
import parser.Parser;
import tasklist.TaskList;
import storage.Storage;
import ui.Ui;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class Duke {
    private final static String FILE_PATH = System.getProperty("user.dir") + "/data/duke.txt";
    private final static String ARCHIVE_PATH = System.getProperty("user.dir") + "/data/archive.txt";
    private final static String DATA_PATH = System.getProperty("user.dir") + "/data/";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private String input;
    private String output;
    private Storage archivedStorage;
    private TaskList archivedTasks;

    /**
     * Creates and initialises a Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        archivedStorage = new Storage(ARCHIVE_PATH);

        try {
            File file = new File(FILE_PATH);
            tasks = new TaskList(storage.load());
            archivedTasks = new TaskList(archivedStorage.load());
        } catch (FileNotFoundException e) {
            File newData = new File(DATA_PATH);
            newData.mkdir();
            File newFile = new File(FILE_PATH);
            File newArchive = new File(ARCHIVE_PATH);
            try {
                boolean isSuccessful = newFile.createNewFile();
                boolean archiveCreated = newArchive.createNewFile();

                tasks = new TaskList(storage.load());
                archivedTasks = new TaskList(archivedStorage.load());
            } catch (IOException ex) {
                System.out.println("An error occurred, file could not be created.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Runs the Duke object after it has been initialised.
     * @param command The input command
     */
    public void run(String command) {
        input = command;
        try {
            output = Parser.commandParser(input, tasks, archivedTasks);
        } catch (DukeException e) {
            output = e.getMessage();
        }
    }

    /**
     * Gets the response for the input command
     * @param input The input command
     * @return a String with the response
     */
     String getResponse(String input) {
         if (input.equals("bye")) {
             new Timer().schedule(new TimerTask() {
                 public void run() {
                     Platform.exit();
                     System.exit(0);
                 }
             } , 1000);
         }
         run(input);
         return this.output;
    }
}

