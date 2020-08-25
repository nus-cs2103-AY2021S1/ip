public abstract class Command {
    TaskList tasks;
    boolean isExit;

    public Command(TaskList tasks) {
        this.tasks = tasks;
        this.isExit = false;
    }

    public void execute() throws DukeException { }
}
