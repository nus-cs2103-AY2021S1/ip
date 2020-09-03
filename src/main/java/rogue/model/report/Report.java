package rogue.model.report;

/**
 * Feedback generated after an {@code Executable} is executed by {@code Rogue}.
 * May contain messages for success, errors, or other miscellaneous information.
 */
public class Report {
    private final String message;
    private final boolean isExit;

    /**
     * Constructs a {@code Report}.
     *
     * @param message The main content of the report.
     */
    public Report(String message) {
        this.message = message;
        this.isExit = false;
    }

    /**
     * Constructs a {@code Report}.
     *
     * @param message   The main content of the report.
     * @param isExit    Indicates whether {@code Rogue} should exit.
     */
    public Report(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public String toString() {
        return message;
    }
}
