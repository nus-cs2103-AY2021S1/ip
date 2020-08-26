package Commands;

import Exceptions.DeadlineException;
import Exceptions.DukeException;
import Exceptions.EventException;
import TaskList.TaskList;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Parser.Parser;

public class AddToList {

    // method that adds tasks into the list of tasks
    public static void added_to_List(String printable) throws DukeException {
        Task task = new Task("Do something");
        String[] nameList = printable.split(" ", 2);
        if(Parser.isDeadline(nameList[0].trim().toLowerCase())){
            String[] task_deadline = nameList[1].trim().split("/by", 2);
            if(task_deadline.length != 2){
                throw new DeadlineException();
            }
            // check for date time format
            try {
                task = new Deadline(task_deadline[0].trim(), task_deadline[1].trim());
                Parser.newTaskItem(task);
            } catch (Exception e){

                throw new DeadlineException();
            }

        } else if(Parser.isEvent(nameList[0].trim().toLowerCase())){
            String[] task_event = nameList[1].trim().split("/at", 2);
            if(task_event.length != 2){
                throw new EventException();
            }
            // check for date time format
            try {
                task = new Event(task_event[0].trim(), task_event[1].trim());
                Parser.newTaskItem(task);

            } catch (Exception e){
                throw new EventException();
            }

        } else if(Parser.isToDo(nameList[0].toLowerCase())){
            task = new ToDo(nameList[1].trim());
            Parser.newTaskItem(task);

        } else {
            Parser.lineFormatter("Please enter an appropriate command!!");
        }
        TaskList.addTask(task);

    }
}
