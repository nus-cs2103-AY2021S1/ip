package duke.command;

import java.util.ArrayList;
import java.util.List;

import duke.Main;
import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;


/**
 * Corresponds to commands the users wants to execute.
 */
public abstract class Command {


    /**
     * Uses polymorphism to execute the methods relevant to the Command.
     *
     * @param tasks       TaskList object which contains the updated list.
     * @param ui          The reference to ui, for updating the ui.
     * @param fileHandler Contains methods to update the file.
     * @throws DukeException Throws DukeException which must be caught by the method.
     */
    public abstract void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException;


    public static CommandType containsKeyword(String commandKeyword) {
        if (contains(ClearCommand.aliases, commandKeyword)) {
            return CommandType.CLEAR;
        } else if (contains(DeadlineCommand.aliases, commandKeyword)) {
            return CommandType.DEADLINE;
        } else if (contains(DeleteCommand.aliases, commandKeyword)) {
            return CommandType.DELETE;
        } else if (contains(DoneCommand.aliases, commandKeyword)) {
            return CommandType.DONE;
        } else if (contains(EventCommand.aliases, commandKeyword)) {
            return CommandType.EVENT;
        } else if (contains(ExitCommand.aliases, commandKeyword)) {
            return CommandType.EXIT;
        } else if (contains(FindCommand.aliases, commandKeyword)) {
            return CommandType.FIND;
        } else if (contains(ListCommand.aliases, commandKeyword)) {
            return CommandType.LIST;
        } else if (contains(TodoCommand.aliases, commandKeyword)) {
            return CommandType.TODO;
        } else {
            Main.getWindow().showsToDialog("The keyword got is: " + commandKeyword);
            return CommandType.UNKNOWN;
        }

    }


    private static boolean contains(List<String> aliases, String commandKeyword) {
        for (String alias : aliases) {
            if (alias.equals(commandKeyword)) {
                return true;
            }
        }
        return false;
    }


    public static void initialise() {
        ClearCommand.aliases = new ArrayList<>();
        ClearCommand.aliases.add("c");
        ClearCommand.aliases.add("clear");

        DeadlineCommand.aliases = new ArrayList<>();
        DeadlineCommand.aliases.add("de");
        DeadlineCommand.aliases.add("deadline");

        DeleteCommand.aliases = new ArrayList<>();
        DeleteCommand.aliases.add("d");
        DeleteCommand.aliases.add("delete");

        DoneCommand.aliases = new ArrayList<>();
        DoneCommand.aliases.add("do");
        DoneCommand.aliases.add("done");

        EventCommand.aliases = new ArrayList<>();
        EventCommand.aliases.add("e");
        EventCommand.aliases.add("event");

        ExitCommand.aliases = new ArrayList<>();
        ExitCommand.aliases.add("b");
        ExitCommand.aliases.add("bye");

        FindCommand.aliases = new ArrayList<>();
        FindCommand.aliases.add("f");
        FindCommand.aliases.add("find");

        ListCommand.aliases = new ArrayList<>();
        ListCommand.aliases.add("l");
        ListCommand.aliases.add("list");

        TodoCommand.aliases = new ArrayList<>();
        TodoCommand.aliases.add("t");
        TodoCommand.aliases.add("todo");

    }
}
