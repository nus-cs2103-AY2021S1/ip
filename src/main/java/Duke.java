import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class which represents the duke bot.
 * @author vanGoghhh
 */

public class Duke extends Application{

    /**
     * Initiates the bot.
     * @throws DukeException
     * @throws IOException
     */
    private static void startBot() throws DukeException, IOException {
        Storage storage = new Storage("data" + File.separator + "duke.txt");
        TaskList dukeTaskList = new TaskList(storage.loadData());
        UI dukeUI = new UI();
        Parser dukeParser = new Parser();

        dukeUI.greetUser();

        boolean isExit = false;

        while (!isExit) {
            String userCommand = dukeUI.readCommand();
            Command cmd = dukeParser.parseCommand(userCommand);
            cmd.execute(dukeTaskList, dukeUI);
            isExit = cmd.isExit();
            storage.writeData(dukeTaskList);
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) throws Exception {
        startBot();
    }
}