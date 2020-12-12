package duke.Command;

import duke.AppUi;
import duke.task.Task;
import duke.TaskList;

public class DoneCommand extends Command {

    private String[] command;
    private int ptr;
    private TaskList list;
    private AppUi appUi;

    public DoneCommand(String input, String[] command, int ptr, TaskList list) {
        super(input);
        this.command = command;
        this.ptr = ptr;
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute(){
        try {
            int taskNumber = Integer.parseInt(command[ptr + 1]);
            Task task = list.markTaskDone(taskNumber);
            if (task != null) {
                return appUi.getMarkAsDoneMsg(task);
            } else {
                return appUi.getTaskNumberExceedMsg(taskNumber, list.getSize());
            }
        } catch (Exception e) {
            return appUi.getWrongFormatAfterDoneMsg();
        }
    }
}
