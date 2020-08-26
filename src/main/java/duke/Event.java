package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Gets the description of the event
     * @param s user input
     * @return the description of the event
     */
    public static String getDescription(String s){
        String firstWord = "event", secondWord = "/at";
        int start = 0, len = s.length();
        while(!s.substring(start, start + 5).equals(firstWord)) start++;
        start += 6;
        if(start >= len) return s.substring(len);
        int end = start + 1;
        while(end + 3 < len && !s.substring(end, end + 3).equals(secondWord)) end++;
        if(end + 3 >= len) end = len + 1;
        return s.substring(start, end - 1);
    }

    /**
     * Gets the time of the event
     * @param s user input
     * @return the time of the event
     */
    public static String getTime(String s){
        String word = "/at";
        int i = 0, len = s.length();
        while(i + 3 < len && !s.substring(i, i + 3).equals(word)) i++;
        return i + 3 == len ? "" : s.substring(i + 4);
    }

    /**
     * Changes the date format in user input
     * @param command user input
     * @return the formatted date
     */
    public static String changeDateFormat(String[] command){
        String word = "/at";
        for(int i = 0; i < command.length; i++){
            if(command[i].equals(word)){
                if(i + 1 < command.length){
                    command[i + 1] = command[i + 1].replace('/', '-');
                    return command[i + 1];
                }
            }
        }
        return null;
    }

    /**
     * Returns the Time in user input
     * @param command user input
     * @return the Time in user input
     */
    public static String getLocalTime(String[] command){
        String word = "/at";
        for(int i = 0; i < command.length; i++) {
            if(command[i].equals(word)){
                if(i + 2 < command.length){
                    return command[i + 2];
                }
                else{
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Creates a Event object
     * @param input user input
     * @return a Event object
     */
    public static Event of(String input){
        String by = getTime(input), description = getDescription(input);
        String[] command = input.split(" ");
        int ptr = 0;
        while(command[ptr].equals("")) ptr++;
        if(description.equals("") || by.equals("") || command[command.length - 1].equals("/at") || ptr == command.length - 1){
            return null;
        }
        Event event = new Event(description, by);
        try{
            LocalDate date = LocalDate.parse(changeDateFormat(command));
            event.setDate(date);
        }
        catch(Exception e){

        }
        try{
            LocalTime time = LocalTime.parse(getLocalTime(command));
            event.setTime(time);
        }
        catch (Exception e){

        }
        return event;
    }

    /**
     * Create a Event object
     * @param description description of the event
     * @param at at of the event
     * @param isDone whether the event is done
     * @return a Event object
     */
    public static Event of(String description, String at, boolean isDone){
        Event event = new Event(description, at, isDone);
        String[] dateAndTime = at.replace('/', '-').split(" ");
        try{
            LocalDate d = LocalDate.parse(dateAndTime[0]);
            event.setDate(d);
        }
        catch (Exception e){

        }
        try{
            LocalTime t = LocalTime.parse(dateAndTime[1]);
            event.setTime(t);
        }
        catch (Exception e){

        }
        return event;
    }

    /**
     * Construct a Event object
     * @param description description of the Event
     * @param at at of the Event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Construct a Event object
     * @param description description of the Event
     * @param at at of the Event
     * @param isDone whether the Event is done
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the at
     * @return the at
     */
    public String getAt(){
        return at;
    }

    /**
     * Sets the date of the event
     * @param date the date to be set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Sets the time of the event
     * @param time the time to be set
     */
    public void setTime(LocalTime time){
        this.time = time;
    }

    /**
     * Returns the date
     * @return the date
     */
    public LocalDate getDate(){
        return date;
    }

    /**
     * Returns the time
     * @return the time
     */
    public LocalTime getTime(){
        return time;
    }

    /**
     * Returns whether is done
     * @return whether is done
     */
    public boolean getIsDone(){return isDone;}

    /**
     * Overrides the toString method
     * @return the String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (date == null ? at : (date.toString() + " (" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")")) +
                (time != null ? (" " + time.toString()) : "") + ")";
    }
}