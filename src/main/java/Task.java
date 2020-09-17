public class Task {
    private String name;
    private boolean isCompleted;

    /**
     * constructor for Task object
     * @param name description of the task
     * @param isCompleted boolean which tells if task is completed or not
     */
    public Task(String name, boolean isCompleted){
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * method to mark a task as done
     */
    public void markDone(){
        this.isCompleted = true;
    }

    public String getIcon(){
        String icon = isCompleted ? "\u2713" : "\u2717";
        return String.format("[%s]", icon);
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return "";
    }

    public String isDone(){
        return String.valueOf(this.isCompleted);
    }

    public String getTime(){
        return "";
    };

    @Override
    public String toString(){
        return this.getIcon() + this.name;
    }

}
