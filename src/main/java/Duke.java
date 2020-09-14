import java.io.IOException;


public class Duke {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    public Duke() throws IOException, ToDoException, eventException, deadlineException {
        Storage dukeStorage = Storage.initialiseStorage();
        this.storage = dukeStorage;
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String userInput) throws IOException{
        TaskList taskList = this.storage.taskList;
        try {
            if (userInput.equals("bye")) {
                return ui.showByeMessage();
            } else if (userInput.equals("list")) {
                return ui.showList(storage.taskList);
            } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                return parser.processAddTaskInput(userInput, taskList, storage, ui);
            } else {
                TaskList originalTaskList = TaskList.copy(taskList);
                parser.processOtherActionInput(userInput, taskList, ui, storage);
                return ui.showOtherActionMessage(userInput, originalTaskList);
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}