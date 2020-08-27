package com.Duke.TaskManager;

import com.Duke.Commands.*;

public class DukeParser {
    private final TaskList ls;

    public DukeParser(TaskList ls) {
        this.ls = ls;
    }

    public Command parse(String input){
        String[] splitList = input.split(" ", 2);
        if(input.equals("bye")){
            return new ByeCommand(ls);
        }else if(input.equals("list")){
            return new ListCommand(ls);
        }else if(splitList[0].equals("done")){
            return new DoneCommand(ls, Integer.parseInt(splitList[1]) - 1);
        }else if(splitList[0].equals("todo")){
            return new ToDoCommand(splitList[1],ls);
        }else if(splitList[0].equals("deadline")){
            return new DeadlineCommand(splitList,ls);
        }else if(splitList[0].equals("event")){
            return new EventCommand(splitList,ls);
        }else if(input.equals("blah")){
            return new BlahCommand();
        }else if(splitList[0].equals("delete")){
            return new DeleteCommand(ls,Integer.parseInt(splitList[1]));
        }else{
            return new TaskCommand(ls,input);
        }
    }
}
