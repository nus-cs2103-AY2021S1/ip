package duke;

import duke.command.Command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    private ByteArrayOutputStream GUIOutput;
    private ByteArrayOutputStream GUIError;
    private Storage storageVar;
    private TaskListHandler handlerVar;
    private static final PrintStream originalOutput = System.out;
    private static final PrintStream originalError = System.err;

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
        Storage storage = new Storage( home + "/data");
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
        setGUIStreams();
        try {
            Command c = Parser.parse(input, handlerVar);
            c.execute(handlerVar, storageVar);
            System.out.println();
        } catch (NoSuchElementException e1) {
            System.out.println(e1.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            DukeException.tryAgain();
        }
        resetGUIStreams();
        return GUIOutput.toString();
    }

    public void setGUIStreams() {
        GUIOutput = new ByteArrayOutputStream();
        GUIError = new ByteArrayOutputStream();
        System.setOut(new PrintStream(GUIOutput));
        System.setErr(new PrintStream(GUIError));
    }

    public void resetGUIStreams() {
        System.setOut(originalOutput);
        System.setErr(originalError);
    }

}
