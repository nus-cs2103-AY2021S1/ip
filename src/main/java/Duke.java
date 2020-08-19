import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputList= new ArrayList<String>();
        int count = 1;

        while (true) {

            String input = scanner.nextLine();

            if (input.equals("list")) {

                for (int i = 0; i < inputList.size() ; i++) {
                    System.out.println(inputList.get(i));
                }
            } else if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                inputList.add(String.valueOf(count) + ". " + input);
                count++;
            }
        }
    }
}
