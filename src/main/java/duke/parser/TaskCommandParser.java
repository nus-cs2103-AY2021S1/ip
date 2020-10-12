package duke.parser;

import duke.DukeException;

public interface TaskCommandParser {
    String checkIfValid() throws DukeException;
}
