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

    public Task completedTask(){
        completed = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.toString());
        return this;
    }

}
