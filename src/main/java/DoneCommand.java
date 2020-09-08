import java.util.Scanner;

public class DoneCommand extends Command {

    public DoneCommand() {
        super(false);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws EmptyListException,
            NonExistentTaskException, InvalidDoneCommandException{
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        ui.printDonePrompt();
        int taskNum = Integer.parseInt(ui.readCommand());
        if (taskNum > taskList.getTaskListSize()) {
            throw new NonExistentTaskException();
        } else if (taskList.getTask(taskNum - 1).isDone()) {
            throw new InvalidDoneCommandException();
        } else {
            taskList.getTask(taskNum - 1).markAsDone();
            ui.printDoneAcknowledgement(taskList, taskNum);
        }
        Storage.saveTaskChanges(taskList);
    }
}