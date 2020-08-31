package duke;

import java.util.ArrayList;

public class Finder {

    /**
     * Construct a Finder object
     */
    public Finder(){}

    /**
     * Finds and prints the Tasks that have matching keyword
     * @param list the Tasklist
     * @param keyword the keyword for seraching
     */
    public static void find(TaskList list, String keyword){
        ArrayList<Task> res = new ArrayList<>();
        for(int i = 0; i < list.getSize(); i++){
            if(list.get(i) instanceof Todo){
                Todo todo = (Todo)list.get(i);
                if(todo.getDescription().contains(keyword)){
                    res.add(todo);
                }
            } else if(list.get(i) instanceof Deadline) {
                Deadline ddl = (Deadline) list.get(i);
                if (ddl.getDescription().contains(keyword) || ddl.getBy().contains(keyword)) {
                    res.add(ddl);
                }
            } else {
                Event event = (Event)list.get(i);
                if(event.getDescription().contains(keyword) || event.getAt().contains(keyword)){
                    res.add(event);
                }
            }
        }
        Ui.printFindResult(res);
    }
}
