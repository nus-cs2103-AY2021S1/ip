import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Parser parser;
    private Ui ui;

    private Duke(Storage storage, Parser parser, Ui ui) {
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
}