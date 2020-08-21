package main.java;
public class Deadline extends Task {
    private String deadline;
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }
    public Deadline(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" +"[" + this.getStatusIcon()+"] " + this.description + "(by:" + this.deadline +")";
    }

    @Override
    public String writeToFile() {
        String result = "D # ";
        if(isDone) {
            result+="1 # ";
        } else {
            result+="0 # ";
        }
        result+=description;
        result+=" # "+deadline;
        return result;
    }
}
