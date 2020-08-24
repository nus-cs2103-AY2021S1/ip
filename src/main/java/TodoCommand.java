public class TodoCommand extends Command {
    private String[] nextCommandArr;
    
    public TodoCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            Todo newTodo = new Todo(nextCommandArr[1]);
            taskList.add(newTodo);
            ui.addTaskText(newTodo, taskList);
        } catch (Exception e) {
            throw new DukeException("The description of a todo cannot be empty~");
        }
        
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
