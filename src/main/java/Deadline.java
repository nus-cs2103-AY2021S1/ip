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
            String taskTime = NameTimePair[1];
            return new Deadline(taskName,taskTime);
    }

    public static Deadline create(String taskName, String taskTime){
        return new Deadline(taskName,taskTime);
    }

    @Override
    public String toString(){
        String symbol = isDone ? "\u2713" : "\u2718";
        return String.format("[%s][%s] %s (by: %s)",tag,symbol,taskName,by);
    }

    @Override
    public String safeFileFormat(){
        int done = isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s \n",tag,done,taskName,by);
    }
}