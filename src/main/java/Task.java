public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted){
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public void markDone(){
        this.isCompleted = true;
    }

    public String getIcon(){
        if(isCompleted){
            return "[✓] ";
        } else {
            return "[✗] ";
        }
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
