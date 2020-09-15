/**
 * Class that makes a ToDoCommand which helps generate a ToDo task.
 */
public class ToDoCommand extends Command {
    private String input;

    /**
     * Constructs a ToDoCommand object with a provided input.
     *
     * @param input The input that has been input by the user.
     */
    ToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Makes a ToDo event.
     *
     * @param tasklist The list of tasks.
     * @param ui       The ui object that helps generate the different messgaes for display.
     * @return String The message that is to be output on the GUI.
     * @throws DukeEmptyToDoException If there is a empty description.
     */
    @Override
    public String execute(TaskList tasklist, UI ui) throws DukeEmptyToDoException {
        String message = "";
        try {
            if (input.split(" ").length == 1) {
                throw new DukeEmptyToDoException(input);
            }
            String toDoMessage = Parser.stringBuilder(input.split(" "), 1, input.split(" ").length - 1);
            ToDo todoTask = new ToDo(toDoMessage);
            tasklist.addTask(todoTask);
            message = ui.printTaskAdd(todoTask, tasklist.numOfTasks());
        } catch (DukeEmptyToDoException e) {
            message = e.getMessage();
        }
        return message;
    }
}
