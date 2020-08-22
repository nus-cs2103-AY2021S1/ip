import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDo extends Task {
    public ToDo(String name, LocalDateTime date){
        super(name, date);
    }

    @Override
    public String toString(){
        return "[T]"+ super.toString();
    }
}
