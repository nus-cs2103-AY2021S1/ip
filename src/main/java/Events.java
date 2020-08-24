import java.io.Serializable;public class Events extends Task {

    private String start;

    public Events(String description) {
        super(description);
        this.type = "Events";
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart() {
        return this.start;
    }

    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "] "
                + this.getDescription().substring(0,description.indexOf("/")) + "(at: " + this.getStart() + ")";
    }
}
