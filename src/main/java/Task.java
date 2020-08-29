public class Task {
    public int status;
    public int category;
    public String command;
    Task(int category, int status, String command){
        this.category = category;
        this.status = status;
        this.command = command;
    }
}