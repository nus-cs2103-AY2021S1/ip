import java.io.IOException;
import java.util.Iterator;

public class Duke {
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    public Duke() throws IOException {
        Storage dukeStorage = Storage.initialiseStorage();
        dukeStorage.loadFromDisk();
        this.storage = dukeStorage;
        this.parser = new Parser();
        this.ui = new Ui();
    }

    public Duke(Storage storage, Parser parser, Ui ui) {
        this.storage = storage;
        this.parser = parser;
        this.ui = ui;
    }

    private String response(String userInput, TaskList taskList) throws DukeException, IOException {
        if (userInput.equals("bye")) {
            return ui.showByeMessage();
        } else if (userInput.equals("list")) {
            return ui.showList(returnList());
        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            Task thisTask = parser.processAddTaskInput(userInput, taskList, ui);
            taskList.addTask(thisTask);
            storage.saveToDisk();
            return ui.showAddTaskMessage(thisTask, taskList);
        } else {
            TaskList originalTaskList = TaskList.copy(taskList);
            parser.processOtherActionInput(userInput, taskList, ui);
            storage.saveToDisk();
            return ui.showOtherActionMessage(userInput, originalTaskList);
        }
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

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws IOException, DukeException {
        return response(input, storage.taskList);
    }
}