package duke.exception;

import duke.main.Format;

/**
 * This class consists all exceptions related to Duke input.
 */
public class DukeException {
    public static void classCastException() {
        System.out.println(new Format<>(Exceptions.CLASSCASTEXCEPTION.toString()));
    }

    public static void numberFormatException() {
        System.out.println(new Format<>(Exceptions.NUMBERFORMATEXCEPTION.toString()));
    }

    public static void numberExcessException() {
        System.out.println(new Format<>(Exceptions.NUMBEREXCESSEXCEPTION.toString()));
    }

    public static void emptyTaskException() {
        System.out.println(new Format<>(Exceptions.EMPTYTASKEXCEPTION.toString()));
    }

    public static void inputFormatException() {
        System.out.println(new Format<>(Exceptions.INPUTFORMATEXCEPTION.toString()));
    }

    public static void fileException() {
        System.out.println(new Format<>(Exceptions.FILEEXCEPTION.toString()));
    }

    public static void readLineException() {
        System.out.println(new Format<>(Exceptions.READLINEEXCEPTION.toString()));
    }


    public static void timeFormatException() {
        System.out.println(new Format<>(Exceptions.TIMEFORMATEXCEPTION.toString()));
    }
}
