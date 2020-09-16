package duke.ui;

import duke.task.Task;

import java.util.List;

public abstract class Ui {
    protected static final String LINE = "_________________________________________________";
    protected static final String GREETING_MESSAGE = "\n Quack! I am Duck"
            + "\n How can I help you today?\n";
    protected static final String EXIT_MESSAGE = "\n Waddling off now. See you soon! \n";

    public abstract void processError(String errorMessage);

    public abstract void processAddMessage(Task task, int count);

    public abstract void processDeleteMessage(Task taskToDelete, int count);

    public abstract void processDoneMessage(Task task);

    public abstract void processClose();

    public abstract void processResultTaskList(List<Task> resultTaskList);

    public abstract void listStoredTasks(List<Task> storedTasks);
}
