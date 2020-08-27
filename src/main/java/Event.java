public class Event extends Task {
    private String timing;
    Event(String name, String timing) {
        super(name);
        this.timing = timing;
    }

    @Override
    public String missingNameError() {
        return "The description of an event task cannot be empty.";
    }

    @Override
    public String toSaveDataFormat() {
        String saveData = "";
        saveData += this.isDone ? 1 : 0;
        saveData += " E " + this.name + "\n" + this.timing;
        return saveData;
    }

    @Override
    public String toString() {
        String marked = this.isDone ? "[✓] " : "[✗] ";
        String eventTime = this.timing.length() > 0 ? " (at: " + this.timing + ")" : "";
        return "[E]" + marked + this.name + eventTime;
    }
}
