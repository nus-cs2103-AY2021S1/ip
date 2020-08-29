package duke;

public class Task {
    boolean completed = false;
    String name;
    String time;


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


    public Task completedTask(){
        completed = true;
        return this;
    }

}
