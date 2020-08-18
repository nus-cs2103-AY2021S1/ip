import java.util.List;
import java.util.ArrayList;

public class StringIdentifier {
    private static boolean isProgramRunning = true;
    private static List<String> lst = new ArrayList<>();

    public boolean isRunning() {
        return this.isProgramRunning;
    }

    public void checker(String str) {
        System.out.println("    ____________________________________________________________");
        if (str.equals("bye")) {
            close();
        } else if (str.equals("list")) {
            displayList();
        } else {
            store(str);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void store(String str) {
        this.lst.add(str);
        System.out.println("     added: " + str);
    }

    public void displayList() {
        int size = this.lst.size();
        int index = 1;
        for (int i = 0; i < size; i++) {
            System.out.println("     " + index + ". " + this.lst.get(i));
        }
    }

    public void close() {
        this.isProgramRunning = false;
        System.out.println("     Bye. Hope to see you again soon!");
    }
}
