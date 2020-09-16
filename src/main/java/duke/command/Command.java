package duke.command;

import duke.DukeException;

/**
 * Command can be executed with a String response. Can also call
 * continueDuke() to determine if Duke has to stop running after
 * this command is completed.
 */
public interface Command {

    String executeWithResponse() throws DukeException;

    boolean continueDuke();

}
