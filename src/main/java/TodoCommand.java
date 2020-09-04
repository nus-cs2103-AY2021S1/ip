public class TodoCommand extends Command {

    TodoCommand(String str) {
        super(str);
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String s;
        try {
            Task newTask = new Todo(str.substring(5));
            list.getList().add(newTask);
            s = ui.printAddTask(newTask, list.getList().size());
        } catch (IndexOutOfBoundsException e) {
            s = ui.printNoDescription();
        }
        return s;
    }
}
