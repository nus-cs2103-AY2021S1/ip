import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Luke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todo = new ArrayList<>();
        System.out.printf("Luke : Hey there! I'm Luke.\nPlease tell me what to add to your to-do list.\nYou : ");
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (input.equals("list")) {
                String todoSummary = "Here are the tasks in your to-do list.";
                for (int i = 0; i < todo.size(); i++) {
                    Task current = todo.get(i);
                    todoSummary += String.format("\n%d. [%s] %s", i + 1, current.getStatusIcon(), current.getDescription());
                }
                System.out.printf("%s\nYou : ", todoSummary);
            } else if (Pattern.matches("^(done)+ ?[0-9]*$", input)) {
                input = input.replaceAll("\\D+", ""); //extract only the digits from the input
                Task done = todo.get(Integer.parseInt(input) - 1);
                done.markAsDone();
                System.out.printf("Luke : The following task has successfully been marked as done!\n\t[%s] %s\nYou : ",
                        done.getStatusIcon(), done.getDescription());
            } else if (input.equals("bye")) {
                System.out.println("Luke : Oh, are you leaving? Hope to see you soon!");
                break;
            } else {
                todo.add(new Task(input));
                System.out.printf("Luke : Added '%s' to your to-do list.\nYou : ", input);
            }
        }
    }
}
