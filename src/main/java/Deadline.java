public class Deadline extends Task {

    protected String by;

    private Deadline(String taskName, String taskTime) {
        super(taskName);
        this.by = taskTime;
        this.tag = "D";
    }

    public static Deadline create(String taskDescription) throws DukeException {
            if(!taskDescription.contains("/by")){
                throw new DukeException("Please include '/by' in front of the deadline");
            }
            String[] NameTimePair = taskDescription.split(" /by");
            String taskName = NameTimePair[0];
            String taskTime;
            if(NameTimePair.length <= 1){
                throw new DukeException("Please specify a deadline for the task");
            }else{
                taskTime = NameTimePair[1];
            }
            return new Deadline(taskName,taskTime);
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return String.format("[%s][%s] %s (by: %s)",tag,symbol,taskName,by);
    }
}