package DateTime;

import Errors.ErrorExceptions;
import Tasks.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DateTimeManager {
    public static LocalDateTime setDateTime(CharSequence s){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu HHmm");
        LocalDateTime dt = LocalDateTime.parse(s,format);
        return dt;
    }
    public static LocalDate setDate(CharSequence s){
        LocalDate d = LocalDate.parse(s);
        return d;
    }
    public static void addDate(task task, String date) throws ErrorExceptions {
        Scanner sc = new Scanner(date);
        try{
            String d = sc.next();
            try{
                String t = sc.next();
                try{
                    String DT = d + " " + t;
                    LocalDateTime dt = DateTimeManager.setDateTime(DT);
                    task.setDate(dt);
                } catch(DateTimeParseException e){
                    throw new ErrorExceptions("Wrong date time format! dd-mm-yyyy HHmm");
                }
            } catch(NoSuchElementException e){
                throw new ErrorExceptions("Missing time!");
            }
        } catch(NoSuchElementException e){
            throw new ErrorExceptions("Missing Date!");
        }
    }
}
