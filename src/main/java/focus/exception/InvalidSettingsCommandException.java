package focus.exception;

/** Represents exception for invalid format when user wants to go to settings. */
public class InvalidSettingsCommandException extends FocusException {
    /** Creates InvalidSettingsCommandException for SettingsCommand class to throw. */
    public InvalidSettingsCommandException() {
        super("Please type in the correct format for settings!\n"
                + "\tIf you need an example, type 'help'!");
    }
}
