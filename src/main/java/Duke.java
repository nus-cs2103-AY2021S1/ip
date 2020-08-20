import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static List<String> lists = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greeting();

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while(true) {
            if(input.equals("bye")){
                break;
            } else if(input.equals("list")) {
                displayList();
                input = scanner.nextLine();
            } else {
                System.out.println("added: " + input);  // Output user input
                lists.add(input);
                input = scanner.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void displayList() {
        for(int i = 0; i < lists.size(); i++ ) {
            System.out.println(i+1 + ". " + lists.get(i));
        }
    }

    public static void greeting() {
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }

}
