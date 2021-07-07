package duke.command;

import duke.storage.Storage;

public class HelpCommand extends Command {

    // Command types
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String FIND = "find";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";
    private static final String HELP = "help";
    private static final String COMMAND_LIST = "commands";


    private String helpType;

    /**
     * Constructor for a HelpCommand.
     * @param input Optional argument for the help command
     */
    public HelpCommand(String... input) {
        if (input.length > 0) {
            helpType = input[0];
        } else {
            helpType = HELP;
        }
    }

    @Override
    public String execute(Storage storage) {
        String reply = "Welcome to Duke's Help System!\n"
                + "\n"
                + "To find a list of all commands, type \"help commands\".\n"
                + "To find out more about a command, type \"help <name of command>\".\n"
                + "    Example: \"help list\" will return the readme for the List command.";

        switch (helpType) {
        case BYE:
            reply = "bye - Closes the program.";
            break;
        case LIST:
            reply = "list - Shows the list of all Tasks added.\n\n"
                    + "Example usage:\n \"list\" -> Shows all the Tasks added. ";
            break;
        case FIND:
            reply = "find <keyword> - Shows a list of Tasks which have the keyword.\n\n"
                    + "Example usage:\n \"find task\" -> Returns a list of Tasks with the word \"task\" in their name.";
            break;
        case DONE:
            reply = "done <index> - Marks the task at the index of the list as completed.\n\n"
                    + "Example usage:\n \"done 1\" -> Marks the first entry of the list as completed.";
            break;
        case DELETE:
            reply = "delete <index> - Deletes the task at the index of the list.\n\n"
                    + "Example usage:\n \"delete 1\" -> Removes the first entry of the list.";
            break;
        case DEADLINE:
            reply = "deadline <name> /by <YYYY-MM-DD> -> Adds a Deadline Task with this name at this time.\n\n"
                    + "Example usage:\n \"deadline Submission /by 2020-09-05\""
                    + "-> Adds a Deadline Task with the name \"Submssion\" with the date"
                    + " 5th September 2020";
            break;
        case EVENT:
            reply = "event <name> /at <YYYY-MM-DD> -> Adds an Event Task with this name at this time.\n"
                    + "\n"
                    + "Example usage: \"event Concert /at 2020-09-05\" -> Adds an Event Task with the name "
                    + "\"Concert\" with the date\n"
                    + " 5th September 2020 ";
            break;
        case TODO:
            reply = "todo <name> - Adds a Todo Task with this name.\n"
                    + "Example usage:\n \"todo Do homework\" -> Adds a Todo task with the name \"Do Homework\".";
            break;
        case COMMAND_LIST:
            reply = "Command List:\n"
                    + "    list - Shows the list of all Tasks added.\n"
                    + "    find <keyword> - Shows a list of Tasks which have the keyword.\n"
                    + "    done <index> - Marks the task at the index of the list as completed.\n"
                    + "    delete <index> - Deletes the task at the index of the list.\n"
                    + "    todo <name> - Adds a Todo Task with this name.\n"
                    + "    event <name> /at <YYYY-MM-DD> - Adds an Event Task with this name at this time.\n"
                    + "    deadline <name> /by <YYYY-MM-DD> - Adds a Deadline Task with this name at this time.\n"
                    + "    bye - Closes the program.";
            break;
        default:
            // HELP is also included here.
            // Does not modify the reply as initialised already.
            break;
        }
        return reply;
    }
}
