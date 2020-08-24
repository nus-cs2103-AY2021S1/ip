import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected String time;
    protected LocalDate localDate;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task(String taskName, String time) {
        this.taskName = taskName;
        this.isDone = false;
        this.time = time;
        this.localDate = LocalDate.parse(time.trim());
    }


    public String getName() {
        return this.taskName;
    }

    public void setTaskToBeDone() {
        this.isDone = true;
    }

    public String getStatusSymbol() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    public void setWhetherTaskDone(String whetherIsDone) {
         isDone = whetherIsDone.equals("true");
    }

    public String writeToFile() {
        return this.getStatusSymbol() + "|" + this.taskName;
    }

    @Override
    public String toString() {
        return "[" +
                this.getStatusSymbol() + "] " + taskName;
    }
}
