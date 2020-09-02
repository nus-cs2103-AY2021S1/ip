package duke.exception;

import duke.tools.Format;

/**
 * This class consists all exceptions related to Duke input.
 */
public class DukeException {
    public static String classCastException() {
        return new Format<>(Exceptions.CLASSCASTEXCEPTION.toString()).toString();
    }

    public static String numberFormatException() {
        return new Format<>(Exceptions.NUMBERFORMATEXCEPTION.toString()).toString();
    }

    public static String numberExcessException() {
        return new Format<>(Exceptions.NUMBEREXCESSEXCEPTION.toString()).toString();
    }

    public static String emptyTaskException() {
        return new Format<>(Exceptions.EMPTYTASKEXCEPTION.toString()).toString();
    }

    public static String inputFormatException() {
        return new Format<>(Exceptions.INPUTFORMATEXCEPTION.toString()).toString();
    }

    public static String fileException() {
        return new Format<>(Exceptions.FILEEXCEPTION.toString()).toString();
    }

    public static String readLineException() {
        return new Format<>(Exceptions.READLINEEXCEPTION.toString()).toString();
    }


    public static String timeFormatException() {
        return new Format<>(Exceptions.TIMEFORMATEXCEPTION.toString()).toString();
    }

    public static String findDetailMissingException() {
        return new Format<>(Exceptions.FINDDETAILMISSINGEXCEPTION.toString()).toString();
    }
}
