import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String description) {
        super(description.split(" /")[0]);
        String[] output = description.split("/");
        String pattern = ("(by?)(\\s)(.+)");
        deadline = output[1].replaceAll(pattern, "$3");
    }

    public DeadlineTask(String description, boolean done, String deadline) {
        super(description);
        isDone = done;
        this.deadline = deadline;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"D", isDone ? "1" : "0", description, deadline};
    }

    @Override
    public String toString()
    {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, deadline);
    }
}
