import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;
    protected LocalDate date;
    protected LocalTime time;

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

    public static String getTime(String s){
        String word = "/at";
        int i = 0, len = s.length();
        while(i + 3 < len && !s.substring(i, i + 3).equals(word)) i++;
        return i + 3 == len ? "" : s.substring(i + 4);
    }

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

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time){
        this.time = time;
    }

    public LocalDate getDate(){
        return date;
    }

    public LocalTime getTime(){
        return time;
    }

    public boolean getIsDone(){return isDone;}

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (date == null ? at : (date.toString() + " (" + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")")) +
                (time != null ? (" " + time.toString()) : "") + ")";
    }
}