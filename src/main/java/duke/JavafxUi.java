package duke;

import java.util.List;

/**
 * The JavafxUi class to handle user interface methods for the javajx interface
 *
 * @author  Hope Leong
 * @version 0.1
 * @since   27/8/2020
 */
public class JavafxUi {
    final String OUTPUT_LINE = "--------------------------";

    /**
     * JavafxUi constructor
     */
    JavafxUi(){

    }

    /**
     * drawLine method which returns a line
     */
    public String drawLine(){
        return OUTPUT_LINE;
    }

    /**
     * addTask method which returns the task information
     */
    public String addTask(Task task){
        return "added: " + task.toString() + "\n";
    }

    /**
     * deleteTask method which returns the task information
     */
    public String deleteTask(Task task){
        if (task == null){
            return deleteAll();
        } else {
            return "Noted. I've removed this task: " + "\n" + task.toString() + "\n";
        }

    }

    /**
     * doneTask method which returns the task information
     */
    public String doneTask(Task task){
        return "Nice! I've marked this task as done: \n" + task.toString();
    }

    /**
     * listCount method which returns the number of tasks
     */
    public String listCount(int count){
       return "Now you have "+count+" tasks in the list.\n" +
        drawLine();
    }

    /**
     * bye method which returns a message when the user exits
     */
    public String bye(){
        return "Bye. Hope to see you again soon!+\n"+
        drawLine();
    }

    /**
     * deleteAll method which returns a message when the user deletes all tasks
     */
    public String deleteAll(){
        return "All tasks have been deleted!" + "\n";
    }

    /**
     * printList method which returns all the tasks in the list
     * @param list list of tasks
     */
    public String printList(List<Task> list){
        String temp = "";
        int tempIndex = 1;
        for (Task x: list){
           temp += tempIndex + "." + x.toString() +"\n";
            tempIndex += 1;
        }
        temp += drawLine();
        return temp;
    }

    public String foundWord(List<Task> list){
        return "Here are the matching tasks in your list:\n" +
        printList(list);
    }
}
