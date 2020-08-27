package com.Duke.Commands;

import com.Duke.ProcessManager.TaskList;
import com.Duke.ProcessManager.UI;
import com.Duke.Tasks.Deadline;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{
    private final String[] splitList;
    private final TaskList ls;

    public DeadlineCommand(String[] splitList, TaskList ls) {
        this.splitList = splitList;
        this.ls = ls;
    }


    public void execute(){
        try{
            String[] splitList2 = splitList[1].split("/by ", 2);
            Deadline deadline = new Deadline(splitList2[0], LocalDate.parse(splitList2[1]), false);
            UI.deadlineCalled(ls,deadline);
        } catch (DateTimeParseException e){
            UI.printError("     \u2639 OOPS!!! The deadline is not of the proper format, make sure you enter it as YYYY-MM-dd");
        } catch (Exception e){
            UI.printError("     \u2639 OOPS!!! The description is empty or you have not entered a proper deadline.");
        }
    }
}
