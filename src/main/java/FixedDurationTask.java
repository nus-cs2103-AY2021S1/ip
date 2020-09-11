import java.io.Serializable;

public class FixedDurationTask extends Task implements Serializable {
    protected String duration;

    FixedDurationTask(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    String getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return "[F]" + "[" + getStatusIcon() + "] " + getDescription() + "(needs: " + getDuration() + ")";
    }
}
