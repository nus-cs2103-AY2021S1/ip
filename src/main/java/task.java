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

    public String taskType(){ return this.type; }

    public LocalDateTime taskDate(){ return this.date; }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

}
