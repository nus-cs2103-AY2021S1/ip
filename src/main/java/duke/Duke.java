package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import duke.command.Command;

public class Duke {
    private static final PrintStream originalOutput = System.out;
    private static final PrintStream originalError = System.err;
    private final Storage storageVar;
    private final TaskListHandler handlerVar;
    private ByteArrayOutputStream guiOutput;

    public Duke() {
        storageVar = new Storage("./data");
        handlerVar = new TaskListHandler(storageVar.getListFromFile());
    }

    public static void main(String[] args) {
        initialize();
    }

    /**
     * Starts the chatbot.
     */
    public static void initialize() {
        String home = System.getProperty("user.dir");
        Storage storage = new Storage(home + "/data");
        if (Storage.hasLoadingError) {
            return;
        }
        Ui.greet();
        TaskListHandler handler = new TaskListHandler(storage.getListFromFile());
        Ui userInterface = new Ui(handler, storage);
        userInterface.run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        setGuiStreams();
        try {
            Command c = Parser.parse(input, handlerVar);
            c.execute(handlerVar, storageVar, input);
            System.out.println();
        } catch (NoSuchElementException e1) {
            System.out.println(e1.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            DukeException.tryAgain();
        }
        resetGuiStreams();
        return guiOutput.toString();
    }

    public void setGuiStreams() {
        guiOutput = new ByteArrayOutputStream();
        ByteArrayOutputStream guiError = new ByteArrayOutputStream();
        System.setOut(new PrintStream(guiOutput));
        System.setErr(new PrintStream(guiError));
    }

    /**
     * Reverts the input and output streams to the standard system ones.
     */
    public void resetGuiStreams() {
        System.setOut(originalOutput);
        System.setErr(originalError);
    }

}
