package duke.Command;

import duke.AppUi;
import duke.Deadline;
import duke.TaskList;

public class DeadlineCommand extends Command {

    private static final int DEADLINE = 1;
    private TaskList list;
    private AppUi appUi;

    public DeadlineCommand(String input, TaskList list) {
        super(input);
        this.list = list;
        this.appUi = new AppUi();
    }

    public String execute(){
        Deadline deadline = Deadline.of(input);
        if (deadline == null) {
            return appUi.getWrongDeadlineFormatMsg();
        }
        list.add(deadline);
        String res = "";
        if (deadline.getDate() == null) {
            res += appUi.getWrongDateFormatMsg();
        }
        if (deadline.getTime() == null) {
            res += appUi.getWrongTimeFormatMsg();
        }
        return res + appUi.getAfterAddMsg(deadline, DEADLINE, list.getSize());
    }
}
