package Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DukeCommandMatcher {
    private static final List<String> commandList = new ArrayList<>(Arrays.asList(Constants.LISTPATTERN,
                                                                  Constants.EXITPATTERN));
    private List<String> taskList = new ArrayList<>();

    public void matchCommand(String command){
        //check if the command is in the list
        for(String commandPattern: commandList){
            //the command is in the list
            if(UtilFunction.matchPattern(commandPattern, command)){
               switch(commandPattern){
                   case Constants.EXITPATTERN:
                       handleExit();
                       return;
                   case Constants.LISTPATTERN:
                       handleList();
                       return;
               }
            }
        }
        handleAdd(command);
    }

    private void handleExit(){
        System.out.println("Farewell/再見/さようなら～～");
    }

    private void handleAdd(String task){
        taskList.add(task);
        System.out.println("added: " + task);
    }

    private void handleList(){
        for(int i =0; i< taskList.size(); i++){
            System.out.println(i + ". " + taskList.get(i));
        }
    }

}
