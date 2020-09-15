public class Event extends Task {

    private String time;

    public Event(String name, String time) {
        super(name);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.printTask());
        sb.append(" (at: " + this.time + ")");
        return sb.toString();
    }

}