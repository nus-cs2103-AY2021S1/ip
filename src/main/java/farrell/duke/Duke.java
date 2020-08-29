package main.java.farrell.duke;

import java.time.LocalDate;

/**
 * A personal assistant chatbot that helps to keep track of tasks.
 */
public class Duke {
    /** The list of tasks being tracked */
    private TaskList taskList;

    /** An object that handles saving and loading data */
    private DataManager dataManager = new DataManager();

    /** An object that handles user interaction */
    private UiManager uiManager;

    private boolean shouldExit = false;

    public Duke(UiManager uiManager) throws DukeException {
        this.uiManager = uiManager;
        taskList = dataManager.load();
        uiManager.displayStartMessage();

    }

    /**
     * Displays a welcome message, then runs the main program loop.
     * The main program loop consists of the following:
     * 1. Obtaining user input.
     * 2. Parsing user input to the corresponding command.
     * 3. Execute the command.
     * 4. Display the program output to the user.
     */
    public void run(String input) {
        try {
            //String input = uiManager.getNextInput();
            uiManager.sendUserMessage(input);

            String[] splitInput = input.split(" ", 2);

            CommandType command = CommandType.enumFromString(splitInput[0]);
            String parameters = splitInput.length > 1 ? splitInput[1] : null;
            processCommand(command, parameters);
        } catch (DukeException exception) {
                uiManager.sendDukeMessage(exception.getMessage());
        }
    }

    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Executes a command.
     *
     * @param command The command to execute.
     * @param parameters Parameters relevant to the execution of the command.
     * @return 1 if the program should continue. 0 if the program should exit.
     * @throws DukeException If an invalid command is given.
     */
    public void processCommand(CommandType command, String parameters) throws DukeException{
        switch(command) {
        case TODO:
            ToDo todo = new ToDo(parameters);
            taskList.addTask(todo);
            dataManager.save(taskList);
            uiManager.sendDukeMessage("Added: " + todo.toString());
            break;
        case EVENT:
            String[] eventParameters = parameters.split(" /at ");
            Event event = new Event(eventParameters[0], LocalDate.parse(eventParameters[1]));
            taskList.addTask(event);
            dataManager.save(taskList);
            uiManager.sendDukeMessage("Added: " + event.toString());
            break;
        case DEADLINE:
            String[] deadlineParameters = parameters.split(" /by ");
            Deadline deadline = new Deadline(deadlineParameters[0], LocalDate.parse(deadlineParameters[1]));
            taskList.addTask(deadline);
            dataManager.save(taskList);
            uiManager.sendDukeMessage("Added: " + deadline.toString());
            break;
        case DONE:
            int doneNumber = Integer.parseInt(parameters);
            taskList.updateDone(doneNumber);
            dataManager.save(taskList);
            uiManager.sendDukeMessage("Nice! I've marked the this as done.\n"
                    + taskList.getTask(doneNumber).toString());
            break;
        case LIST:
            uiManager.sendDukeMessage(taskList.toString());
            break;
        case DELETE:
            int deleteNumber = Integer.parseInt(parameters);
            Task deleteTask = taskList.getTask(deleteNumber);

            taskList.deleteTask(deleteNumber);
            dataManager.save(taskList);
            uiManager.sendDukeMessage("I've removed this task:\n"
                    + deleteTask.toString());
            break;
        case BYE:
            shouldExit = true;
            uiManager.sendDukeMessage("Bye. Hope to see you again soon!");
            break;
        case FIND:
            String filteredList = taskList.filteredToString(parameters);
            uiManager.sendDukeMessage("Here are the matching tasks in your list:\n" + filteredList);
            break;
        case UNDEFINED:
            throw new DukeException("I don't know what that means!");
        default:
            throw new DukeException("This command has not yet been implemented!");
        }
    }
}
