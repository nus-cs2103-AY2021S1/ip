package command;

import exception.DukeException;
import storage.Storage;
import task.Deadline;
import tasklist.TaskList;
import ui.Ui;

public class DeadlineCommand extends Command {
    private String deadlineDetails;

    public DeadlineCommand(String deadlineDetails) {
        this.deadlineDetails = deadlineDetails;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] details = this.deadlineDetails.split(" /by ", 2);
        Deadline newDeadline = new Deadline(details[0], details[1], false);
        taskList.add(newDeadline);
        String output = ui.LINE + "Got it. I've added this task: \n"
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list."
                + "\n" + ui.LINE;
        System.out.println(output);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
