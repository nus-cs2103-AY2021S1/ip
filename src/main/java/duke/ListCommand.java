package duke;

public class ListCommand implements Command{
    public ListCommand() {}
    @Override
    public void execute(TaskList tasks) {
        // Do UI stuff here
        Ui.printList(tasks.printTodoList());
        // Do storage stuff here
    }
}
