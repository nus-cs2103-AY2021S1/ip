import java.io.IOException;

public class Parser {
    private DukeStorage storage;
    private Ui ui;
    private TaskList taskList;

    private boolean isDukeOn = true; // flag to indicate duke is ready to receive any query

    public Parser(DukeStorage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    public boolean isDukeOn() {
        return this.isDukeOn;
    }

    // main driver function for duke to tackle commands
    public void askDuke(String command, String afterCommand) throws DukeException {
        Command commandType;
        try {
            commandType = Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            commandType = Command.UNIDENTIFIED;
        }

        // handle all different commands using switch and enum instead; organised the methods
        // to make the code look neater
        switch (commandType) {
        case BYE:
            exit();
            break;
        case LIST:
            displayList();
            break;
        case DONE:
            recordDoneTask(afterCommand);
            break;
        case TODO:
            recordToDoTask(afterCommand);
            break;
        case DEADLINE:
            recordDeadlineTask(afterCommand);
            break;
        case EVENT:
            recordEventTask(afterCommand);
            break;
        case DELETE:
            deleteTask(afterCommand);
            break;
        case UNIDENTIFIED:
            // if a bad command is thrown at Duke
            throw new DukeException("Please key in a command I understand!");
        }
    }

    private void exit() {
        isDukeOn = false;
        try {
            storage.saveStorage(taskList.getTasks());
        } catch (IOException ex) {
            System.out.println("Error in saving!");
            ex.printStackTrace();
        }
        ui.format("Bye. Hope to see you again soon!");
    }

    private void addOnToList(Task task) {
        taskList.addTask(task);
        ui.format("Got it. I've added this task:\n" + task +  "\n"
                + "Now you have " + taskList.tasksSize() + " tasks in the list.");
    }

    private void displayList() {
        StringBuilder sb = new StringBuilder();
        int len = taskList.tasksSize();
        if (len == 0) {
            sb.append("No tasks!");
            ui.format(sb.toString());
            return;
        }
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < len; i++) {
            sb.append(i + 1 + "." + taskList.get(i) + "\n");
        }
        ui.format(sb.toString());
    }

    private void recordDoneTask(String afterCommand) throws DukeException {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= taskList.tasksSize() || taskNo < 0) {
            throw new DukeException("Please enter a valid task no!");
        }
        taskList.markTaskCompleted(taskNo);
        displayCompletedTask(taskList.get(taskNo));
    }

    private void displayCompletedTask(Task task) {
        ui.format("Nice! I've marked this task as done:\n" + task);
    }

    private void recordToDoTask(String afterCommand) throws DukeException {
        // needs an after command
        if (afterCommand == null) {
            throw new DukeException("Please do not leave the todo description empty!");
        }
        addOnToList(new ToDo(afterCommand));
    }

    private void recordDeadlineTask(String afterCommand) throws DukeException {
        // needs an after command
        if (afterCommand == null) {
            throw new DukeException("Please do not leave the deadline description empty!");
        }
        // first chunk is the deadline details, second chunk is by when
        String[] splittedDeadline = afterCommand.split("/");

        // teach the user the format for the deadline
        if (splittedDeadline.length == 1) {
            throw new DukeException("Format of deadline recording: deadline keyword" +
                    ", deadline instructions, forward slash, by keyword with a colon, specific date/time)"
                    + "\n e.g. deadline return book /by Sunday");
        }

        String details = splittedDeadline[0].trim();
        String by = splittedDeadline[1].split("by", 2)[1].trim();
        addOnToList(new Deadline(details, by));
    }

    private void recordEventTask(String afterCommand) throws DukeException {
        // needs an after command
        if (afterCommand == null) {
            throw new DukeException("Please do not leave the event description empty!");
        }
        // first chunk is the event details, second chunk is at where
        String[] splittedEvent = afterCommand.split(("/"));

        // teach the user the format for the deadline
        if (splittedEvent.length == 1) {
            throw new DukeException("Format of event recording: event keyword" +
                    ", event instructions, forward slash, at keyword with a colon, start/end time)"
                    + "\n e.g. project meeting /at Mon 2-4pm");
        }

        String details = splittedEvent[0].trim();
        String at = splittedEvent[1].split("at", 2)[1].trim();
        addOnToList(new Event(details, at));
    }

    private void deleteTask(String afterCommand) throws DukeException {
        // needs a valid number
        int taskNo = Integer.parseInt(afterCommand) - 1;
        if (taskNo >= taskList.tasksSize() || taskNo < 0) {
            throw new DukeException("Please enter a valid task no!");
        }
        displayDeletedTask(taskNo);
    }

    private void displayDeletedTask(int index) {
        ui.format("Noted. I've removed this task:\n" + taskList.get(index) + "\n"
                + "Now you have " + (taskList.tasksSize() - 1) + " tasks in the list.");
        taskList.delete(index);
    }
}
