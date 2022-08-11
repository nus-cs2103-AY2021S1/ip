package duke;

import java.util.List;

public class HandleException {

    /**
     * Constructor of HandleException.
     */
    public HandleException() {}


    /**
     * Handle Exceptions when running the program.
     *
     * @param et  ExceptionType object to initialize DukeException object.
     * @return  List of Strings that represent the DukeException object.
     */
    public static List<String> handleException(DukeException.ExceptionType et) {
        DukeException de = new DukeException(et);
        return de.toArrList();
    }

}