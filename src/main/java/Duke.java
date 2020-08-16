import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {

    static char line  = (char) 	0x2501 ;
    static void loopMethod() {
        //gets input and displays it
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        if (word.equals("bye")) {
        encloseWithinHorizontalLines("Bye. Hope to see you again soon!");
        return;
        }
        else {
        encloseWithinHorizontalLines(word);
        }
        loopMethod();
    }

    static void encloseWithinHorizontalLines(String word) {
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
        System.out.println(word);
        System.out.println();
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    public static void main(String[] args) {
        encloseWithinHorizontalLines("\"Hello! I'm Duke \\n What can I do for you?\"");
        loopMethod();
    }
}
