package duke.Command;

import duke.AppUi;
import duke.Task;
import duke.TaskList;

public class DeleteCommand extends Command {

    private String[] command;
    private int ptr;
    private TaskList list;
    private AppUi appUi;

    public DeleteCommand(String input, String[] command, int ptr, TaskList list) {
        super(input);
        this.command = command;
        this.ptr = ptr;
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute(){
        try {
            int taskNumber = Integer.parseInt(command[ptr + 1]);
            Task task = list.delete(taskNumber);
            if (task == null) {
                return appUi.getTaskNumberExceedMsg(taskNumber, list.getSize());
            } else {
                return appUi.getDeleteMessage(task, list);
            }
        } catch (Exception e) {
            return appUi.getWrongFormatAfterDeleteMsg();
        }
    }
}
