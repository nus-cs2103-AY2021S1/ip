package Duke.Commands;

import Duke.Errors.DeadlineException;
import Duke.Errors.DukeException;
import Duke.Errors.EventException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Event;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * handles cases when Event is keyword
 */
public class EventCommand extends AddCommand{
    /**
     * Assigns string to a value of string
     *
     * @param string assigns string to this this.string
     */
    public EventCommand(String string) {
        super(string);
    }

    /**
     * Adds Event task or handle exceptions
     *
     * @param tasks to change the taskList if necessary when no error
     * @param ui
     * @param storage to change the file in the if necessary when no error
     * @return String returns the string of the output that informs the action has been complete.
     * @throws DukeException if there no description after Event no time or time is wrong format
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (isDescriptionAbsent()) {
            throw new EventException(true, false, false, false, false);
        }
        String[] dataSplit = splitData(); //splits String into different of Event name, start and end time and/or date
        Event event = provide(dataSplit[0], dataSplit[1], dataSplit[2]); //gives the event or throws exception
        try {
            return updateTaskList(storage, event, tasks); //updates the Task list in Storage and TaskList since the Event is added
        } catch (IOException i) {
            throw new FileAbsentException(storage.getFilePath());
        }
    }

    /**
     * checks whether the commandDescription contains the description
     *
     * @return true if description absent and false otherwise.
     */
    private boolean isDescriptionAbsent(){
        return commandDescription.length() == 5 || commandDescription.length() == 6; // since description is present after index 6
    }
    /**
     * splits the data into Deadline description and the Deadline date and/ or time. If the date and/or time is absent
     * then DeadlineException is thrown.
     *
     * @return the String array where the first String is the name of the Deadline and the second is the date and/or time
     * of start for event, third is the date and/or time of end for event.
     * @throws EventException thrown when the time and/or date is absent.
     */
    private String[] splitData() throws EventException {
        String s = "";
        int index = -1;
        int end = -1;
        boolean startPresent = false;
        boolean endPresent = false;
        String start = "";
        for (int i = 5; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '/') {
                index = i;
                startPresent = true; //the presence of / indicates that the start time is present.
                break;
            }
            s = s + commandDescription.charAt(i);
        }
        for (int i = index + 1; i < commandDescription.length(); i++) {
            if (commandDescription.charAt(i) == '-' && i != commandDescription.length() - 1) {
                end = i;
                endPresent = true; // - indicates that end time is present
                break;
            }
            start = start + commandDescription.charAt(i);
        }
        if (!startPresent) {
            throw new EventException(false, false, false, false, true);
        }
        if (!endPresent) {
            throw new EventException(false, true, false, false, false);
        }
        String[] dataSplit = new String[3];
        dataSplit[0] = s.substring(1, s.length() - 1);
        dataSplit[1] = commandDescription.substring(index + 4, end);
        dataSplit[2] = commandDescription.substring(end + 1);
        return dataSplit;
    }
    /**
     * This method creates a deadline task by checking whether the date and/or time given is in the correct
     * format. If it is then Deadline task is returned else, DeadlineException is returned.
     *
     * @param name description of Deadline task
     * @param start gives the dateTime of the start to check whether they are in the correct format
     * @param end gives the dateTime of the end to check whether they are in the correct format
     * @return deadline if the dateTime is in the correct format
     * @throws DeadlineException if the dateTime is in the incorrect format
     */
    private static Event provide(String name, String start, String end) throws DukeException {
        Event e;
        try{
            LocalDate startDate = stringToLocalDate(start); //converts start to date
            LocalDate endDate = stringToLocalDate(end); //converts end to date

            if(startDate.isAfter(endDate)){
                throw new EventException(false, false, true, false, false); //if start > end then it throws this error.
            }
            e = new Event(name, startDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")),
                    endDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        }catch (EventException event){
            throw new EventException(false, false, true, false, false);
        }catch (DateTimeException d) {
            try {
                LocalDateTime startDateTime = stringToLocalDateTime(start);//converts start to dateTime
                LocalDateTime endDateTime = stringToLocalDateTime(end);//converts end to dateTime
                if(startDateTime.isAfter(endDateTime)){
                    throw new EventException(false, false, true, false, false); //if start > end it throws this error
                }
                e = new Event(name, startDateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")),
                        endDateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (EventException event){
                throw new EventException(false, false, true, false, false);
            }
            catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = stringToLocalTime(start);//converts start to time
                    LocalTime endDate = stringToLocalTime(end);//converts end to time
                    if(parsedDate.isAfter(endDate)){
                        throw new EventException(false, false, true, false, false);//if start > end it throws error
                    }
                    e = new Event(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")),
                            endDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                }catch (EventException y){
                    throw new EventException(false, false, true, false, false);
                }catch (DateTimeException z) {
                    throw new EventException(false, false, false, true, false);
                }
            }
        }
        return e;
    }


}
