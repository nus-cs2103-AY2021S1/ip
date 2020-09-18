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
     * getResponse method to parse a user input and return a response
     * @param input user input
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
        case "":
            throw new DukeException("Avoid starting commands with blank spaces!");
        case "bye":
            output += ui.bye();
            Main.closeDuke();
            return output;
        case "list":
            output += ui.printList(taskList.getTasks());
            return output;
        case "done":
            if (inputArray.length == 2) {
                output += ui.doneTask(taskList.done(inputArray[1]));
                output += ui.listCount(taskList.countList());
                storage.saveFile(taskList.getTasks());
                return output;
            } else {
                throw new DukeException("Please specify the index of the task to set as done!");
            }
        case "todo":
            if (inputArray.length == 2) {
                output += ui.addTask(taskList.addTodo(inputArray[1]));
                output += ui.listCount(taskList.countList());
                storage.saveFile(taskList.getTasks());
                return output;
            } else {
                throw new DukeException("Please key in the name of the todo!");
            }
        case "deadline":
            try {
                output += ui.addTask(taskList.addDeadline(inputArray[1]));
                output += ui.listCount(taskList.countList());
                storage.saveFile(taskList.getTasks());
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please key in more information about the deadline!");
            }
        case "event":
            try {
                output += ui.addTask(taskList.addEvent(inputArray[1]));
                output += ui.listCount(taskList.countList());
                storage.saveFile(taskList.getTasks());
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please key in more information about the event!");
            }
        case "delete":
            try {
                output += ui.deleteTask(taskList.delete(inputArray[1]));
                output += ui.listCount(taskList.countList());
                storage.saveFile(taskList.getTasks());
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the index of the task to delete!");
            }
        case "find":
            try {
                output += ui.foundWord(taskList.findWord(inputArray[1]));
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the word you want to search for!");
            }
        case "reschedule":
            try {
                String[] specificationsArray = inputArray[1].split(" ", 2);
                output += ui.rescheduledTask(taskList.rescheduleTask(specificationsArray[0], specificationsArray[1]));
                storage.saveFile(taskList.getTasks());
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the index and a new date and time!");
            }
        case "snooze":
            try {
                output += ui.rescheduledTask(taskList.snoozeTask(inputArray[1]));
                storage.saveFile(taskList.getTasks());
                return output;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the index of task and number of hours to snooze!");
            }
        case "help":
            output += ui.helpString();
            return output;
        default:
            throw new DukeException("Sorry I don't know what you mean. \n"
                    + "Type help to see the list of commands available!");
        }
    }
}
