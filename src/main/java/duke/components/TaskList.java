package duke.components;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> myList;

    public TaskList(ArrayList<Task> list){

        this.myList = list;

    }

    public ArrayList<Task> getMyList() {
        return myList;
    }

    public Task finishTaskNum(int taskNum){

        Task currentTask = myList.get(taskNum);
        currentTask.finishTask();
        return currentTask;

    }

    public Task addTask(String description, LocalDate date, String time){
        Task currentTask = null;
        if(date==null){
            try {
                currentTask = new ToDo(description);
                myList.add(currentTask);

            }catch(DukeException e){
                System.out.println(e);
            }

        }else if(time==null){
            try {
                currentTask = new Deadline(description,date);
                myList.add(currentTask);

            }catch(DukeException e){
                System.out.println(e);
            }

        }else{
            try {
                currentTask = new Event(description,date,time);
                myList.add(currentTask);

            }catch(DukeException e){
                System.out.println(e);
            }
        }
        return currentTask;
    }

    public Task deleteTask(int taskNum){
        Task currentTask = myList.get(taskNum);
        myList.remove(taskNum);
        return currentTask;
    }

}
