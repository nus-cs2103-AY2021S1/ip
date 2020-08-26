package duke;

import java.io.IOException;

/**
 * Represents a done command for a task.
 */
public class DoneCommand extends Command {

    /** User input as a String */
    String userInput;

    /**
     * Constructs a new instance of a DoneCommand object with user input.
     *
     * @param userInput User input as a String.
     */
    DoneCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the marking of task as done.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @throws DukeException if index < 0 or index > taskList.size().
     * @throws IOException When writing to file fails.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui)
            throws DukeException, IOException {
        int len = userInput.length();
        int pos = Integer.parseInt(userInput.substring(5, len));
        if (pos <= taskList.size() && pos > 0) {
            taskList.getTasks().get(pos - 1).markAsDone(); //marking task as done
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            System.out.println("Great work! I've marked this task as done:");
            System.out.println(taskList.getTasks().get(pos - 1));
            System.out.println("Keep the ticks going! ^_^");
            storage.writeToFile(taskList.getTasks());
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }
}
