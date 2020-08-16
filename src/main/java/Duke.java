import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TaskList list = new TaskList();
        String input;
        System.out.println("Hi there! I'm Peanut.\nHow can I be of assistance?\n");
        input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("");
            if (input.equals("list")) {
                System.out.println(list);
            } else {
                Task task = Task.makeTask(input);
                list.addTask(task);
            }
            input = sc.nextLine();
        }
        System.out.println("\nBye! Sad to see you go :(\n");

    }

}



