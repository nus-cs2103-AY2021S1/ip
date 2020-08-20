import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    private static List<Task> lists = new ArrayList<>();

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
            } else if(input.length() >= 6 && input.substring(0, 5).equals("done ")) {
                System.out.println("Nice! I've marked this task as done: ");
                int num = Integer.parseInt(input.substring(5, 6));
                lists.get(num - 1).markAsDone();
                System.out.println(lists.get(num - 1).toString());
                input = scanner.nextLine();
            } else {
                System.out.println("added: " + input);  // Output user input
                lists.add(new Task(input));
                input = scanner.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < lists.size(); i++ ) {
            System.out.println(i+1 + "." + lists.get(i).toString());
        }
    }

    public static void greeting() {
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }

}
