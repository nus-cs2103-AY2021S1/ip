import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

/**
 * Represents Duke bot and contains main information of how it works.
 */
public class Duke {
    private static final String PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    
    /**
     * Creates Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }
    
    /**
     * Creates Duke object.
     * @param filePath Pathname of the file that stores tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Reads user input then acts accordingly and stores user data.
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //parser.parse(sc.nextLine(), tasks, ui, storage);
        }
    }
    
    public TaskList getTasks() {
        return this.tasks;
    }
    
    public Storage getStorage() {
        return this.storage;
    }
    
    public Parser getParser() {
        return this.parser;
    }
    
    public Ui getUi() {
        return this.ui;
    }
    
}