/**
 * A kind of DukeException which specify invalid event description.
 */
public class InvalidEventDescripDukeException extends DukeException{
    InvalidEventDescripDukeException() {
        super(" ☹ OOPS!!! The description of a event cannot be empty.");
    }
}

