import java.util.*;

public class TodoManager {
    List<Event> lst = new ArrayList<>();
    public void addEvent(String name) {
        lst.add(new Event(name));
        System.out.println(Helper.horiLine);
        System.out.println("added: " + name);
        System.out.println(Helper.horiLine);
    }
    public void listEvent() {
        System.out.println(Helper.horiLine);
        for (int i = 0; i < lst.size(); i++) {
            System.out.println((i + 1) + "." + " " + lst.get(i).name);
        }
        System.out.println(Helper.horiLine);
    }

}