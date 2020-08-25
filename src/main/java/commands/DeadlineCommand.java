package commands;

import data.exception.DukeIllegalCommandException;
import data.exception.DukeInvalidUserInputException;
import data.task.Deadline;
import data.task.TaskList;
import parser.Parser;
import storage.Storage;
import ui.Ui;

public class DeadlineCommand extends Command{

    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    private String user_input;

    public DeadlineCommand(TaskList taskList, Storage storage, Ui ui, String user_input) {
        this.taskList = taskList;
        this.storage = storage;
        this.user_input = user_input;
        this.ui = ui;
    }

    @Override
    public void execute() throws DukeInvalidUserInputException, DukeIllegalCommandException {
        try{
            String withoutCommand = user_input.substring(user_input.indexOf(' '));
            String[] withoutCommandArr = withoutCommand.split("/");
            String description = withoutCommandArr[0].trim();
            if (description.isEmpty()) {
                throw new DukeInvalidUserInputException("I'm sorry to inform you that the "
                        + "description of a deadline must not be empty.");
            }
            if (withoutCommandArr.length < 2) {
                throw new DukeInvalidUserInputException("It appears you are missing a "
                        + "follow up '/by' command.");
            }
            String followUpCommand = Parser.parseFollowUpCommand(withoutCommandArr[1]);
            if (followUpCommand.equals("by")) {
                if (!withoutCommandArr[1].trim().contains(" ")) {
                    throw new DukeInvalidUserInputException("It appears you are missing "
                            + "the date and time for your deadline.");
                }
                String dateTime = withoutCommandArr[1].substring(withoutCommandArr[1].indexOf(" ")).trim();
                Deadline newTask = new Deadline(description, dateTime);
                this.taskList.add(newTask);
                this.ui.showTotalTasks(this.taskList.getTotalTask());
                this.storage.saveTask(newTask);
            } else {
                throw new DukeIllegalCommandException(followUpCommand);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("I'm sorry to inform you that the description "
                    + "of a deadline must not be empty.");
        }
    }

    @Override
    public String toString() {
        return "DeadlineCommand";
    }
}
