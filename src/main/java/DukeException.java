/**
 * DukeException represents exceptions specific to Duke, which is used by Duke to handle errors
 */
public class DukeException extends Exception{
    DukeException (String errorMessage) {
        super(errorMessage);
    }
}
