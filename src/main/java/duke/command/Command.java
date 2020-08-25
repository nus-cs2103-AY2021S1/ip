package duke.command;

import duke.*;
import duke.exception.*;

import java.io.IOException;

abstract public class Command {

    public abstract void execute(Tasklist tasklist, UserInterface ui) throws DukeListException, DukeIndexException, IOException;

}
