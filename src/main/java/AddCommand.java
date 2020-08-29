import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * The AddCommand class is used to update the current TaskList with the newly added task
 * and prints the added task to the user.
 */
class AddCommand extends Command {
    protected String[] newTaskDetails;

    AddCommand(String[] newTaskDetails) {
        this.newTaskDetails = newTaskDetails;
    }

    /**
     * The execute method of the AddCommand is used to add the task in the command given into
     * the TaskList and updates the stored TaskList in saved file.
     *
     * @param tasks This is the current TaskList in saved file.
     * @return A boolean to indicate whether the program should be exited.
     */
    @Override
    boolean execute(TaskList tasks) {
        try {
            Ui.printTask(tasks.addTask(newTaskDetails), Action.ADD, tasks.getSize());
        } catch (DukeException e) {
            Ui.simplePrint(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof AddCommand) {
            AddCommand command = (AddCommand) o;
            return Arrays.equals(command.newTaskDetails, this.newTaskDetails);
        } else {
            return false;
        }
    }
}