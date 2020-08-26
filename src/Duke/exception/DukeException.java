package Duke.exception;

import Duke.main.Formating;

/**
 * This class consists all exceptions related to Duke input.
 */
public class DukeException {

    public static void classCastException() {
        System.out.println(
                new Formating<>(Exceptions.CLASSCASTEXCEPTION.toString())
        );
    }

    public static void numberFormatException() {
        System.out.println(
                new Formating<>(Exceptions.NUMBERFORMATEXCEPTION.toString())
        );
    }

    public static void numberExcessException() {
        System.out.println(
                new Formating<>(Exceptions.NUMBEREXCESSEXCEPTION.toString())
        );
    }

    public static void emptyTaskException() {
        System.out.println(
                new Formating<>(Exceptions.EMPTYTASKEXCEPTION.toString())
        );
    }

    public static void inputFormatException() {
        System.out.println(
                new Formating<>(Exceptions.INPUTFORMATEXCEPTION.toString())
        );
    }

    public static void timeMissingException() {
        System.out.println(
                new Formating<>(Exceptions.NOTIMEEXCEPTION.toString())
        );
    }

    public static void FileException() {
        System.out.println(
                new Formating<>(Exceptions.FILEEXCEPTION.toString())
        );
    }

    public static void ReadLineException() {
        System.out.println(
                new Formating<>(Exceptions.READLINEEXCEPTION.toString())
        );
    }


    public static void timeFormatException() {
        System.out.println(
                new Formating<>(Exceptions.TIMEFORMATEXCEPTION.toString())
        );
    }
}
