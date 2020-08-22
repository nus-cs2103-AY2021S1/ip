package duke.Command;

<<<<<<< HEAD
import duke.Duke;
=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
import duke.Message;
import duke.Task;

public class CompleteCommand extends Command {

    private final int index;

    public CompleteCommand(int index) {
        this.index = index;
    }

    public String execute() {
<<<<<<< HEAD
        Task task = Duke.listArray.get(index - 1);
=======
        Task task = listArray.get(index - 1);
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
        task.markAsDone();
        return Message.DONE + task.toString();
    }
}
