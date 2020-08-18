import java.util.Optional;

public class Task {

    private String type;// can be [T] [E] or [D]
    private boolean done = false;
    private String details;
    private String deadLine = null;

    public Task(String type, String details) {
        this.type = type;
        this.details = details;
    }

    public Task(String type, String details,String deadLine) {
        this.type = type;
        this.details = details;
        this.deadLine = deadLine;
    }

    @Override
    public String toString() {
        String checkbox = done? "[✓]":"[✗]" ;
        String myDeadline = deadLine==null? "": deadLine;

        return this.type+checkbox+" "+ this.details+" "+ deadLine;
    }
}
