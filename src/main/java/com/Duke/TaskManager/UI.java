package com.Duke.TaskManager;

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

    public static String printError(String msg){
        System.out.println(line);
        System.out.println(msg);
        System.out.println(line);
        return msg;
    }

    public static String byeCalled(TaskList ls) throws DukeException{
        Storage.write(ls);
        System.out.println(line);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(line);
        return "Bye. Hope to see you again soon!";
    }

    public static String listCalled(TaskList list) throws DukeException{
        System.out.println(line);
        StringBuilder builder = new StringBuilder();
        if(list.ls.isEmpty()){
            throw new DukeException("Sorry you have no tasks in your list.");
        }else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < list.ls.size(); i++) {
                int j = i + 1;
                System.out.println("     " + j + "." + list.ls.get(i).toString());
                builder.append(list.ls.get(i).toString() +"\n");
            }
        }
        System.out.println(line);
        Storage.write(list);
        return builder.toString();
    }

    public static String doneCalled(TaskList list, int task)throws DukeException {
        System.out.println(line);
        list.setDone(task);
        System.out.println("     Nice! I've marked this task as done: \n");
        System.out.println("       " + list.ls.get(task).toString());
        System.out.println(line);
        Storage.write(list);
        return "Nice! I've marked this task as done: \n" + list.ls.get(task).toString();

    }

    public static String toDoCalled(TaskList ls, ToDo toDo) throws DukeException {
        System.out.println(line);
        System.out.println("     Got it I've now added this Task:");
        System.out.println("       " + toDo.toString());
        ls.add(toDo);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
        Storage.write(ls);
        return "Got it I've now added this Task: \n" + toDo.toString() + "\n" + "Now you have " + ls.length() + " tasks in the list.";
    }

    public static String deadlineCalled(TaskList ls, Deadline deadline) throws DukeException {
        System.out.println(line);
        System.out.println("     Got it I've now added this Task:");
        System.out.println("       " + deadline.toString());
        ls.add(deadline);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
        Storage.write(ls);
        return "Got it I've now added this Task: \n" + deadline.toString() + "\n" + "Now you have " + ls.length() + " tasks in the list.";
    }

    public static String eventCalled(TaskList ls, Event event) throws DukeException {
        System.out.println(line);
        System.out.println("     Got it I've now added this Task:");
        System.out.println("       " + event.toString());
        ls.add(event);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
        Storage.write(ls);
        return "Got it I've now added this Task: \n" + event.toString() + "\n" + "Now you have " + ls.length() + " tasks in the list.";
    }

    public static String blahCalled() throws DukeException{
        System.out.println(line);
        String blahMessage = Blah.blahCreated();
        System.out.println(blahMessage);
        System.out.println(line);
        return blahMessage;
    }

    public static String deleteCalled(TaskList ls, int task)throws DukeException {
        System.out.println(line);
        String deletedMessage = ls.delete(task);
        System.out.println("     Now you have " + ls.length() + " tasks in the list.");
        System.out.println(line);
        Storage.write(ls);
        return deletedMessage+"\n"+ "Now you have " + ls.length() + " tasks in the list.";
    }

    public static String taskCalled(TaskList ls, Task task) throws DukeException {
        System.out.println(line);
        ls.add(task);
        System.out.println("     added: " + task.getTask());
        System.out.println(line);
        Storage.write(ls);
        return "added: " + task.getTask();
    }

    public static String findCalled(TaskList ls, String hint) throws DukeException {
        System.out.println(line);
        TaskList containsHint = ls.findTask(hint);
        StringBuilder builder = new StringBuilder();
        if(containsHint.ls.isEmpty()){
            throw new DukeException("Sorry you have no tasks that match that description");
        }else {
            System.out.println("     Here are the matching tasks in your list:");
            builder.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < containsHint.ls.size(); i++) {
                int j = i + 1;
                System.out.println("     " + j + "." + containsHint.ls.get(i).toString());
                builder.append(j + "." + containsHint.ls.get(i).toString() + "\n");
            }
        }
        System.out.println(line);
        return builder.toString();
    }
}
