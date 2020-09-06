package duke.command;

import org.apache.commons.cli.CommandLine;

import duke.Context;
import duke.exception.DukeException;

@FunctionalInterface
interface CommandExecutable {
    void run(Context context, CommandLine arguments) throws DukeException;
}
