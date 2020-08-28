package duke.Commands;

import duke.Exceptions.*;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Tasks.Task;
import duke.Parser.Parser;
import duke.Ui.Ui;

import java.util.Scanner;

/**
 * The AddInput method adds to a given TaskList based on the command given
 * saves the list of Tasks in the TaskList into a txt file
 */
public class AddInput {

    /**
     * Adds a Task into the given TaskList object based on the given command
     * @param taskList
     * @param storage
     */
    public static void add_input(TaskList taskList, Storage storage) {
        Scanner scanner = new Scanner(System.in);


        while(scanner.hasNext()){
            try{
                String input = scanner.nextLine();
                //splitting into list for easier comparison
                String[] inputList = input.trim().split(" ", 2);

                // case where input is bye, and a case where the inputList is of length 1
                if(Parser.isBye(inputList[0].trim().toLowerCase())){
                    Ui.byeGreetings();
                    storage.saveTasks(taskList);
                    break;
                }

                // Case where input is list, to show the list of tasks, and case where the inputList is of length 1
                if(Parser.isList(inputList[0].trim().toLowerCase()) && inputList.length > 1){
                    throw new IncompleteCommandException();
                }
                if(Parser.isList(inputList[0].trim().toLowerCase())) {
                    StringBuffer result = new StringBuffer();
                    //to add in the starting line of the section
                    result.append("Here are the tasks in your list:\n");

                    for (int i = 0; i < taskList.getSize(); i++) {
                        // getting the current task
                        Task currentTask = taskList.getTask(i);

                        // adding the current task into the taskList
                        result.append((i + 1) + ". " + currentTask.toString() + "\n");
                    }
                    Ui.lineFormatter(result.toString());
                }

                //case where the command is incomplete, in the cases of done, todo, event, deadline and delete
                else if(inputList.length < 2) {
                    throw new IncompleteCommandException();

                }
                //Checks for FIND_KEY, to find list of tasks containing the keyword
                else if(Parser.isFind(inputList[0].trim().toLowerCase())){
                    Find.find(inputList[1].trim(), taskList);
                }

                // case where the input is done
                else if(Parser.isComplete(inputList[0].trim().toLowerCase()) && Parser.isNum(inputList[1])){
                    int currentIndex = Integer.parseInt(inputList[1]) - 1;
                    if(currentIndex + 1> taskList.getSize() || currentIndex + 1 <= 0){
                        throw new DoneException(currentIndex, taskList.getSize());
                    } else {
                        Task task = taskList.getTask(currentIndex);
                        // to check if the task is already done
                        if(task.getStatus()){
                            throw new TaskAlreadyDoneException(task);
//                            lineFormatter("This task is already done!\n" +
//                                    task.toString());
                            // if task is not done
                        } else {
                            taskList.getTask(currentIndex).markAsDone();
                            Ui.taskDone(taskList.getTask(currentIndex));
                        }
                    }
                } else if(Parser.isDelete(inputList[0].trim().toLowerCase()) && Parser.isNum(inputList[1])) {
                    int currentIndex = Integer.parseInt(inputList[1]) - 1;
                    if (currentIndex + 1 > taskList.getSize() || currentIndex + 1 <= 0) {
                        throw new DeleteException(currentIndex, taskList.getSize());
                    } else {
                        Task deletedTask = taskList.removeTask(currentIndex);
                        Ui.taskDeleted(deletedTask);
                    }
                } else {
                    AddToList.added_to_List(taskList, input);


                }
            } catch (DukeException e){
                Ui.lineFormatter(e.getMessage());
            }

        }
    }
}
