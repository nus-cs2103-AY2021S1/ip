public class Events extends Task {
    private String at;

    Events(String task , String at) {
        super(task);
        this.at = at;
    }
    @Override
    public String toString() {
        return String.format("[E]%s(at:%s)", super.toString(), this.at);
    }
}
