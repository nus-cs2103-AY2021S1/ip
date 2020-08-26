import java.util.*;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void showName(){
        System.out.println(this.name);
    }

    public void showTask(int index){
        System.out.println(index + ". " +this.toString());
    }

    public String getType(){
        return "Task";
    }

    public String getName(){
        return name;
    }

    public int isDone(){
        return isDone
                ? 1
                : 0;
    }

    public String getTime(){
        return "";
    }

    public String showContent(){
        int status;
        if(isDone){
            status = 1;
        } else{
            status = 0;
        }
        String commonContent = this.getType() + " | " + status + " | " + this.name;
        String content;
        if(this instanceof Deadline || this instanceof Event){
            content = commonContent + " | " + this.getTime();
        }else{
            content = commonContent;
        }

        return content+"\n";
    }

    @Override
    public String toString(){

        String isDoneSymbol;

        if(isDone){
            isDoneSymbol= "\u2713";
        }else{
            isDoneSymbol= "\u2718";
        }

        return "[" + isDoneSymbol + "] " + name;

    }
}
