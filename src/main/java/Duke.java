import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greetings = "Hello! I'm Duke\n     What can I do for you?";
    public static String farewell = "Bye. Hope to see you again soon!";
    public static String doneAlert = "Nice! I've marked this task as done: ";


    public static void main(String[] args) {
        List<Task> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        printAnswer("", greetings);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();

            if (instruction.equals("bye")) {
                printAnswer("", farewell);
                break;
            } else if (instruction.length() > 4 && instruction.substring(0,4).equals("done")) {
                Integer index = Integer.valueOf(instruction.substring(5)) - 1;
                Task temp = result.get(index);
                temp.markAsDone();
                result.set(index, temp);
                printAnswer(doneAlert, "   " + temp.toString());
            } else if (instruction.equals("list")) {
                printList(result);
            } else {
                result.add(new Task(instruction));
                printAnswer("", "added: " + instruction);
            }
        }


    }

    public static void printAnswer(String guidance, String answer) {
        String Line = "____________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + Line);
        if (guidance.length() != 0) {
            System.out.println(bigSpace + guidance);
        }
        System.out.println(bigSpace + answer);
        System.out.println(smallSpace + Line + "\n");
    }

    public static void printList(List<Task> result) {
        String Line = "____________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";
        String reminder = "Here are the tasks in your list:";

        System.out.println(smallSpace + Line);
        System.out.println(bigSpace + reminder);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(bigSpace + (i + 1) + ". " + result.get(i));
        }
        System.out.println(smallSpace + Line + "\n");
    }
}
