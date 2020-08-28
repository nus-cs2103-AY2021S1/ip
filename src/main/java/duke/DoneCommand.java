package duke;

public class DoneCommand implements Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks) {
        // do tasklist stuff here
        tasks.markAsDone(index);
        // do UI stuff here
        Ui.printDone("     " + tasks.getTask(index).toString() + "\n");
        // do storage stuff here
        // tbc
    }

    @Override
    public boolean setIsFinished() {
        return false;
    }
}
