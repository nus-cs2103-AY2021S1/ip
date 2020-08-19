import Util.Constants;
import Util.UtilFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DukeCommandMatcher {
    private static final List<String> commandList = new ArrayList<>(Arrays.asList(Constants.LISTPATTERN,
                                                                  Constants.EXITPATTERN, Constants.DONEPATTERN,
                                                                  Constants.TODOPATTERN, Constants.DEADLINEPATTERN,
                                                                  Constants.EVENTPATTERN));
    private List<Task> taskList = new ArrayList<>();

    public String matchCommand(String command){
        //get the first command
        String[] splitCommand = command.split("\\s+", 2);
        //check if the command is in the list
        for(String commandPattern: commandList){
            //the command is in the list
            if(UtilFunction.matchPattern(commandPattern, splitCommand[0])){
               switch(commandPattern){
                   case Constants.EXITPATTERN:
                       return handleExit();
                   case Constants.LISTPATTERN:
                       return handleList();
                   case Constants.DONEPATTERN:
                       return handleDone(splitCommand[1]);
                   case Constants.TODOPATTERN:
                       return handleTodo(splitCommand[1]);
                   case Constants.DEADLINEPATTERN:
                       return handleDeadline(splitCommand[1]);
                   case Constants.EVENTPATTERN:
                       return handleEvent(splitCommand[1]);
               }
            }
        }
        System.out.println("error");
        return "error command";
    }

    private String handleExit(){
        System.out.println("Farewell/再見/さようなら～～");
        return "EXIT";
    }

    private String handleAdd(Task task){
        taskList.add(task);
        int numOfTask = taskList.size();
        System.out.println("Got it. I've added this task:\n" +
                "   " + task +  '\n' +
                "Now you have " + numOfTask + (numOfTask > 1 ? " tasks " : " task ") + "in the list.");
        return "Task added";
    }

    private String handleList(){
        for(int i =1; i< taskList.size()+1; i++){
            System.out.println(i + ". " + taskList.get(i-1));
        }
        return "List implemented";
    }

    private String handleDone(String targetTask){
        Task task = this.taskList.get(Integer.parseInt(targetTask) - 1);
        task.setStatus(true);
        System.out.println("Très bien!I have helped you marked task " + targetTask + " as done\n"
            + task);
        return "Task " + targetTask + " has been done";
    }

    private String handleTodo(String todoStr){
        ToDo todo = new ToDo(todoStr);
        return handleAdd(todo);
    }

    private String handleDeadline(String deadlineStr){
        String[] splitDeadLineStr = deadlineStr.split("/", 2);
        Deadline deadline = new Deadline(splitDeadLineStr[0], splitDeadLineStr[1]);
        return handleAdd(deadline);
    }

    private String handleEvent(String eventStr){
        String[] splitEventStr = eventStr.split("/", 2);
        Event event = new Event(splitEventStr[0], splitEventStr[1]);
        return handleAdd(event);
    }

}
