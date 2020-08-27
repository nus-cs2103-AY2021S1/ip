import jdk.swing.interop.SwingInterOpUtils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.NoSuchFileException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Duke implements Serializable {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts up the bot by calling the Parser class and its methods
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public void run() throws FileNotFoundException, DukeException {
        System.out.println("Hello I'm Greg!");
        System.out.println("How may I be of service today?");
        Parser parser = new Parser();
        parser.commandParser(tasks, storage);
    }

    /**
     * Creates a new Duke Instance and calls upon the run method to start up the bot.
     * @param args
     * @throws FileNotFoundException
     * @throws DukeException
     */
    public static void main(String[] args) throws FileNotFoundException, DukeException {
        new Duke("tasks").run();
    }
}


   
