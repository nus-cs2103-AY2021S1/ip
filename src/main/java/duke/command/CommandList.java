package duke.command;

import duke.exceptions.DukeNoInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TextParser;
import duke.tasks.ToDo;



/**
 * CommandList to store commands supported by duke
 * Software pattern referenced from https://github.com/JoeyChenSmart/ip
 * Implementation is done by myself with reference to this example
 */
class CommandList {
    private static final String ALLHELP = "\t Need some help huh?\n"
            + "Heres a list of my commands!\n"
            + "- 'bye' to close the application\n"
            + "- 'list' to list the current list of duke.tasks and their statuses\n"
            + "- 'done' to set a task as done\n"
            + "- 'find' to find a task using regex or a query text string\n"
            + "- 'todo' to list a untimed task\n"
            + "- 'deadline' to list a timed deadline task, please structure with "
            + "[deadline <task name> -by dd-MM-YYYY]\n"
            + "- 'event' to list a timed event task, please structure with "
            + "[event <task name> -at dd-MM-YYYY]\n"
            + "- 'help' to list these commands again help <command> for specific commands\n";
    private static final TextParser PARSER = new TextParser();
    static final CommandExecuter DEADLINE = (taskManager, ui, arguments) -> {
        if (arguments.length == 0) {
            throw new DukeNoInputException();
        }
        assert arguments.length == 2 : "The length of the argument should always only be 2"
                + " because of -by seperator";
        ui.systemMessage(taskManager.add(Deadline.createNewDeadline(arguments[0] , arguments[1])));
    };
    static final CommandExecuter TODO = (taskManager, ui, arguments) -> {
        if (arguments[0].isEmpty()) {
            throw new DukeNoInputException();
        }
        assert arguments.length == 1 : "The length of the argument should always only be 1";
        ui.systemMessage(taskManager.add(new ToDo(arguments[0])));
    };
    static final CommandExecuter EVENT = (taskManager, ui, arguments) -> {
        if (arguments.length == 0) {
            throw new DukeNoInputException();
        }
        assert arguments.length == 2 : "The length of the argument should always only be 2"
                + " because of -at seperator";
        ui.systemMessage(taskManager.add(Event.createNewEvent(arguments[0], arguments[1])));
    };
    static final CommandExecuter FIND = (taskManager, ui, arguments) -> {
        ui.systemMessage(taskManager.findTasks(arguments[0]));
    };
    static final CommandExecuter BYE = (taskManager, ui, arguments) -> {
        taskManager.saveTasks();
        ui.close();
    };
    static final CommandExecuter DELETE = (taskManager, ui, arguments) -> {
        System.out.print(arguments[0]);
        ui.systemMessage(taskManager.deleteTask(arguments[0]));
    };
    static final CommandExecuter DONE = (taskManager, ui, arguments) -> {
        ui.systemMessage(taskManager.doTask(arguments[0]));
    };
    static final CommandExecuter LIST = (taskManager, ui, arguments) -> {
        ui.systemMessage(taskManager.listTasks());
    };
    static final CommandExecuter HELP = (taskManager, ui, arguments) -> {
        CommandHelp commandHelp = PARSER.parseHelpCommand(arguments[0]);
        String message = commandHelp.toString();
        if (commandHelp.equals(CommandHelp.ERROR) || commandHelp.equals(CommandHelp.BLANK)) {
            message = ALLHELP;
        }
        ui.systemMessage(message);
    };
}
