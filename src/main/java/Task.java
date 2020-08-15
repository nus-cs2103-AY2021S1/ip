public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name){
        this.name = name;
        this.isCompleted = false;
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

    @Override
    public String toString(){
        return this.getIcon() + this.name;
    }

}
