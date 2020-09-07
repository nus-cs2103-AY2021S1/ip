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
     * @throws InvalidInputException if String command not available
     */
    static Command parse(String command) throws InvalidInputException, EmptyTodoException {
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
        } else if (command.equals("load")) {
            return new LoadFileCommand();
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
                throw new InvalidInputException();
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
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Returns if the command is supposed to cause an exit from UI
     * @return True/False to exit
     */
    public boolean isExit() {
        return exit;
    }
}

