package Ultron;

import Ultron.Tasks.Task;

import java.util.ArrayList;

public class TaskList {

    //Store the internal list
    private final ArrayList<Task> list;

    public TaskList() {

        //Create a new arraylist to store the tasks
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskArrayList){

        //Set the arraylist to the list
        list = taskArrayList;
    }

    public ArrayList<Task> getList(){
        return list;
    }

    public int size() {


        //Get the size of the list
        return this.list.size();
    }

    public boolean markDone(final int index) {

        //Check if the index is valid
        if (index < this.list.size() && index >= 0) {

            //Mark the task at index as done
            this.get(index).markDone();

            //Return true if operation is successful
            return true;

        } else {

            //Otherwise operation will fail
            return false;
        }

    }

    public boolean remove(final int index) {
        //Check if the index is valid
        if (index < this.list.size() && index >= 0) {

            //Remove the task at index
            this.list.remove(index);

            //Return true if operation is successful
            return true;

        } else {

            //Otherwise operation will fail
            return false;
        }
    }

    public Task get(final int index) {

        //Return the index of the arraylist
        return this.list.get(index);
    }

    public void add(final Task task) {

        //Add the task to the list
        this.list.add(task);
    }
}
