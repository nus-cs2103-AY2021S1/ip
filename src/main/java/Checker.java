import java.time.LocalDate;

public class Checker {

    public Checker(){}

    public static void checkAndPrint(TaskList list){
        for(int i = 0; i < list.getSize(); i++){
            if(list.get(i) instanceof Deadline){
                Deadline ddl = (Deadline)list.get(i);
                if(ddl.getDate() != null && ddl.getDate().equals(LocalDate.now()) && !ddl.getIsDone()){
                    System.out.println("Deadline: (" + ddl.getDescription() + ") is due today");
                }
            }
            else if(list.get(i) instanceof Event){
                Event event = (Event)list.get(i);
                if(event.getDate() != null && event.getDate().equals(LocalDate.now()) && !event.getIsDone()){
                    System.out.println("Event: (" + event.getDescription() + ") is today");
                }
            }
        }
    }
}
