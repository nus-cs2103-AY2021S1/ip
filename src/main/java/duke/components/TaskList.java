package duke.components;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * TaskList class handles the creation of an instance of a TaskList.
 * the 'TaskList' class supports operators.
 * Supported operators includes (i) adding a task to the TaskList
 * (ii) deleting a task from the TaskList
 * and (iii) mark a task as done
 */

public class TaskList {

    private ArrayList<Task> myList;

    public TaskList(ArrayList<Task> list){

        this.myList = list;

    }

    public ArrayList<Task> getMyList() {
        return myList;
    }

    /**
     * finishes the task corresponding to the number given.
     *
     * @param taskNum the number indicating the position of the task that will be marked done.
     * @return the task that has been marked as done.
     */
    public Task finishTaskNum(int taskNum){

        Task currentTask = myList.get(taskNum);
        currentTask.finishTask();
        return currentTask;

    }

    /**
     * adds the task with the given information.
     *
     * @param description description of the task.
     * @param date date the task has to be done by.
     * @param time time the task is at.
     * @return task that has been added to the list.
     */

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

    /**
     * deletes the task from the list
     *
     * @param taskNum number indicating the position of the task on the list.
     * @return the task deleted
     */

    public Task deleteTask(int taskNum){
        Task currentTask = myList.get(taskNum);
        myList.remove(taskNum);
        return currentTask;
    }

    public ArrayList<Task> findTasks(String keyword){
        ArrayList<Task> findList = new ArrayList<>();
        for(int i = 0 ; i < myList.size();i++){
            if(myList.get(i).getDescription().contains(keyword)){
                findList.add(myList.get(i));
            }
        }
        return findList;
    }

}
