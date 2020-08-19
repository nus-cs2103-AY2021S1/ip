import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class MyList {
    static List myList = new ArrayList<>();

    public static String addTask(String task) {
        MyList.myList.add(task);
        return "added: " + task;
    }

    public static String returnList() {
        Iterator i = myList.iterator();
        StringBuilder output = new StringBuilder();
        int counter = 1;
        output.append(counter + ". ").append(i.next());
        while (i.hasNext()) {
            counter ++;
            output.append("\n").append(counter + ". ").append(i.next());
        }
        return output.toString();
    }
}
