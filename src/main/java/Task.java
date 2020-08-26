public abstract class Task {
    protected final String description;
    protected Boolean done;
    protected static String format = "<task description>";
    
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    
    public static String getFormat() {
        return format;
    }
    
    public abstract String getParsedTask();
    
    public String getCheckBox() {
        if (this.done) {
            return "[\u2713]";
        } else {
            return "[\u2718]";
        }
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return getCheckBox() + " " + this.description;
    }
}
