package com.Duke.Commands;

import com.Duke.ProcessManager.TaskList;
import com.Duke.ProcessManager.UI;
import com.Duke.Tasks.Event;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command{

    private final String[] splitlist;
    private final TaskList ls;

    public EventCommand(String[] splitlist, TaskList ls) {
        this.splitlist = splitlist;
        this.ls = ls;
    }


    @Override
    public void execute() {
        try {
            String[] splitList2 = this.splitlist[1].split("/at ", 2);
            String[] splitList3 = splitList2[1].split("-", 2);
            Event event = new Event(splitList2[0], LocalTime.parse(splitList3[0]), LocalTime.parse(splitList3[1]), false);
            UI.eventCalled(ls,event);
        }catch(DateTimeParseException e){
            UI.printError("     \u2639 OOPS!!! The format of your start or end time is not correct, format it as HH:mm");
        } catch(Exception e){
            UI.printError("     \u2639 OOPS!!! The description or the time duration of a event cannot be empty.");
        }
    }
}
