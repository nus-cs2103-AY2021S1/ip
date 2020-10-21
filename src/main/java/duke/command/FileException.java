package duke.command;

import duke.DukeException;

/**
 * A class of exceptions arising from file-related errors/restrictions.
 */
public class FileException extends DukeException {

    /** An exception thrown when a line in the specified file cannot be parsed. */
    public static final FileException FILE_PARSING_FAILURE = new FileException(
            "Sorry, an error occurred while trying to parse the file.");

    /**
     * An exception thrown when an I/O error occurs while trying to load or create a file at the
     * specified filepath.
     */
    public static final FileException FILE_NOT_FOUND = new FileException(
            "Sorry, the file does not seem to exist.\n"
                    + "Try: CREATE [TASK/FINANCE] [filepath].");

    /**
     * An exception thrown when there is an existing file in the filepath where the new file is to be created.
     */
    public static final FileException FILE_ALREADY_EXISTS = new FileException(
            "Sorry, that filepath already has an existing file.\n"
                    + "Would you like to load that file, or create a file in a different filepath?");

    /**
     * An exception thrown when an I/O error occurs while trying to write to a file at the
     * specified filepath.
     */
    public static final FileException FILE_NO_ACCESS = new FileException(
            "Sorry, I cannot seem to access the file");

    /**
     * An exception thrown when the user commands Duke to load/create a file but does not specify the filepath.
     */
    public static final FileException INVALID_FILE_SPECIFICATION = new FileException(
            "Sorry, please follow this format to specify the file and its type:\n"
                    + "    [CREATE/LOAD] [TASK/FINANCE] [filepath]");

    /**
     * An exception thrown when the user tries to load/create another file when there is already a file
     * currently loaded.
     */
    public static final FileException FILE_ALREADY_LOADED = new FileException(
            "There is currently a file that is already loaded.\n"
                    + "If you would like to load/create a new file, "
                    + "please UNLOAD the current file first.");

    /**
     * An exception thrown when the user tries to perform some operations without loading a file first.
     */
    public static final FileException FILE_NOT_LOADED = new FileException(
            "Sorry, no files has been loaded yet. You need to either CREATE or LOAD a file first.");

    /**
     * An exception thrown when the user tries to perform operations that are incompatible with the
     * type of the file that is currently loaded.
     */
    public static final FileException WRONG_FILE_TYPE = new FileException(
            "Sorry, the current file is of the wrong file type (task/finance). Please UNLOAD the"
                    + "current file and then CREATE or LOAD another file of the desired file type.");

    /** Constructs a new FileException. For instantiating static members only. */
    private FileException(String message) {
        super(message);
    }
}
