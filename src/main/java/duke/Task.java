package duke;

public class Task {
    boolean isDone = false;
    private String name;
    private String time;


    Task(String name){
        this.name = name;
    }

    Task(String name, String time){
        this.name = name;
        this.time = time;
    }

    public String getName(){
        return name;
    }

    public String getTime(){
        return time;
    }

    public Task completeTask(){
        isDone = true;
        return this;
    }

}
