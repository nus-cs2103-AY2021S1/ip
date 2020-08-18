import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String horiLine = "____________________________________________________________" + "\n";
    private static ArrayList<Task> List = new ArrayList<>();

    private static void greet() {
        String greeting = "Hello! I'm Duke" + "\n" + "What can I do for you?" + "\n";
        System.out.println(horiLine + greeting + horiLine);
    }

    private static void echo() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) { //exit condition
                String goodbye = "Bye. Hope to see you again soon!";
                System.out.println(horiLine + goodbye + "\n" + horiLine);
                break;
            } else if (input.equals("list")) {
                System.out.println(horiLine);
                for (Task task : List) {
                    System.out.println(task.getNumber() + "." + task.toString());
                }
                System.out.println(horiLine);
            } else if(input.startsWith("done")) {
                String[] number = input.split("done ");
                markDone(Integer.parseInt(number[1]));
            } else {
                List.add(new Task(input));
                System.out.println(horiLine + "added: " + input + "\n" + horiLine);
            }
        }
        sc.close();
    }

    private static void markDone(int i) {
        Task task = List.get(i - 1);
        task.setDone();
        System.out.println(horiLine + "Nice! I've marked this task as done:" +  "\n" +
                task.toString() + "\n" + horiLine);
    }

    public static void main(String[] args) {
        echo();
    }
}
