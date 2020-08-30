import java.io.IOException;
import java.time.LocalDate;

/**
 * Parses commands as well as provide differnet type of subclasses
 * to handle different types of commands
 */

abstract class Command {
    protected boolean exit;
    static private ExitCommand exitCommand = new ExitCommand();
    static private ListCommand listCommand = new ListCommand();
    private String type;
    private String task;
    private LocalDate date;

    /**
     * Takes a String command and parses it to return appropriate
     * Command object
     * @param command String command
     * @return appropriate Command object
     * @throws InvalidInput if String command not available
     */


    static Command parse(String command) throws InvalidInput {
        if (command.equals("bye")) {
            return exitCommand;
        } else if (command.equals("list")) {
            return listCommand;
        } else if (command.contains("done ")) {
            int task = Integer.parseInt(command.replace("done ", "")) - 1;
            return new DoneCommand(task);
        } else if (command.contains("delete ")) {
            int task = Integer.parseInt(command.replace("delete ", "")) - 1;
            return new DeleteCommand(task);
        } else if (command.contains("find ")) {
            String key = command.replace("find ", "");
            return new FindCommand(key);
        } else {
            //actual entry
            String postFix = command.split(" ", 2)[1];
            String preFix = command.split(" ", 2)[0];
            if (preFix.equals("todo")) {
                return new TodoCommand(postFix);
            } else if (preFix.equals("deadline")) {
                return new DeadlineCommand(postFix);
            } else if (preFix.equals("event")) {
                return new EventCommand(postFix);
            } else {
                throw new InvalidInput();
            }
        }
    }

    /**
     * Executes the current command
     * @param tasks Task list
     * @param ui User interface
     * @param storage Storage handling
     * @throws IOException may be thrown while saving to storage
     */

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Returns if the command is supposed to cause an exit from UI
     * @return True/False to exit
     */
    public boolean isExit() {
        return exit;
    }
}

/**
 * An exit command
 */
class ExitCommand extends Command {
    ExitCommand() {
        super();
        this.exit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}

/**
 * A list command to list all tasks
 */
class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printf("Here are the tasks in your list:\n" + tasks.toString());
    }
}

/**
 * A done command to mark a given task number as done
 */
class DoneCommand extends Command {
    private int doneTask;

    DoneCommand(int task) {
        super();
        this.doneTask = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.done(this.doneTask);
        ui.printf("Nice! I've marked this task as done:\n" + tasks.get(this.doneTask));
        storage.saveFile(tasks);
    }
}

/**
 * A delete command to delete a given task number
 */
class DeleteCommand extends Command {
    private int deleteTask;

    DeleteCommand(int task) {
        super();
        this.deleteTask = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printf("Noted. I've removed this task:\n" + tasks.get(this.deleteTask));
        tasks.delete(this.deleteTask);
        storage.saveFile(tasks);
        ui.printf(tasks.taskCount());
    }
}

/**
 * A Todo command to add a todo Task to the TaskList
 */
class TodoCommand extends Command {
    private String task;

    TodoCommand(String toParse) {
        this.task = toParse;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.addTodo(this.task);
        ui.printf("Got it. I've added this task:\n" + task.toString());
        ui.printf(tasks.taskCount());
        storage.saveFile(tasks);
    }
}

/**
 * A Deadline command to add a Deadline Task to the TaskList
 */
class DeadlineCommand extends Command {
    private String task;
    private String deadline;

    DeadlineCommand(String toParse) {
        String[] split = toParse.split(" /by ");
        this.task = split[0];
        this.deadline = split[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.addDeadline(this.task, this.deadline);
        ui.printf("Got it. I've added this task:\n" + task);
        ui.printf(tasks.taskCount());
        storage.saveFile(tasks);
    }
}

/**
 * An Event command to add an Event Task to the TaskList
 */
class EventCommand extends Command {
    private String task;
    private String at;
    EventCommand(String toParse) {
        String[] split = toParse.split(" /at ");
        this.task = split[0];
        this.at = split[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task task = tasks.addEvent(this.task, this.at);
        ui.printf("Got it. I've added this task:\n" + task);
        ui.printf(tasks.taskCount());
        storage.saveFile(tasks);
    }
}

class FindCommand extends Command {
    private String key;

    FindCommand(String key) {
        this.key = key;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printf("Here are the matching tasks in your list:\n");
        ui.printf(tasks.find(this.key));
    }
}