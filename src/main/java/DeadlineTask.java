public class DeadlineTask extends Task{
    private String deadline;
    public DeadlineTask(String name, boolean isCompleted, String deadline) {
        super(name,isCompleted);
        this.deadline = deadline;
    }

    public String getType(){
        return "D";
    }

    public String getTime(){
        return this.deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + "(by: " + deadline + ")";
    }
}
