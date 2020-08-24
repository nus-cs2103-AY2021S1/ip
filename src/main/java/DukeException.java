import java.io.Serializable;

class DukeException extends Exception implements Serializable {

    DukeException(String s) {
        super(s);
    }
}
