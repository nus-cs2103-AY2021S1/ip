package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    private final String item;

    public DoneCommand(String item) {
        this.item = item;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int number = Integer.parseInt(item) - 1;
            String response = "Nice! I've marked this task as done:\n";
            tasks.markAsDone(number);
            response += tasks.getList().get(number);
            storage.updateDataFile(tasks.getList());
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
