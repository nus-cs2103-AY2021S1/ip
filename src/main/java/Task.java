public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
        Duke.printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        Duke.printLine();

    }

    public String writerSave(){
        String store = "D |";
        if(isDone){
            store += " 1 |";
        } else {
            store += " 0 |";
        }
        store += " "  + this.description;
        return store;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){
            return "[" + getStatusIcon()+ "]" + " " + description;
    }

}
