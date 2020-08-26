package Commands;

import Exceptions.*;
import Storage.StorageCommands;
import TaskList.TaskList;
import Tasks.Task;
import Parser.Parser;

import java.util.Scanner;

public class AddInput {

    public static void add_input() {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            try{
                String input = scanner.nextLine();
                //splitting into list for easier comparison
                String[] inputList = input.trim().split(" ", 2);

                // case where input is bye, and a case where the inputList is of length 1
                if(Parser.isBye(inputList[0].trim().toLowerCase())){
                    Parser.byeGreetings();
                    StorageCommands.saveTasks();
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

                    for (int i = 0; i < TaskList.getSize(); i++) {
                        // getting the current task
                        Task currentTask = TaskList.getTask(i);

                        // adding the current task into the tasklist
                        result.append((i + 1) + ". " + currentTask.toString() + "\n");
                    }
                    Parser.lineFormatter(result.toString());
                }

                //case where the command is incomplete, in the cases of done, todo, event, deadline and delete
                else if(inputList.length < 2) {
                    throw new IncompleteCommandException();

                }
                // case where the input is done
                else if(Parser.isComplete(inputList[0].trim().toLowerCase()) && Parser.isNum(inputList[1])){
                    int currentIndex = Integer.parseInt(inputList[1]) - 1;
                    if(currentIndex + 1> TaskList.getSize() || currentIndex + 1 <= 0){
                        throw new DoneException(currentIndex, TaskList.getSize());
                    } else {
                        Task task = TaskList.getTask(currentIndex);
                        // to check if the task is already done
                        if(task.getStatus()){
                            throw new TaskAlreadyDoneException(task);
//                            lineFormatter("This task is already done!\n" +
//                                    task.toString());
                            // if task is not done
                        } else {
                            TaskList.getTask(currentIndex).markAsDone();
                            Parser.taskDone(TaskList.getTask(currentIndex));
                        }
                    }
                } else if(Parser.isDelete(inputList[0].trim().toLowerCase()) && Parser.isNum(inputList[1])) {
                    int currentIndex = Integer.parseInt(inputList[1]) - 1;
                    if (currentIndex + 1 > TaskList.getSize() || currentIndex + 1 <= 0) {
                        throw new DeleteException(currentIndex, TaskList.getSize());
                    } else {
                        Task deletedTask = TaskList.removeTask(currentIndex);
                        Parser.taskDeleted(deletedTask);
                    }
                } else {
                    AddToList.added_to_List(input);


                }
            } catch (DukeException e){
                Parser.lineFormatter(e.getMessage());
            }

        }
    }
}
