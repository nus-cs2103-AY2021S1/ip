import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The class with the chat bot that deals with the user's inputs.
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private static final String BYE = "bye";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(Path.of(filePath));
        try {
            taskList = new TaskList(storage.showTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            System.exit(0);
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(Path.of("duke.txt"));
        try {
            taskList = new TaskList(storage.showTasks());
        } catch (DukeException e) {
            ui.showLoadingError();
            System.exit(0);
        }
    }

    /**
     * Gets the response from Duke with the given input.
     * @param input String from user.
     * @return A String containing duke's response.
     */
    public String getResponse(String input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        String trimmed = input.trim();
        String first = trimmed.split(" ")[0].trim(); // checking the first word
        String last = input.substring(first.length()).trim(); // get rid of the first word

        switch (first) {
        case "done":

            return Parser.parseDone(last, taskList, storage, ui);

        case "todo":

            return Parser.parseToDo(last, taskList, storage, ui);

        case "deadline":

            return Parser.parseDeadline(last, taskList, storage, ui, formatter);

        case "event":

            return Parser.parseEvent(last, taskList, storage, ui, formatter);

        case "find":   // finds the task even if the keyword matches the task partially

            String[] keyWords = last.split(" ");
            ArrayList<Task> findTasks = new ArrayList<>();
            for (String keyWord : keyWords) {
                ui.handleMatchingTasks(keyWord, taskList.getTasks(), findTasks);
            }
            return ui.printMatchingTasks(findTasks);


        case "delete":

            return Parser.parseDelete(last, taskList, storage, ui);

        case "list":

            return ui.showList(taskList.getTasks());

        case BYE:

            return ui.exit();

        default:

            return ui.invalidInput();
        }
    }
}
