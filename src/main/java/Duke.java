import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a Duke chat bot that helps the user to keep track of their tasks, such as todos,
 * events and deadlines.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private  Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.showLoadError();
        }
    }

    void handleInput(String[] input) {
        try {
            switch (input[0]) {
            case "list":
                ui.list(tasks.formattedList());
                break;
            case "bye":
                storage.saveFile(tasks);
                ui.exit();
                break;
            case "done":
                ui.completeTask(tasks.completeTask(Integer.valueOf(input[1])));
                break;
            case "delete":
                ui.deleteTask(tasks.deleteTask(Integer.valueOf(input[1])), tasks.getLength());
                break;
            case "find":
                ui.find(tasks.findTasks(input[1]));
                break;
            case "todo":
                // Fallthrough
            case "event":
                // Fallthrough
            case "deadline":
                if (input.length == 1) {
                    throw new MissingDescriptionException(input[0]);
                } else {
                    ui.addTask(tasks.addTask(input[0], input[1]), tasks.getLength());
                }
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            ui.showDukeError(e);
        } catch (IOException e) {
            ui.showSaveError();
        }
    }

    /**
     * Runs the chat bot by accepting user inputs and handling it through
     * other classes like TaskList and Ui.
     */
    public void run() {
        ui.greet();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String[] input = Parser.parse(sc.nextLine());
            handleInput(input);
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}

