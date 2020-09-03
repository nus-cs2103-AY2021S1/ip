package dobby;

import dobby.command.ByeCommand;
import dobby.command.DeadlineCommand;
import dobby.command.DeleteCommand;
import dobby.command.DoneCommand;
import dobby.command.EventCommand;
import dobby.command.FindCommand;
import dobby.command.FindtypeCommand;
import dobby.command.HelpCommand;
import dobby.command.InvalidInput;
import dobby.command.ListCommand;
import dobby.command.ScheduledCommand;
import dobby.command.TodoCommand;

/**
 * Parses the input given by the user and interacts with TaskList accordingly
 */
public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the chat bot reply or the error message depending on the input
     * @param text text inputted by user
     * @return message reply to user based on input
     * @throws DobbyException based on conditions with customised message
     */
    public String getMessage(String text) throws DobbyException {
        try {
            String command = text.trim();

            if (text.indexOf(' ') > 0) {
                command = text.substring(0, text.indexOf(' '));
            }

            switch(command.toLowerCase()) {
            case "todo":
                return (new TodoCommand()).parseInput(tasks, text);
            case "deadline":
                return (new DeadlineCommand()).parseInput(tasks, text);
            case "event":
                return (new EventCommand()).parseInput(tasks, text);
            case "list":
                return (new ListCommand()).parseInput(tasks, text);
            case "done":
                return (new DoneCommand()).parseInput(tasks, text);
            case "delete":
                return (new DeleteCommand()).parseInput(tasks, text);
            case "scheduled":
                return (new ScheduledCommand()).parseInput(tasks, text);
            case "find":
                return (new FindCommand()).parseInput(tasks, text);
            case "findtype":
                return (new FindtypeCommand()).parseInput(tasks, text);
            case "bye":
                return (new ByeCommand()).parseInput(tasks, text);
            case "help":
                return (new HelpCommand()).parseInput(tasks, text);
            default:
                return (new InvalidInput()).parseInput(tasks, text);
            }
        } catch (DobbyException e) {
            return e.getMessage();
        }
    }
}
