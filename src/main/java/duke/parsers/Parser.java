package duke.parsers;

import duke.exceptions.DukeException;

/**
 * Parser that parses information into type T.
 */
public interface Parser<T> {
    T parse() throws DukeException;
}
