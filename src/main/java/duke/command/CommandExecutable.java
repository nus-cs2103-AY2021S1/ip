package duke.command;

import org.apache.commons.cli.CommandLine;

import duke.exception.DukeException;
import duke.exception.DukeParseException;
import duke.task.TaskList;
import duke.ui.Ui;

@FunctionalInterface
interface CommandExecutable {
    void run(TaskList taskList, Ui ui, CommandLine arguments) throws DukeException, DukeParseException;
}
