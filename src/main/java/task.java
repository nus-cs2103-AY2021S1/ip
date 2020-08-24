import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class task {
    protected static int count = 0;
    protected boolean completed;
    protected String name;
    protected int index;
    protected LocalDateTime date;
    protected String type;

    public task(String name, String type){
        this.count++;
        this.index = this.count;
        this.name = name;
        this.completed = false;
        this.type = type;
    }


    public String taskName(){
        return this.name;
    }

    public int taskIndex(){
        return this.index;
    }

    public boolean taskCompleted(){
        return this.completed;
    }

    public void done(){
        this.completed = true;
    }

    public String read(){
        String done = "";
        if(this.completed){
            done = "[✓]";
        }
        else{
            done = "[X]";
        }
        if(this.type.equals("[T]")){
            return this.type + done + " " + this.name;
        } else if(this.type.equals("[D]")){
            return this.type + done + " " + this.name + "(by: " +
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + ")";
        } else{
            return this.type + done + " " + this.name + "(at: " +
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + ")";
        }
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

    public void delete(){
        this.count = count - 1;
        String line1 = "Noted. I've removed this task: ";
        String line2 = "  " + this.read();
        String line3 = "Now you have " + this.count + " tasks in the list.";
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
    }

    public void print(){
        System.out.println("Got it. I've added this task: ");
        if(this.type.equals("[T]")){
            System.out.println("  " + this.type + "[X]" + " " + this.name);
        } else if(this.type.equals("[D]")){
            System.out.println("  " + this.type + "[X]" + " " + this.name + "(by: " +
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + ")");
        } else{
            System.out.println("  " + this.type + "[X]" + " " + this.name + "(at: " +
                    this.date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HHmm")) + ")");
        }
        System.out.println("Now you have " + count + " tasks in the list.");
    }
}
