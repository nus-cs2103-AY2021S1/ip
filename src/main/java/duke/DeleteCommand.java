package duke;

import java.io.IOException;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a delete command.
     * @param description Description of command
     */
    DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert tasks != null : "tasks have not been initialised";
        assert ui != null : "ui have not been initialised";
        assert storage != null : "storage have not been initialised";
        try {
            int itemNumber = Integer.parseInt(description);
            String output = "";
            output += "Noted. I've removed this task:\n\t" + tasks.get(itemNumber - 1);
            tasks.remove(itemNumber - 1);
            output += "\nYou now have " + tasks.size() + " tasks in the list.";
            storage.writeToFile(tasks);
            return ui.showOutput(output);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("Please key in a valid number for \"delete\"");
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
