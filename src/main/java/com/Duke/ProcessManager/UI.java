package com.Duke.ProcessManager;

import com.Duke.DataManager.Storage;
import com.Duke.Tasks.*;

public class UI {

    private final static String line = "     ___________________________________________________________________";

    public static void dukeInit(){
        String introText1 = "     Hello! I'm Duke";
        String introText2 = "     What can I do for you?";
        System.out.println(line);
        System.out.println(introText1);
        System.out.println(introText2);
        System.out.println(line);
    }

    public static void printError(String msg){
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
    }

    public static void byeCalled(TaskList ls) throws DukeException{
        Storage.write(ls);
        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void listCalled(TaskList list) throws DukeException{
        System.out.println(line);
        if(list.ls.isEmpty()){
            throw new DukeException("Sorry you have no tasks in your list.");
        }else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < list.ls.size(); i++) {
                int j = i + 1;
                System.out.println("     " + j + "." + list.ls.get(i).toString());
            }
        }
        System.out.println(line);
    }

    public static void doneCalled(TaskList list, int task)throws DukeException{
        System.out.println(line);
        list.setDone(task);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + list.ls.get(task).toString());
        System.out.println(line);

    }

    public static void toDoCalled(TaskList ls, ToDo toDo){
        System.out.println(line);
        System.out.println("     Got it I've now added this Task:");
        System.out.println("       " + toDo.toString());
        ls.add(toDo);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
    }

    public static void deadlineCalled(TaskList ls, Deadline deadline){
        System.out.println(line);
        System.out.println("     Got it I've now added this Task:");
        System.out.println("       " + deadline.toString());
        ls.add(deadline);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
    }

    public static void eventCalled(TaskList ls, Event event){
        System.out.println(line);
        System.out.println("     Got it I've now added this Task:");
        System.out.println("       " + event.toString());
        ls.add(event);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
    }

    public static void blahCalled() throws DukeException{
        System.out.println(line);
        Blah.blahCreated();
        System.out.println(line);
    }

    public static void deleteCalled(TaskList ls, int task)throws DukeException{
        System.out.println(line);
        ls.delete(task);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
    }

    public static void taskCalled(TaskList ls, Task task){
        System.out.println(line);
        ls.add(task);
        System.out.println("     added: " + task.getTask());
        System.out.println(line);
    }
}
