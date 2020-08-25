import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents a component of the Duke application that coordinates the roles of
 * other components (i.e. the storage, parser, user interface, task list) in
 * executing a user command.
 */
public class Command {

    private String currInput;

    public Command() {
        this.currInput = "";
    }

    /**
     * Receives the user command from the user interface. The user command
     * will be processed by the parser.
     * @param s the user command.
     */
    public void receive(String s) {
        this.currInput = s;
    }

    /**
     * Returns a boolean value indicating whether the Duke application exits.
     * @return a boolean value indicating whether the Duke application exits.
     */
    public boolean exit() {
        return this.currInput.equals("bye");
    }

    /**
     * Executes a user command. The Command object receives the processed
     * user command from the parser and alerts the task list, storage and user
     * interface to perform the appropriate actions.
     * @param parser the parser of the Duke application
     * @param task the task list of the Duke application
     * @param storage the storage system of the Duke application
     * @param ui the user interface of the Duke application
     * @throws IOException if an error occurs while accessing/creating the directory/file containing
     * the tasks.
     * @throws InvalidTaskArgumentException if an error occurs while processing
     * a command to add tasks.
     * @throws InvalidDoneException if an errors occurs while processing
     * a command to mark tasks as done.
     * @throws InvalidCommandException if the user command cannot be understood.
     * @throws InvalidDeleteException if an error occurs while processing
     * a command to delete tasks.
     * @throws DateException if an error occurs while processing the date/times
     * of event/deadlines.
     */
    public void executeTask(Parser parser, TaskList task, Storage storage, Ui ui)
            throws IOException, InvalidTaskArgumentException, InvalidDoneException, InvalidCommandException,
            InvalidDeleteException, DateException {
        ArrayList<String> lst = parser.processString(currInput, task.getLength());
        if (lst.get(0).equals("Show")) {
            task.showList(ui);
            storage.save(task.getTasks());
        } else if (lst.get(0).equals("Done")) {
            task.markDone(Integer.parseInt(lst.get(1)), ui);
            storage.save(task.getTasks());
        } else if (lst.get(0).equals("Add")) {
            if (lst.get(1).equals("ToDo")) {
                task.addTask(new ToDo(lst.get(2)), ui);
            } else if (lst.get(1).equals("Deadline")) {
                task.addTask(new Deadline(lst.get(2), lst.get(3)), ui);
            } else {
                task.addTask(new Event(lst.get(2), lst.get(3)), ui);
            }
            storage.save(task.getTasks());
        } else {
            task.deleteTask(Integer.parseInt(lst.get(1)), ui);
            storage.save(task.getTasks());
        }
    }
}
