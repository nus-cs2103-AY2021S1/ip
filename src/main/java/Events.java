public class Events extends Task {
    private String at;

    Events(String task , String at) {
        super(task, TaskType.EVENT);
        this.at = at;
    }
    @Override
    public String toString() {
        return String.format("%s(at:%s)", super.toString(), this.at);
    }
}
