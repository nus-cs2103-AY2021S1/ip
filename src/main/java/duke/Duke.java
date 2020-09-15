package duke;

/**
 * The Duke program can record down todos, deadlines and events and save it on your computer.
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Duke constructor to initialize a Duke object, initializes a Ui, Storage and TaskList object.
     * @exception DukeException On input error and file path error.
     */
    public Duke() throws DukeException {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList(storage.loadFile());
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            String response = parseUserInput(input);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * parseUserInput method which handles the inputs and responds to the user while calling the appropriate classes
     * @param input user input
     * @exception DukeException On input error and file path error.
     */
    public String parseUserInput(String input) throws DukeException {
        String output = "";
        // splits the input into the different words in order to understand what the user wants
        String[] inputArray = input.split(" ", 2);
        String userCommand = inputArray[0];
        switch (userCommand) {
        case "bye":
            output += ui.bye();
            return output;
        case "list":
            output += ui.printList(taskList.getTasks());
            return output;
        case "done":
            output += ui.doneTask(taskList.done(Integer.parseInt(inputArray[1])));
            output += ui.listCount(taskList.countList());
            storage.saveFile(taskList.getTasks());
            return output;
        case "todo":
            output += ui.addTask(taskList.addTodo(inputArray[1]));
            output += ui.listCount(taskList.countList());
            storage.saveFile(taskList.getTasks());
            return output;
        case "deadline":
            output += ui.addTask(taskList.addDeadline(inputArray[1]));
            output += ui.listCount(taskList.countList());
            storage.saveFile(taskList.getTasks());
            return output;
        case "event":
            output += ui.addTask(taskList.addEvent(inputArray[1]));
            output += ui.listCount(taskList.countList());
            storage.saveFile(taskList.getTasks());
            return output;
        case "delete":
            output += ui.deleteTask(taskList.delete(inputArray[1]));
            output += ui.listCount(taskList.countList());
            storage.saveFile(taskList.getTasks());
            return output;
        case "find":
            output += ui.foundWord(taskList.findWord(inputArray[1]));
            return output;
        case "reschedule":
            String[] specificationsArray = inputArray[1].split(" ",2);
            output += ui.rescheduledTask(taskList.rescheduleTask(specificationsArray[0],specificationsArray[1]));
            storage.saveFile(taskList.getTasks());
            return output;
        case "snooze":
            output += ui.rescheduledTask(taskList.rescheduleTask(inputArray[1],"1"));
            storage.saveFile(taskList.getTasks());
            return output;
        default:
            throw new DukeException("Sorry I don't know what you mean. \n" +
                    "Type /help to see the list of commands available!");
        }
    }
}
