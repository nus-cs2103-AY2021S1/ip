import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Yooo, I'm Deke.\nWhat can I do for you today?"); //Greeting

        ArrayList<Task> list = new ArrayList<>();
        String input = sc.nextLine();

        while (!input.isEmpty()) {
            if (input.equals("bye")) { //if user types "bye"
                System.out.println("Bye bye!!! See you again next time :)");
                input = "";
                sc.close();
            } else if (input.equals("list")) { //if user types "list"
                System.out.println("here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                input = sc.nextLine();
            } else if (input.contains("done")) { //if user input contains "done"
                int index = Integer.parseInt(input.split(" ")[1]);
                if (index >= 1) { //if input index is valid
                    Task newTask = list.get(index - 1).markAsDone();
                    list.set(index - 1, newTask);
                    System.out.println("Nice! I've marked this task as doneee! yayy:\n" + newTask);
                } else {
                    System.out.println("Please enter a valid input number");
                }
                input = sc.nextLine();
            } else { //if user types anything other than "bye" or "list"
                list.add(new Task(input));
                System.out.println("added: " + input);
                input = sc.nextLine();
            }
        }
    }
}
