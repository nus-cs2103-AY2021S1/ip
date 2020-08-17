import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static String line = "    ____________________________________________________________";
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> arr =  new ArrayList<Task>();

    private static void markAsDone(String input) {
        String index = input.substring(5, input.length());
        int number = Integer.parseInt(index) - 1;
        arr.get(number).setDone();
        System.out.println(line);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("       %s", arr.get(number).getOutput()));
        System.out.println(line);
    }

    private static void printList() {
        System.out.println("     Here are the tasks in your list:");
        System.out.println(line);
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(String.format("     %d. %s", i + 1, arr.get(i).getOutput()));
        }
        System.out.println(line);
    }

    private static void addNewTask(String input) {
        arr.add(new Task(input));
        System.out.println(line);
        System.out.println(String.format("     added: %s", input));
        System.out.println(line);
    }

    public static void main(String[] args) {
        boolean on = true;
        System.out.println(line);
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println(line);

        while(on) {
            String input = sc.nextLine();
            if(input.compareTo("bye") == 0) {
                on = false;
                System.out.println(line);
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println(line);
            } else if (input.indexOf("done") == 0) {
                markAsDone(input);
            } else if (input.compareTo("list") == 0){
                printList();
            } else {
                addNewTask(input);
            }
        }
    }
}
