import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int count = 0;
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNext()) {
            String input = userInput.nextLine();
            String[] splitArr = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.println((i + 1) + ". " + tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                    } else {
                        break;
                    }
                }
            } else if (splitArr.length == 2 && splitArr[0].equals("done") && Integer.parseInt(splitArr[1]) > 0) {
                int index = Integer.parseInt(splitArr[1]);
                tasks[index - 1].setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[index -1].getStatusIcon() + " " + tasks[index - 1].getDescription());
            } else {
                tasks[count] = new Task(input);
                count ++;
                System.out.println("added: " + input);
            }
        }
    }
}
