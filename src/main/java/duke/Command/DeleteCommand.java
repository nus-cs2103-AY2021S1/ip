package duke.Command;

<<<<<<< HEAD
import duke.Duke;
=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
import duke.Message;
import duke.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute() {
<<<<<<< HEAD
        Task task = Duke.listArray.get(index - 1);
        Duke.listArray.remove(index - 1);
        return Message.DELETE + task.toString() + "\n" +
                "Now you have " + Duke.listArray.size() +
                (Duke.listArray.size() == 1 ? " task " : " tasks ")
=======
        Task task = listArray.get(index - 1);
        listArray.remove(index - 1);
        return Message.DELETE + task.toString() + "\n" +
                "Now you have " + listArray.size() +
                (listArray.size() == 1 ? " task " : " tasks ")
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
                + "in the list";
    }
}
