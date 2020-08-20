import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static boolean isNumber(char c) {
        int c_int = Character.getNumericValue(c);
        return c_int <= 9 && c_int >= 0;
    }

    public static boolean isMarkingDone(String command) {
        String[] lst = command.split(" ");

        if (lst.length == 1 || !lst[0].equals("done")) {
            return false;
        } else {
            String secondElement = lst[1];

            if (secondElement.length() == 2) {
                return isNumber(secondElement.charAt(0)) && isNumber(secondElement.charAt(1));
            } else if (secondElement.length() == 1) {
                return isNumber(secondElement.charAt(0));
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Hello! I'm Duke! \nWhat can I do for you?");
        System.out.println("————————————————————————————————————————————————————————————");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("————————————————————————————————————————————————————————————");
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else if (isMarkingDone(command)) {
                int taskId = Integer.parseInt(command.split(" ")[1]) - 1;
                if (taskId < tasks.size()) {
                    tasks.get(taskId).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskId).toString());
                } else {
                    System.out.println("Task does not exist; Try another task number");
                }

            } else {
                Task newTask = new Task(command);
                tasks.add(newTask);
                System.out.println("added: " + command);
            }
            System.out.println("————————————————————————————————————————————————————————————");
            command = sc.nextLine();
        }

        System.out.println("————————————————————————————————————————————————————————————");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("————————————————————————————————————————————————————————————");
        sc.close();
    }
}
