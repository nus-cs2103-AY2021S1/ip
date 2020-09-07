package duke.command;

import org.apache.commons.cli.CommandLine;

import duke.Context;
import duke.exception.DukeException;

/**
 * Lambda type for executable commands to be launched by the parser.
 */
@FunctionalInterface
public interface CommandExecutable {
    void run(Context context, CommandLine arguments) throws DukeException;
}
