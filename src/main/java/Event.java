public class Event extends Task{
    private String time;
    private String taskType = "E";


    Event(String taskName, String time){
        super(taskName);
        this.time = time;
    }

    private String getTime(){
        return this.time;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString() {
        return super.toString()+" (at: " + this.time + ")";
    }
}