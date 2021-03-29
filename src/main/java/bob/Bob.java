package bob;

import bob.command.Command;
import bob.exception.BobException;
import javafx.scene.Scene;

/**
 * This class encapsulates Bob, the personal assistant.
 */
public class Bob {

    /** A list consisting of tasks tracked by Bob. */
    private TaskList tasks = new TaskList();

    /** Handles storage and access of data used by Bob. */
    private Storage storage;

    /** Presents an interface to the user */
    private UI uI;


    /** Constructs a Bob with a provided filePath
     *
     * @param filePath the filePath of the file for data storage and access.
     */
    public Bob(String filePath) {
        uI = new UI();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            storage.initialiseStorage();
            storage.loadSave(tasks);
        } catch (BobException e) {
            uI.printError(e.getMessage());
        }
    }

    /**
     * An empty constructor.
     */
    public Bob() {

    }

    /**
     * Initialises Bob to accept input and provide responses.
     */
    public void run() {
        System.out.println(uI.greet());

        boolean isExit = false;

        while (!isExit) {
            try {
                String command = uI.readCommand();
                Command c = Parser.parse(command);
                assert c != null : "A command should be provided";
                System.out.println(c.execute(tasks, uI, storage));
                isExit = c.isExit();
            } catch (BobException e) {
                System.out.println(uI.printError(e.getMessage()));
            }
        }
    }

    /**
     * Constructs Bob and initialises it.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Bob("data/save.txt").run();
    }

    /**
     * Returns the response of Bob with input provided from the user.
     *
     * @param input provided by the user.
     * @return a response from Bob.
     * @throws BobException if exceptions occur.
     */
    String getResponse(String input) throws BobException {
            String command = input;
            Command c = Parser.parse(command);
            assert c != null : "A command should be provided";
            return c.execute(tasks, uI, storage);
    }
}

