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
        return new Task(this.description, true);
    }

    public static void write(String input){
        for(int i = 0; i < 100; i++){
            if(taskStorage[i] == null){
                taskStorage[i] = new Task(input);
                System.out.println("added: " + input);
                break;
            }
        }
    }

    public static void read(){
        for(int i = 0; i < 100; i ++){
            if(taskStorage[i] != null){
                System.out.println(taskStorage[i]);
            }else{
                break;
            }
        }
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "]" + this.description;
    }

}
