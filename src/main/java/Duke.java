import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I am Duke\n"
                + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String terminate = "bye";
        Tasks newTask = new Tasks();
        String input;

        while (!(input = sc.nextLine()).equals(terminate)) {

            if (input.equals("")) {
                continue;
            } else if (!input.equals("list")) {
                newTask.addTask(input);
            } else {
                newTask.getList();
            }

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
