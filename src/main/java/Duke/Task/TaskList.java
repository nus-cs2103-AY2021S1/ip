package Duke.Task;

import Duke.Task.Task;
import Duke.Task.Todo;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }
    
    public ArrayList<Task> getList() {
        return this.taskList;
    }
    
    //count number of tasks
    public String countNum() {
        int num = this.taskList.size();
        return "    Now you have " + num + " tasks in the list.";
    }
    
    public int getSize() {
        return taskList.size();
    }
    
    public Task get(int num) {
        return taskList.get(num);
    }
    
    public void remove(int num) {
        taskList.remove(num);
    }
    
    //add new to-do to the list
    public void addToDo(Todo newToDo) {
        this.taskList.add(newToDo);
    }

    //add new deadline to the list
    public void addDeadline(Deadline newDdl) {
        this.taskList.add(newDdl);
    }

    //add new event to the list
    public void addEvent(Event newEvent) {
        this.taskList.add(newEvent);
    }

//    //mark a task as done
//    public void markAsDone(int num) throws Duke.Tool.DukeException {
//        if (num > 0 && num <= taskList.size()) {
//            taskList.get(num - 1).markAsDone();
//            String msgForDone = "    ____________________________________________________________\n"
//                    + "    Nice! I 've marked this task as done: \n"
//                    + "       " + taskList.get(num - 1).toString() + "\n"
//                    + "    ____________________________________________________________\n";
//            System.out.println(msgForDone);
//        } else {
//            throw new Duke.Tool.DukeException(
//                    "OOPS!!! The task is not found. Please try again."
//            );
//        }
//    }
//
//    //list all the tasks
//    public void list() {
//        String msgForList = "    ____________________________________________________________\n";
//        msgForList += "    Here are the tasks in your list: \n";
//        for (int i = 0; i < taskList.size(); i++) {
//            msgForList += "    " + (i + 1) + ". "
//                    + taskList.get(i).toString() + "\n";
//        }
//        msgForList += "    ____________________________________________________________\n";
//        System.out.println(msgForList);
//    }
//
//    //delete a task
//    public void delete(int num) throws Duke.Tool.DukeException {
//        if (num > 0 && num <= taskList.size()) {
//            String msgForDelete = "    ____________________________________________________________\n"
//                    + "    Noted. I've removed this task: \n"
//                    + "       " + taskList.get(num - 1).toString() + "\n";
//            taskList.remove(num - 1);
//            msgForDelete += this.countNum() + "\n"
//                        + "    ____________________________________________________________\n";
//            System.out.println(msgForDelete);
//        } else {
//            throw new Duke.Tool.DukeException(
//                    "OOPS!!! The task is not found. Please try again."
//            );
//        }
//    }
}
