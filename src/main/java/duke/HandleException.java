package main.java.duke;

public class HandleException {

    /**
     * Constructor of HandleException.
     */
    public HandleException() {}


    /**
     * Handle Exceptions when running the program.
     *
     * @param et  ExceptionType object to initialize DukeException object.
     */
    public static void handleException(DukeException.ExceptionType et) {
        DukeException de = new DukeException(et);
        System.out.println(de);
    }

}