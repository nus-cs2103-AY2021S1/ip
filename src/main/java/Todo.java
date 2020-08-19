public class Todo extends Task{
    private String taskType = "T";

    Todo(String taskName){
        super(taskName);
    }

    public String getTaskType(){
        return this.taskType;
    }
}