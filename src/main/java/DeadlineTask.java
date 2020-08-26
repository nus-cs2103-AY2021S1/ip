public class DeadlineTask extends Task{
    private String time;
    private String taskType = "D";

    DeadlineTask(String taskContent, String time){
        super(taskContent);
        this.time = time;
    }

    public String getTime(){
        return this.time;
    }

    public String getTaskType(){
        return this.taskType;
    }

    @Override
    public String toString() {
        return super.toString()+" (by: " + this.time + ")";
    }
}