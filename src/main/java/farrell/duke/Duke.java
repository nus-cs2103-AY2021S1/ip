package main.java.farrell.duke;

import java.time.LocalDate;

/**
 * A personal assistant chatbot that helps to keep track of tasks.
 */
public class Duke {
    /** The list of tasks being tracked */
    static TaskList taskList;

    /** An object that handles saving and loading data */
    static DataManager dataManager = new DataManager();

    /** An object that handles user interaction */
    static UiManager uiManager = new UiManager();

    /**
     * Displays a welcome message, then runs the main program loop.
     * The main program loop consists of the following:
     * 1. Obtaining user input.
     * 2. Parsing user input to the corresponding command.
     * 3. Execute the command.
     * 4. Display the program output to the user.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        uiManager.displayStartMessage();

        try {
            taskList = dataManager.load();

            while (uiManager.hasUserInput()) {
                String input = uiManager.getNextInput();
                String[] splitInput = input.split(" ", 2);

                CommandType command = CommandType.enumFromString(splitInput[0]);
                String parameters = splitInput.length > 1 ? splitInput[1] : null;
                int result = processCommand(command, parameters);
                if (result == 0) {
                    return;
                }
            }
        } catch (DukeException exception) {
            uiManager.printInWindow(exception.getMessage());
        }
    }

    /**
     * Executes a command.
     *
     * @param command The command to execute.
     * @param parameters Parameters relevant to the execution of the command.
     * @return 1 if the program should continue. 0 if the program should exit.
     * @throws DukeException If an invalid command is given.
     */
    public static int processCommand(CommandType command, String parameters) throws DukeException{
        switch(command) {
        case TODO:
            ToDo todo = new ToDo(parameters);
            taskList.addTask(todo);
            dataManager.save(taskList);
            uiManager.printInWindow("Added: " + todo.toString());
            break;
        case EVENT:
            String[] eventParameters = parameters.split(" /at ");
            Event event = new Event(eventParameters[0], LocalDate.parse(eventParameters[1]));
            taskList.addTask(event);
            dataManager.save(taskList);
            uiManager.printInWindow("Added: " + event.toString());
            break;
        case DEADLINE:
            String[] deadlineParameters = parameters.split(" /by ");
            Deadline deadline = new Deadline(deadlineParameters[0], LocalDate.parse(deadlineParameters[1]));
            taskList.addTask(deadline);
            dataManager.save(taskList);
            uiManager.printInWindow("Added: " + deadline.toString());
            break;
        case DONE:
            int doneNumber = Integer.parseInt(parameters);
            taskList.updateDone(doneNumber);
            dataManager.save(taskList);
            uiManager.printInWindow("Nice! I've marked the this as done.\n" + taskList.getTask(doneNumber).toString());
            break;
        case LIST:
            uiManager.printInWindow(taskList.toString());
            break;
        case DELETE:
            int deleteNumber = Integer.parseInt(parameters);
            uiManager.printInWindow("I've removed this task:\n" + taskList.getTask(deleteNumber).toString());
            taskList.deleteTask(deleteNumber);
            dataManager.save(taskList);
            break;
        case BYE:
            uiManager.printInWindow("Bye. Hope to see you again soon!");
            return 0;
        case UNDEFINED:
            throw new DukeException("I don't know what that means!");
        default:
            throw new DukeException("This command has not yet been implemented!");
        }
        return 1;
    }
}
