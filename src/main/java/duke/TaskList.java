package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class to handle task list functions
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class TaskList {
    private List<Task> list;

    /**
     * TaskList constructor to initialize a TaskList object
     * @param list list of tasks from the load file
     */
    TaskList(List<Task> list){
        this.list = list;
    }

    /**
     * add method which takes in the user input and adds the appropriate task to the list
     * @param input user input where the user specifies what he wants to add
     * @throws DukeException when there is an error with the user input
     */
    public Task add(String input) throws DukeException{

        if (input.split(" ")[0].equals("todo")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of todo cannot be empty!");
            }
            Task newTask = new ToDo(temp[1]);
            list.add(newTask);
            return newTask;

        } else if (input.split(" ")[0].equals("deadline")) {
            String[] temp = input.split(" ",2);
            if (temp.length <= 1) {
                throw new DukeException("Description of deadline cannot be empty!");
            }
            String[] temp2 = temp[1].split("/by",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Deadlines(temp2[0], temp2[1]);
            list.add(newTask);
            return newTask;

        } else if (input.split(" ")[0].equals("event")) {
            String[] temp = input.split(" ",2);
            if (temp.length == 1) {
                throw new DukeException("Description of event cannot be empty!");
            }
            String[] temp2 = temp[1].split("/at",2);
            if (temp2.length <= 1){
                throw new DukeException("You need to specify a time!");
            }
            Task newTask = new Events(temp2[0], temp2[1]);
            list.add(newTask);
            return newTask;

        } else {
            throw new DukeException("Sorry I don't know what you mean by that");
        }

    }

    /**
     * delete method which takes in the user input and deletes the appropriate task from the list
     * @param input user input where the user specifies what he wants to delete
     * @throws DukeException when there is an error with the user input
     */
    public Task delete(String input) throws DukeException{
        try {
            String num = input.split(" ")[1];
            if (num.equals("all")) {
                deleteAll();
                return null;
            } else {
                int intNum = Integer.parseInt(num);
                Task temp = list.get(intNum - 1);
                list.remove(intNum - 1);
                return temp;
            }
        } catch (NumberFormatException e){
            throw new DukeException("Please key in a number");
        } catch (IndexOutOfBoundsException r){
            throw new DukeException("The task does not exist");
        }
    }

    /**
     * done method which sets a task as done
     * @param num the integer of the task which the user wants to set as done
     * @return returns the completed task
     */
    public Task done(int num){
        return list.set(num-1, list.get(num-1).completeTask());
    }

    /**
     * getList method which returns the list of tasks
     * @return returns the list of tasks
     */
    public List<Task> getList(){
        return list;
    }

    /**
     * countList method which returns the number of tasks in the list
     * @return returns the number of tasks in the list
     */
    public int countList(){
        return list.size();
    }

    /**
     * deleteAll method which deletes all tasks in the list
     */
    public void deleteAll(){
        list = new ArrayList<>();
    }
}
