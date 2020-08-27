package duke;

import java.util.List;

public class Ui {
    String line = "--------------------------------------------------"; //50 dashes

    Ui(){

    }

    public void drawLine(){
        System.out.println(line);
    }

    public void addTask(Task task){
        System.out.println("added: " + task.toString());
    }

    public void deleteTask(Task task){
        if (task == null){
            deleteAll();
        } else {
            System.out.println("Noted. I've removed this task: " + "\n" + task.toString());
        }

    }

    public void doneTask(Task task){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public void listCount(int count){
        System.out.println("Now you have "+count+" tasks in the list.");
    }

    public void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void deleteAll(){
        System.out.println("All tasks have been deleted!");
    }

    public void printList(List<Task> list){
        int tempIndex = 1;
        for (Task x: list){
            System.out.println(tempIndex + "." + x.toString());
            tempIndex += 1;
        }
    }
}
