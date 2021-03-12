package duke.command;

/**
 * Represents the 2 simple commands, delete and done.
 */
public enum SimpleCommandType {
    DELETE,
    DONE;

    /**
     * Returns a string representation of the {@code SimpleCommandType}.
     *
     * @return String representation of the {@code SimpleCommandType}.
     */
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    /**
     * Converts the simple command type to title case.
     *
     * @return {@code SimpleCommand} type in title case.
     */
    public String toTitleCase() {
        String current = toString();
        char firstLetter = current.charAt(0);
        String remaining = toString().substring(1, current.length());
        return Character.toUpperCase(firstLetter) + remaining;
    }
}
