package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Shows all the valid commands recognized by Duke.\n"
        + "Example: " + COMMAND_WORD;

    /**
     * Guides the user on how to use an individual command or all commands if none specified.
     *
     * @param input Command specified by the user.
     * @throws DukeException if Invalid command entered after help.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) throws DukeException {
        if (input.contains(" ")) {
            String commandGiven = input.trim().split(" ")[1];
            switch (commandGiven) {
            case "bye":
                System.out.println(ByeCommand.COMMAND_USAGE);
                break;
            case "list":
                System.out.println(ListCommand.COMMAND_USAGE);
                break;
            case "done":
                System.out.println(DoneCommand.COMMAND_USAGE);
                break;
            case "delete":
                System.out.println(DeleteCommand.COMMAND_USAGE);
                break;
            case "clear":
                System.out.println(ClearCommand.COMMAND_USAGE);
                break;
            case "deadline":
                System.out.println(DeadlineCommand.COMMAND_USAGE);
                break;
            case "event":
                System.out.println(EventCommand.COMMAND_USAGE);
                break;
            case "todo":
                System.out.println(TodoCommand.COMMAND_USAGE);
                break;
            case "find":
                System.out.println(FindCommand.COMMAND_USAGE);
                break;
            case "undo":
                System.out.println(UndoCommand.COMMAND_USAGE);
                break;
            default:
                throw new DukeException("\u2639 Whoops, " + '"' + commandGiven + '"' + " is not a valid command for "
                    + "help! \n"
                    + "Try entering 'help' to show all commands.");
            }
        } else {
            System.out.println("Here's how to use all the commands!");
            System.out.println();
            int counter = 1;
            System.out.println(counter + ") " + DeadlineCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + EventCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + TodoCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + ByeCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + ListCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + FindCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + ClearCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + UndoCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + DoneCommand.COMMAND_USAGE);
            System.out.println();
            counter++;
            System.out.println(counter + ") " + DeleteCommand.COMMAND_USAGE);
        }
    }
}
