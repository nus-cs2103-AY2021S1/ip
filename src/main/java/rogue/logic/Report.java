package rogue.logic;

public class Report {
    private final String description;
    private final boolean isExit;

    public Report(String description) {
        this.description = description;
        this.isExit = false;
    }

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