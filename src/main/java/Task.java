public class Task {
    boolean completed = false;
    String name;

    Task(String name){
        this.name = name;
    }

    public Task completedTask(){
        completed = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.toString());
        return this;
    }

}
