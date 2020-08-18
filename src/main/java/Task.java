public class Task {

    protected String description;
    protected boolean isDone;

    public static Task[] taskStorage = new Task[100];

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone){
        this.description = description;
        this.isDone = isDone;
    }

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

    public static void write(String input, String type, String byOrAt){

            for (int i = 0; i < 100; i++) {
                if (taskStorage[i] == null) {
                    if(type.equals( "todo")) {
                        taskStorage[i] = new ToDo(input);
                    }else if(type.equals( "deadline")){
                        taskStorage[i] = new Deadline(input, byOrAt);
                    }else{
                        taskStorage[i] = new Event(input, byOrAt);
                    }
                    System.out.println("Got it. I've added this task: \n" + taskStorage[i]);
                    System.out.println("Now you have " + (i + 1) + " tasks in the list.");
                    break;
                }
            }

    }

    public static void read(){
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < 100; i ++){
            if(taskStorage[i] != null){
                System.out.println(String.valueOf(i + 1 ) +"."+ taskStorage[i]);
            }else{
                break;
            }
        }
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
