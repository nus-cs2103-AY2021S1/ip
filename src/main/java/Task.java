package main.java;

public class Task {
    String name;
    boolean isDone;

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

    public void showTask(){
        System.out.println(this.toString());
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
