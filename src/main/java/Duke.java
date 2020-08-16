import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    static char line  = (char) 	0x2501 ;
    static List<Task> tasks = new ArrayList<>();
    static void loopMethod() {

        //gets input and displays it
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        if (word.equals("bye")) {
            printTopLine();
            System.out.println("Bye. Hope to see you again soon!");
            printBottomLine();
        return;
        }
        else if (word.equals("list")) {
            printTopLine();
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            printBottomLine();
        }
        else {
            Task newTask = new Task(word);
            tasks.add(newTask);
            printTopLine();
            System.out.println("added: " + word);
            printBottomLine();
        }
        loopMethod();
    }

    static void printTopLine() {
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    static void printBottomLine() {
        System.out.println();
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    public static void main(String[] args) {
        printTopLine();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        printBottomLine();
        loopMethod();
    }
}
