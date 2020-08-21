public class Event extends Task {
    public Event(String description, int index) {
        super(description, index);
    }
    @Override
    public String getStatusWithIndex() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = new StringBuilder(this.description.substring(idx + 1)).insert(2,':').toString();
        return isDone ? index + ". " + "[D][\u2713] " + task + String.format("(%s)", end)
                : index + ". " + "[D][\u2718] " + task + String.format("(%s)", end); //return tick or X symbols
    }
    @Override
    public String toString() {
        int idx = this.description.indexOf('/');
        String task = this.description.substring(0, idx);
        String end = new StringBuilder(this.description.substring(idx + 1)).insert(2,':').toString();
        return isDone ? "[D][\u2713] " + task + String.format("(%s)", end)
                : "[D][\u2718] " + task + String.format("(%s)", end);
    }
}
