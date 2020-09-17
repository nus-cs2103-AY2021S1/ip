package duke;

import java.time.LocalDateTime;

public class Task{
    private String content;        // stores the name of this task
    private Boolean closed = false;     //indicates the status of this task
    private String taskType = "";
    private LocalDateTime dateTime;
    private LocalDateTime fromDateTime, toDateTime;

    Task(){}

    Task(String taskName){
        this.content = taskName;
    }

    Task(String taskName, boolean taskStatus){
        this.content = taskName;
        this.closed = taskStatus;
    }

    /**
     * Returns the current status of this task and close current task
     * @return status of current task
     */
    public Boolean closeTask(){
        this.closed = true;
        return this.closed;
    }

    /**
     *
     * @return status of current task
     */
    public Boolean checkDone(){
        return this.closed;
    }

    public String getTaskType(){
        return this.taskType;
    }

    public LocalDateTime getDateTime(){
        return this.dateTime;
    }
    public LocalDateTime getFromDateTime() { return this.fromDateTime; }

    public LocalDateTime getToDateTime() { return this.toDateTime; }

    public String getContent(){
        return this.content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}