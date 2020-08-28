package duke.Commands;

import duke.Exceptions.DeadlineException;
import duke.Exceptions.DukeException;
import duke.Exceptions.EventException;
import duke.TaskList.TaskList;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.ToDo;
import duke.Parser.Parser;
import duke.Ui.Ui;

/**
 * The AddToList method adds to a given TaskList based on the command given
 */
public class AddToList {

    /**
     * Adds a Task into the given TaskList object based on the given command
     * @param taskList
     * @param printable
     */
    public static void addedToList(TaskList taskList, String printable) throws DukeException {
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
                Ui.newTaskItem(task);
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
                Ui.newTaskItem(task);

            } catch (Exception e){
                throw new EventException();
            }

        } else if(Parser.isToDo(nameList[0].toLowerCase())){
            task = new ToDo(nameList[1].trim());
            Ui.newTaskItem(task);

        } else {
            Ui.lineFormatter("Please enter an appropriate command!!");
        }
        taskList.addTask(task);

    }
}
