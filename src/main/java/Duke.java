import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke extends Application {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    public Duke() {
        this.storage = new Storage();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public Duke(Storage storage, Parser parser, Ui ui) {
        this.storage = storage;
        this.parser = parser;
        this.ui = ui;
    }

    public static void main(String[] args) throws IOException {
        Storage dukeStorage = Storage.initialiseStorage();
        dukeStorage.loadFromDisk();
        Duke duke = new Duke(dukeStorage, new Parser(), new Ui());

        duke.ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            try {
                duke.response(scanner, duke.storage.taskList);
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IOException IOe) {
                IOe.printStackTrace();
            }
        }
    }

    private void response(Scanner scanner, TaskList taskList) throws DukeException, IOException {
        String userInput = scanner.nextLine();
        ui.showBorder();
        if (userInput.equals("bye")) {
            ui.showByeMessage();
            scanner.close();
        } else if (userInput.equals("list")) {
            ui.showList(returnList());
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            Task thisTask = parser.processAddTaskInput(userInput, taskList, ui);
            taskList.addTask(thisTask);
        } else {
            parser.processOtherActionInput(userInput, taskList, ui);
        }
        storage.saveToDisk();
        response(scanner, taskList);
    }

    /**
     * Called by ui to give users a list view of all their tasks
     * @return list view of all tasks
     */
    public String returnList() {
        String returnString = "";
        int counter = 0;
        Iterator<Task> taskIterator = storage.taskList.getList().iterator();
        while (taskIterator.hasNext()) {
            Task thisTask = taskIterator.next();
            returnString += "\n" + (counter + 1) + ". " + thisTask.toString();
            counter++;
        }
        return returnString;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

    }
}