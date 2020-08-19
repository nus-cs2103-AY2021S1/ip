public class Task{
    private String name;

    Task(){}

    Task(String taskName){
        this.name = taskName;
    }

    String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}