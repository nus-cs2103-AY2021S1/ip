
import java.time.LocalDate;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public void list(){
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("" + (i + 1)+"." + this.tasks.get(i));
        }
    }


    public void horizontalRule() {
        System.out.println("____________________________________________________________");
    }

    public void greeting() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


    }

    public void echoWithExit(String userInput) { // level 1 task
        if (userInput.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println(userInput);

        }
    }
    public void addTask(String type, String task) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    public void addTask(String type, String task, LocalDate d1) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task,d1);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }
    public void addTask(String type,String task,String deadLine) throws InSuffArgsException{

        if(task.equals("")){
            throw new InSuffArgsException();
        }

        Task myTask = new Task(type,task,deadLine);
        this.tasks.add(myTask);
        System.out.println("added: " + myTask);
    }

    public void deleteTask(int index) {
        Task myTask = this.tasks.get(index);
        this.tasks.remove(index);
        System.out.println("removed: " + myTask);
        numTask();
    }

    public void numTask() {
        int done = 0;
        int undone = 0;
        for (int i = 0; i < this.tasks.size(); i++) {
            boolean finished = tasks.get(i).finished();
            if (finished) {
                done++;
            } else {
                undone++;
            }
        }
        System.out.println(done + " finished tasks in the list.");
        System.out.println(undone + " unfinished tasks in the list.");
    }

}



