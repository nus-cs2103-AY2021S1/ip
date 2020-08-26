package main.java;

public class HandleException {

    public HandleException() {

    }

    public static void handleException(DukeException.ExceptionType et) {
        DukeException de = new DukeException(et);
        System.out.println(de);
    }
}
