package duke.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import duke.exception.DukeException;
import duke.exception.DukeParseException;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Stores all the commands to be run.
 */
public enum Command {
    TODO("todo",
        CommandLibrary.TODO_COMMAND),
    DEADLINE("deadline",
        CommandLibrary.DEADLINE_COMMAND,
        Option.builder("by")
            .required(true)
            .hasArg()
            .build()),
    EVENT("event",
        CommandLibrary.EVENT_COMMAND,
        Option.builder("at")
            .required(true)
            .hasArg()
            .build()),
    LIST("list",
        CommandLibrary.LIST_COMMAND),
    BYE("bye",
        CommandLibrary.BYE_COMMAND),
    DONE("done",
        CommandLibrary.DONE_COMMAND),
    DELETE("delete",
        CommandLibrary.DELETE_COMMAND),
    FIND("find",
        CommandLibrary.FIND_COMMAND);
    private final CommandExecutable exec;
    private final Options options;
    private final String name;

    Command(String name, CommandExecutable exec, Option ... optionArray) {
        this.name = name;
        this.exec = exec;
        this.options = new Options();
        for (Option option : optionArray) {
            options.addOption(option);
        }
    }

    public void dispatch(TaskList taskList, Ui ui, CommandLine args) throws DukeException, DukeParseException {
        this.exec.run(taskList, ui, args);
    }

    public Options getOptions() {
        return this.options;
    }

    public static Command getCommandByName(String name) throws DukeException {
        for (Command command : Command.values()) {
            if (command.name.equals(name)) {
                return command;
            }
        }
        throw DukeException.Errors.UNKNOWN_COMMAND.create();
    }
}
