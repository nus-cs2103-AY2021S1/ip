public class Deadline extends Task{

    protected DateAndTime by;

    public Deadline(String description, DateAndTime by){
        super(description);
        this.by = by;
    }

    public Deadline(String description, DateAndTime by, boolean isDone){
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
