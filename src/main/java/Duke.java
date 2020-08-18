import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // this method prints a horizontal line of fixed length
    public static void horiLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
            if (i == length - 1) System.out.println("");
        }
    }


    // Note that all the outputs are formatted with two spaces before.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList tempStorage = new ArrayList();
        // welcome message
        horiLine(60);
        System.out.println("  Hello! I'm IntelliGent!\n  What can I do for you?");
        horiLine(60);
        while (sc.hasNextLine()) {
            String nextInput = sc.nextLine();
            if (nextInput.equals("bye")) {
                horiLine(60);
                System.out.println("  Bye. Hope to see you again soon!");
                horiLine(60);
                sc.close();
                break;
            } else if (nextInput.equals("list")) {
                horiLine(60);
                for (int i = 0; i < tempStorage.size(); i++) {
                    System.out.println("  " + (i+1) + ". " + tempStorage.get(i));
                }
                horiLine(60);
            } else {
                horiLine(60);
                tempStorage.add(nextInput);
                System.out.println("  added: " + nextInput);
                horiLine(60);
            }
        }
    }
}
