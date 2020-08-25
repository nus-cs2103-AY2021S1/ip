import java.io.IOException;

public class DeleteCommand extends Command{

    DeleteCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int itemNumber = Integer.parseInt(description);
            String output = "";
            output += "Noted. I've removed this task:\n\t" + tasks.get(itemNumber - 1);
            tasks.remove(itemNumber - 1);
            output += "\nYou now have " + tasks.size() + " tasks in the list.";
            ui.showOutput(output);
            storage.writeToFile(tasks);
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
