import Util.Constants;
import Util.UtilFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DukeCommandMatcher {
    private static final List<String> commandList = new ArrayList<>(Arrays.asList(Constants.LISTPATTERN,
                                                                  Constants.EXITPATTERN, Constants.DONEPATTERN));
    private List<Task> taskList = new ArrayList<>();

    public String matchCommand(String command){
        //split the command first
        String[] splitCommand = command.split("\\s+");
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
               }
            }
        }
        return handleAdd(command);
    }

    private String handleExit(){
        System.out.println("Farewell/再見/さようなら～～");
        return "EXIT";
    }

    private String handleAdd(String task){
        taskList.add(new Task(task));
        System.out.println("added: " + task);
        return "Task added";
    }

    private String handleList(){
        for(int i =1; i< taskList.size()+1; i++){
            System.out.println(i + ". " + taskList.get(i-1));
        }
        return "List implemented";
    }

    private String handleDone(String targetTask){
        Task task = this.taskList.get(Integer.parseInt(targetTask) + 1);
        task.setStatus(true);
        System.out.println("Très bien!I have helped you marked task " + targetTask + " as done\n"
            + task);
        return "Task " + targetTask + " has been done";
    }

}
