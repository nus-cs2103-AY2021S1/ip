package duke.command;

import java.io.IOException;

import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand implements Command {
    private final String itemToDelete;

    public DeleteCommand(String itemToDelete) {
        this.itemToDelete = itemToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        MerchandiseList merchandises) {
        try {
            String response = "Noted. I've removed this task:\n";
            int itemNumber = Integer.parseInt(itemToDelete) - 1;
            response += tasks.getTasks().get(itemNumber) + "\n";
            tasks.deleteTask(itemNumber);
            storage.updateTasksFile(tasks.getTasks());
            if (tasks.getTasks().size() > 1) {
                response += "Now you have " + tasks.getTasks().size() + " tasks in your list.";
            } else {
                response += "Now you have " + tasks.getTasks().size() + " task in your list.";
            }
            ui.setResponse(response);
        } catch (IOException error) {
            ui.setResponse(error.getMessage());
        } catch (NumberFormatException error) {
            ui.setResponse("Please provide a valid task number to delete.");
        } catch (IndexOutOfBoundsException error) {
            ui.setResponse("This task is not in your list!");
        }
    }
}
