package duke.command;

import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {
    private static final String ALL = "all";
    private String command;

    public HelpCommand(String command) {
        this.command = command.toLowerCase();
    }

    public HelpCommand() {
        this.command = ALL;
    }

    public String generateTodoMessage() {
        return "todo command\n"
                + "    Create a todo task.\n"
                + "    Format: todo <task name>\n"
                + "    Eg. todo homework1\n";
    }

    public String generateEventMessage() {
        return "event command\n"
                + "    Create an event task.\n"
                + "    Format: event <task name> /at <date>\n"
                + "    Eg. event meeting1 /at 2020-04-16\n"
                + "    <date> MUST be in YYYY-MM-DD format\n";
    }

    public String generateDeadlineMessage() {
        return "deadline command\n"
                + "    Create a deadline task.\n"
                + "    Format: deadline <task name> /by <date>\n"
                + "    Eg. deadline meeting1 /by 2020-04-16\n"
                + "    <date> MUST be in YYYY-MM-DD format\n";
    }

    public String generateByeMessage() {
        return "bye command\n"
                + "    Stops this programme.\n";
    }

    public String generateDeleteMessage() {
        return "delete command\n"
                + "    Deletes the task specified\n"
                + "    Format: delete <task number>\n"
                + "    Eg. delete 3\n"
                + "    <task number> is obtained by listing tasks\n";
    }

    public String generateDoneMessage() {
        return "done command\n"
                + "    Marks the task specified as done\n"
                + "    Format: done <task number>\n"
                + "    Eg. done 3\n"
                + "    <task number> is obtained by listing tasks\n";
    }

    public String generateListMessage() {
        return "list command\n"
                + "    Lists all tasks out in order of creation\n"
                + "    Format: list\n"
                + "    Eg. list\n";
    }

    public String generateHelpMessage() {
        return "help command\n"
                + "    Prints guide for commands.\n"
                + "    Format: help <command> , help\n"
                + "    Eg. help, help todo\n";
    }

    public String generateFindMessage() {
        return "find command\n"
                + "    Lists all tasks that match a given search term\n"
                + "    Format: find <search term>\n"
                + "    Eg. find homework\n"
                + "    This is a case insensitive search and does a\n"
                + "    keyword search on all task names.\n";
    }

    public String generateAllMessage() {
        return generateByeMessage() + "\n"
                + generateDeadlineMessage() + "\n"
                + generateDeleteMessage() + "\n"
                + generateDoneMessage() + "\n"
                + generateEventMessage() + "\n"
                + generateFindMessage() + "\n"
                + generateHelpMessage() + "\n"
                + generateListMessage() + "\n"
                + generateTodoMessage();
    }

    public String execute(TaskList taskList, Ui ui) {
        switch (command) {
        case Parser.BYE:
            return ui.print(generateByeMessage());
        case Parser.DEADLINE:
            return ui.print(generateDeadlineMessage());
        case Parser.DELETE:
            return ui.print(generateDeleteMessage());
        case Parser.DONE:
            return ui.print(generateDoneMessage());
        case Parser.EVENT:
            return ui.print(generateEventMessage());
        case Parser.FIND:
            return ui.print(generateFindMessage());
        case Parser.LIST:
            return ui.print(generateListMessage());
        case Parser.TODO:
            return ui.print(generateTodoMessage());
        case Parser.HELP:
            return ui.print(generateHelpMessage());
        default:
            return ui.print(generateAllMessage());
        }
    }
}
