package main.java;

public abstract class Command {
    public static String BYE_COMMAND = "bye";
    public static String LIST_COMMAND = "list";
    public static String DONE_COMMAND = "done";
    public static String DELETE_COMMAND = "delete";
    public static String TODO_COMMAND = "todo";
    public static String DEADLINE_COMMAND = "deadline";
    public static String EVENT_COMMAND = "event";
    public static String TASK_AFTER_COMMAND = "taskafter";

    private boolean isExit;

    Command() {
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public void exit() {
        this.isExit = true;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
