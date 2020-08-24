public class Deadline extends Task {

    protected String deadline;

    public Deadline(String new_task, String deadline) {
        super(new_task);
        this.deadline = deadline;
    }

    public String fileFormat() {
        return "D" + " | " + super.fileFormat() + " | " + deadline;
    }
    
    public String timeConverted() {
        timeParser inputTime = new timeParser(deadline);
        String outputTime = inputTime.timeConverter();
        return "D" + " | " + super.fileFormat() + " | " + outputTime;

    }
    
    @Override
    public String toString() {
        
        return "[D]" +  super.toString() + " (by: " + deadline + ")";
    }
}
