package com.Duke.TaskManager;

import com.Duke.Commands.*;

/**
 * This class is the parser that reads user input and feeds it as Commands to Duke
 */
public class DukeParser {
    private final TaskList ls;

    public DukeParser(TaskList ls) {
        this.ls = ls;
    }

    /**
     * This method parses a given input and processes it to become a command for Duke
     * @param input
     * @return The Command for Duke to execute.
     */
    public Command parse(String input){
        String[] splitList = input.split(" ", 2);
        if(input.toLowerCase().equals("bye")){
            return new ByeCommand(ls);
        }else if(input.toLowerCase().equals("list")){
            return new ListCommand(ls);
        }else if(splitList[0].toLowerCase().equals("done")){
            return new DoneCommand(ls, Integer.parseInt(splitList[1]) - 1);
        }else if(splitList[0].toLowerCase().equals("todo")){
            return new ToDoCommand(splitList[1],ls);
        }else if(splitList[0].toLowerCase().equals("deadline")){
            return new DeadlineCommand(splitList,ls);
        }else if(splitList[0].toLowerCase().equals("event")){
            return new EventCommand(splitList,ls);
        }else if(splitList[0].toLowerCase().equals("delete")){
            return new DeleteCommand(ls,Integer.parseInt(splitList[1]));
        }else if(splitList[0].toLowerCase().equals("find")) {
            return new FindCommand(ls, splitList[1]);
        }else {
            return new BlahCommand();
        }
    }
}
