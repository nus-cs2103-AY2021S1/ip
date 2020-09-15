package duke;

import java.io.Serializable;

public class DukeException extends Exception implements Serializable {

    DukeException(String s) {
        super(s);
    }
}
