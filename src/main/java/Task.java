import java.util.ArrayList;

public class Task {

    protected String description;
    protected boolean isDone;
    //protected DateAndTime dateAndTime;

    public static ArrayList<Task> taskStorage = new ArrayList<>();

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

//    public Task(String description, Boolean isDone, DateAndTime dateAndTime){
//        this.description = description;
//        this.isDone = isDone;
//        this.dateAndTime = dateAndTime;
//    }

    public String getStatusIcon(){
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public Task markAsDone(){

        if(this instanceof ToDo){
            return new ToDo(this.description, true);
        }else if(this instanceof Event){
            return new Event(this.description, ((Event) this).at, true);
        }else if(this instanceof Deadline){
            return new Deadline(this.description, ((Deadline) this).by, true);
        }else{
            return new Task(this.description, true);
        }

    }

    public static void write(String input, String type, DateAndTime byOrAt){

        Task toBeAdded;
        if(type.equals( "todo")) {
            toBeAdded = new ToDo(input);
            taskStorage.add(toBeAdded);
        }else if(type.equals( "deadline")){
            toBeAdded = new Deadline(input, byOrAt);
            taskStorage.add(toBeAdded);
        }else{
            toBeAdded = new Event(input, byOrAt);
            taskStorage.add(toBeAdded);
        }
        System.out.println("Got it. I've added this task: \n" + toBeAdded);
        System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

    }

    public static void read(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskStorage.size(); i ++){
            System.out.println(String.valueOf(i + 1 ) +"."+ taskStorage.get(i));
        }
    }

    public static void delete(int ref){
        if(ref >= taskStorage.size()){
            System.out.println("I am afraid that it is not possible to delete an unknown task.");
        }else{
            Task temp = taskStorage.get(ref);
            taskStorage.remove(ref);
            System.out.println("Noted. I've removed this task:\n " +
                    temp + "\nNow you have " + taskStorage.size() + " tasks in the list.");
        }
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
