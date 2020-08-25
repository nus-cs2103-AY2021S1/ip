import java.io.IOException;
import java.time.LocalDate;

abstract class Command {
    protected boolean exit;
    private static ExitCommand exitCommand = new ExitCommand();
    private static ListCommand listCommand = new ListCommand();
    private String type;
    private String task;
    private LocalDate date;


    static Command parse(String command) throws InvalidInput {
        if (command.equals("bye")) {
            return exitCommand;
        } else if (command.equals("list")) {
            return listCommand;
        } else if (command.contains("done ")) {
            int task = Integer.parseInt(command.replace("done ", "")) - 1;
            return new doneCommand(task);
        } else if (command.contains("delete ")) {
            int task = Integer.parseInt(command.replace("delete ", "")) - 1;
            return new deleteCommand(task);
        } else {
            //actual entry
            String postFix = command.split(" ", 2)[1];
            String preFix = command.split(" ", 2)[0];
            if (preFix.equals("todo")) {
                return new todoCommand(postFix);
            } else if (preFix.equals("deadline")) {
                return new deadlineCommand(postFix);
            } else if (preFix.equals("event")) {
                return new eventCommand(postFix);
            } else {
                throw new InvalidInput();
            }
        }
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public boolean isExit() {
        return exit;
    }
}

class ExitCommand extends Command {
    ExitCommand() {
        super();
        this.exit = true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printf("Here are the tasks in your list:\n" + tasks.toString());
    }
}

class doneCommand extends Command {
    int doneTask;
    doneCommand(int task) {
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

class deleteCommand extends Command {
    int deleteTask;
    deleteCommand(int task) {
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

class todoCommand extends Command {
    String task;
    todoCommand(String toParse) {
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

class deadlineCommand extends Command {
    String task;
    String deadline;
    deadlineCommand(String toParse) {
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

class eventCommand extends Command {
    String task;
    String at;
    eventCommand(String toParse) {
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