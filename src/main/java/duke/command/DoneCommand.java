package duke.command;

import java.io.IOException;

import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private final String itemToMarkAsDone;

    public DoneCommand(String itemToMarkAsDone) {
        this.itemToMarkAsDone = itemToMarkAsDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        MerchandiseList merchandises) {
        try {
            int itemNumber = Integer.parseInt(itemToMarkAsDone) - 1;
            String response = "Nice! I've marked this task as done:\n";
            tasks.markTaskAsDone(itemNumber);
            response += tasks.getTasks().get(itemNumber);
            storage.updateTasksFile(tasks.getTasks());
            ui.setResponse(response);
        } catch (IOException error) {
            ui.setResponse(error.getMessage());
        } catch (NumberFormatException error) {
            ui.setResponse("Please provide a valid task number to mark as done.");
        } catch (IndexOutOfBoundsException error) {
            ui.setResponse("This task is not in your list!");
        }
    }
}
