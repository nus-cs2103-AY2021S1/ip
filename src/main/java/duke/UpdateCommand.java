package duke;

import java.io.IOException;

public class UpdateCommand extends Command {
    UpdateCommand(String description) {
        this.description = description;
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String output = "";
            int itemNumber = Integer.parseInt(description);
            tasks.get(itemNumber - 1).isDone = true;
            output += "Nice, I've marked this item as done:\n\t" + tasks.get(itemNumber - 1);
            ui.showOutput(output);
            storage.writeToFile(tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new DukeException("Please key in a valid number for \"done\"");
        } catch (IOException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
