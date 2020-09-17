package com.Duke.Commands;

import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.Event;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
/**
 * This class acts as a model for the execution of the EventCommand
 */
public class EventCommand extends Command{

    private final String[] splitList;
    private final TaskList taskList;

    public EventCommand(String[] splitList, TaskList taskList) {
        this.splitList = splitList;
        this.taskList = taskList;
    }

    /**
     * Simulates Duke executing the Event Command
     * @return The response to the command "event"
     */
    @Override
    public String execute() {
        try {
            String[] splitList2 = splitList[1].split(" /on ", 2);
            String[] splitList3 = splitList2[1].split("/at ", 2);
            String[] splitList4 = splitList3[1].split("-", 2);
            System.out.println(splitList3[0]);
            System.out.println(splitList4[0]);
            System.out.println(splitList4[1]);
            Event event = new Event(splitList2[0],
                    LocalTime.parse(splitList4[0]),
                    LocalTime.parse(splitList4[1]),
                    false,
                    LocalDate.parse(splitList3[0].trim()));
            return UI.eventCalled(taskList,event);
        }catch(DateTimeParseException e){
            return UI.printError("\u2639 OOPS!!! The format of your start or end time is not correct, format it as HH:mm");
        } catch(Exception e){
            return UI.printError("\u2639 OOPS!!! The description or the time duration of a event cannot be empty.");
        }
    }
}
