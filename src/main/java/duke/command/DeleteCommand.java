package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand implements Command {
    private final String item;

    public DeleteCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String response = "Noted. I've removed this task:\n";
            int number = Integer.parseInt(item) - 1;
            response += tasks.getList().get(number) + "\n";
            tasks.deleteTask(number);
            storage.updateDataFile(tasks.getList());
            if (tasks.getList().size() > 1) {
                response += "Now you have " + tasks.getList().size() + " tasks in your list.";
            } else {
                response += "Now you have " + tasks.getList().size() + " task in your list.";
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
