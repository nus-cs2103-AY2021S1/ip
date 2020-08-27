package rogue.logic;

/**
 * Feedback generated after an {@code Executable} is executed by {@code Rogue}.
 * May contain messages for success, errors, or other miscellaneous information.
 */
public class Report {
    private final String description;
    private final boolean isExit;

    /**
     * Constructs a {@code Report}.
     *
     * @param description The main content of the report.
     */
    public Report(String description) {
        this.description = description;
        this.isExit = false;
    }

    /**
     * Constructs a {@code Report}.
     *
     * @param description   The main content of the report.
     * @param isExit        Indicates whether {@code Rogue} should exit.
     */
    public Report(String description, boolean isExit) {
        this.description = description;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    @Override
    public String toString() {
        return description;
    }
}