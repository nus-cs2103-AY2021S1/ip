package duke.exception;

import duke.tools.Format;

/**
 * This class consists all exceptions related to Duke input.
 */
public class DukeException {


    /**
     * Returns an error message when ClassCastException occurs.
     *
     * @return An error message.
     */
    public static String classCastException() {
        return new Format<>(Exceptions.CLASSCASTEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when NumberFormatException occurs.
     *
     * @return An error message.
     */
    public static String numberFormatException() {
        return new Format<>(Exceptions.NUMBERFORMATEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when user inputs more than
     * one number in command done, delete, or find.
     *
     * @return An error message.
     */
    public static String numberExcessException() {
        return new Format<>(Exceptions.NUMBEREXCESSEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when there is not task detail.
     *
     * @return An error message.
     */
    public static String emptyTaskException() {
        return new Format<>(Exceptions.EMPTYTASKEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when the command cannot be recognized.
     *
     * @return An error message.
     */
    public static String inputFormatException() {
        return new Format<>(Exceptions.INPUTFORMATEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when exceptions related to file occurs.
     *
     * @return An error message.
     */
    public static String fileException() {
        return new Format<>(Exceptions.FILEEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when user input time differently from yyyy-mm-dd.
     *
     * @return An error message.
     */
    public static String timeFormatException() {
        return new Format<>(Exceptions.TIMEFORMATEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when user does not key in content after command find.
     *
     * @return An error message.
     */
    public static String findDetailMissingException() {
        return new Format<>(Exceptions.FINDDETAILMISSINGEXCEPTION.toString()).toString();
    }

    /**
     * Returns an error message when the file already exists during creation.
     *
     * @return An error message.
     */
    public static String fileAlreadyExistException() {
        return new Format<>(Exceptions.FILEALREADYEXIST.toString()).toString();
    }

    /**
     * Returns an error message when the write() method throws IOException.
     *
     * @return An error message.
     */
    public static String fileWritingException() {
        return new Format<>(Exceptions.WRITINGEXCEPTION.toString()).toString();
    }
}
