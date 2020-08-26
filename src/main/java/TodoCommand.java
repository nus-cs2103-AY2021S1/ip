public class TodoCommand extends Command{

    TodoCommand(String str) {
        super(str);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            Task newTask = new Todo(str.substring(5));
            list.getList().add(newTask);
            ui.printAddTask(newTask, list.getList().size());
        } catch (IndexOutOfBoundsException e) {
            ui.printNoDescription();
        }
    }
}
