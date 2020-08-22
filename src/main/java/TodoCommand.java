public class TodoCommand extends Command{

    private String command;


    public TodoCommand(String command) {
        this.command = command;
    }

    @Override
    protected void execute(TaskList tasks, UI dukeUI) throws InvalidTaskDescriptionException {
        try {
            String[] todoDetails = this.command.split(" ", 2);
            Todo newTodo = new Todo(todoDetails[1]);
            tasks.addTask(newTodo);
            dukeUI.addTask(tasks, newTodo);
        } catch (IndexOutOfBoundsException e){
            throw new InvalidTaskDescriptionException();
        }
    }

    protected boolean isExit() {
        return false;
    }
}
