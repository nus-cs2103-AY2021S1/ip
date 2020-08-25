package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String fileString = tasks.listToString();

        int taskNumber = Integer.parseInt(userInput.substring(7));
        Task curr = tasks.get(taskNumber - 1);
        String taskToBeDeleted = curr.taskToText();

        System.out.println("    Noted. I've removed this task:\n"
                + "        " + tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list.");

        fileString = fileString.replace(taskToBeDeleted + "\n", "");

        // saves fileString to txt file
        Storage.save("/Users/tengjianling/ip/data/duke.txt", fileString);
    }
}

