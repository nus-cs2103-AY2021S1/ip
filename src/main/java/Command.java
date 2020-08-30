import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the central component of the Duke application. This
 * component coordinates the roles of other components (i.e. the
 * storage, parser, user interface, task list) in executing a user
 * command.
 */
public class Command {

    private String currentInput;

    public Command() {
        this.currentInput = "";
    }

    /**
     * Receives a user command from the user interface.
     * @param s User command.
     */
    public void receive(String s) {
        this.currentInput = s;
    }

    /**
     * Returns Boolean value indicating whether the Duke application should exit.
     * @return Boolean value indicating whether the Duke application should exit.
     */
    public boolean exit() {
        return this.currentInput.equals("bye");
    }

    /**
     * Executes a user command. The Command object directs the user command to the
     * parser for parsing. It then proceeds to alert the task list, storage, and user
     * interface to perform the appropriate actions.
     * @param parser Parser of the Duke application.
     * @param taskList Task list of the Duke application.
     * @param storage Storage system of the Duke application.
     * @param ui User interface of the Duke application.
     * @throws IOException If an error occurs while accessing/creating the directory/file
     * containing the tasks.
     * @throws InvalidTaskArgumentException If an error occurs while parsing
     * a command to add tasks.
     * @throws InvalidDoneException If an errors occurs while parsing
     * a command to mark tasks as done.
     * @throws InvalidCommandException If the user command cannot be understood.
     * @throws InvalidDeleteException If an error occurs while parsing
     * a command to delete tasks.
     * @throws DateException If an error occurs while parsing the dates/times
     * of event/deadlines.
     */
    public void executeTask(Parser parser, TaskList taskList, Storage storage, Ui ui)
            throws IOException, InvalidTaskArgumentException, InvalidDoneException, InvalidCommandException,
            InvalidDeleteException, DateException {
        ArrayList<String> lst = parser.parseString(currentInput, taskList.getLength());
        if (lst.get(0).equals("Show")) {
            taskList.showList(ui);
            storage.save(taskList.getTasks());
        } else if (lst.get(0).equals("Done")) {
            taskList.markDone(Integer.parseInt(lst.get(1)), ui);
            storage.save(taskList.getTasks());
        } else if (lst.get(0).equals("Add")) {
            if (lst.get(1).equals("ToDo")) {
                taskList.addTask(new ToDo(lst.get(2)), ui);
            } else if (lst.get(1).equals("Deadline")) {
                taskList.addTask(new Deadline(lst.get(2), lst.get(3)), ui);
            } else {
                taskList.addTask(new Event(lst.get(2), lst.get(3)), ui);
            }
            storage.save(taskList.getTasks());
        } else if (lst.get(0).equals("Find")) {
            taskList.findTask(lst.get(1), ui);
        } else {
            taskList.deleteTask(Integer.parseInt(lst.get(1)), ui);
            storage.save(taskList.getTasks());
        }
    }
}
