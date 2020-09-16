package botbot.tasks;

public enum TaskStatus {
    DONE ("1", "\u2713"),
    NOT_DONE ("0", "\u2717");
    
    private final String strValue;
    private final String statusIcon;
    
    TaskStatus(String strValue, String statusIcon) {
        this.strValue = strValue;
        this.statusIcon = statusIcon;
    }

    /**
     * Converts a string value to a task status.
     *
     * @param strValue String to be converted.
     * @return Task status.
     */
    public static TaskStatus convertToStatus(String strValue) {
        if (strValue.equals(DONE.strValue)) {
            return DONE;
        } else if (strValue.equals(NOT_DONE.strValue)) {
            return NOT_DONE;
        } else {
            assert false : "Invalid task status";
            return null;
        }
    }
    
    public String getStrValue() {
        return strValue;
    }

    public String getStatusIcon() {
        return statusIcon;
    }
}
