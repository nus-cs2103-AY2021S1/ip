import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;


public class Jarvis {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Jarvis constructor takes in file path to setup duke
     * @param filePath
     * @throws IOException
     */
    public Jarvis(String filePath) throws IOException {
        assert filePath.contains(".txt"); //enure that correct file type is passed
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the Jarvis instance
     */
    public void run() {
        ui.welcome();
        boolean exit = false;
        while (!exit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Command.parse(command);
                c.execute(tasks, ui, storage);
                exit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();;
            }
        }
        ui.goodbye();
    }

    public static void main(String[] args) throws IOException {
        new Jarvis("data/tasks.txt").run();
    }

    /**
     * getResponse takes the user command as a String and returns the response from Jarvis
     * @param input User command String
     * @return Jarvis response String
     */
    String getResponse(String input) {
        String command = input;
        try {
            Command c = Command.parse(command);
            return c.execute(tasks, ui, storage);
        } catch (Exception e) {
            e.printStackTrace();
            return "Jarvis exception: " + e.getMessage();
        }
    }
}