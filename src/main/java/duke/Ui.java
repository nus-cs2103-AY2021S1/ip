package duke;

import java.util.List;

public abstract class Ui {
    protected static final String LINE = "____________________________________________________";
    protected static final String GREETING_MESSAGE = LINE
            + "\n Quack! I am Duck"
            + "\n How can I help you today?\n" + LINE;
    protected static final String EXIT_MESSAGE = LINE
            + "\n Waddling off now. See you soon! \n" + LINE;

    public abstract void processError(String errorMessage);

    public abstract void processAddMessage(Task task, int count);

    public abstract void displayTaskCount(int numOfTasks);

    public abstract void processDeleteMessage(Task taskToDelete, int count);

    public abstract void processDoneMessage(Task task);

    public abstract void processClose();

    public abstract void processResultTaskList(List<Task> resultTaskList);

    public abstract void listStoredTasks(List<Task> storedTasks);
}
