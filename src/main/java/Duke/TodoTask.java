package duke;

public class TodoTask extends Task{
    private String taskType = "T";

    TodoTask(String taskContent){
        super(taskContent);
    }

    public String getTaskType(){
        return this.taskType;
    }
}