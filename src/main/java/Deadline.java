import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends task{
    private String type = "[D]";
    private LocalDateTime date;

    public Deadline(String name){
        super(name);
    }

    public void getDT(){
        System.out.println(this.date.toString());
    }

    public void addDate(String date) throws ErrorExceptions{
        Scanner sc = new Scanner(date);
        try{
            String d = sc.next();
            try{
                String t = sc.next();
                try{
                    String DT = d + " " + t;
                    System.out.println(DT);
                    LocalDateTime dt = DateTimeManager.setDateTime(DT);
                    this.date = dt;
                    System.out.println("Done");
                } catch(DateTimeParseException e){
                    throw new ErrorExceptions("Wrong date time format! dd-mm-yyyy HHMM \n" + e);
                }
            } catch(NoSuchElementException e){
                throw new ErrorExceptions("Missing time!");
            }
        } catch(NoSuchElementException e){
            throw new ErrorExceptions("Missing Date!");
        }
    }

    public String read(){
        String done = "";
        if(super.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return this.type + done + " " + this.name +
                        this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm"));
    }

    public String read2(){
        String done = "";
        if(this.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        return this.type + done + " " + this.name +
                        this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm"));
    }

    public void print(){
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.type + "[X]" + " " + this.name +
                        this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")));
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
