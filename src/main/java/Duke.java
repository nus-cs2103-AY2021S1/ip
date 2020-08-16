import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> todoList = new ArrayList<>();

        while(sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println((i + 1) + ". " + todoList.get(i));
                }
            } else {
                todoList.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
