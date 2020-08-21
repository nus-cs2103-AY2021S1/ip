import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Ui {
    private static final String horizontalLine = "\t=================================================================================";
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
        print(output("Hello! I'm Duke\n\t  What can I do for you?"));
    }

    public void print(String str) {
        System.out.println(str);
    }

    public String output(String message) {
        return horizontalLine + "\n\t  " + message + "\n" + horizontalLine + "\n";
    }

    public void printList(int count, List<Task> list, Predicate<Task> predicate, String note) {
        System.out.println(horizontalLine + "\n\t  " + "Here are the tasks " + note + "in your list:");
        for (int i = 0; i < count; i++) {
            Task task = list.get(i);
            if (predicate.test(task)) {
                System.out.println("\t  " + (i + 1) + "." + list.get(i));
            }
        }
        System.out.println(horizontalLine + "\n");
    }

    public String readInput() {
        return sc.nextLine();
    }
}
