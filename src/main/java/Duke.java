import java.util.*;

public class Duke {
    private static ArrayList<Task> userList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // when user inputs bye
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (input.equals("list")) {
                // when user wants to print list of task
                System.out.println("Herea re the tasks in your list:");
                int index = 1;
                for (Task task : userList) {
                    System.out.println(index + ". " + task.toString());
                    index++;
                }

            } else if (input.startsWith("done")) {
                // when user completes task
                String[] temp = input.split(" ");
                int listNumber = Integer.parseInt(temp[1]);
                userList.get(listNumber - 1).completedTask();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(userList.get(listNumber - 1).toString());

            } else {
                // normal input
                userList.add(new Task(input));
                System.out.println("added: " + input);

            }
        }
    }

}
