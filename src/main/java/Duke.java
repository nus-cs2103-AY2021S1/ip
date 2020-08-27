import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
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

    public void run() throws FileNotFoundException, DukeException {
        System.out.println("Hello I'm Greg!");
        System.out.println("How may I be of service today?");
        Parser parser = new Parser();
        parser.commandParser(tasks, storage);
    }

    public static void main(String[] args) throws FileNotFoundException, DukeException {
        new Duke("tasks").run();
    }
}


   
