import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static String greetings = "Hello! I'm Duke\n     What can I do for you?";
    public static String farewell = "Bye. Hope to see you again soon!";


    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        printAnswer(greetings);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();

            if (instruction.equals("bye")) {
                printAnswer(farewell);
                break;
            } else if (instruction.equals("list")) {
                String resultString = "";
                for (int i = 0; i < result.size(); i++) {
                    if (i < result.size() -1) {
                        resultString = resultString + (i + 1) + ". " + result.get(i) + "\n     ";
                    } else {
                        resultString = resultString + (i + 1) + ". " + result.get(i);
                    }
                }
                printAnswer(resultString);

            } else {
                result.add(instruction);
                printAnswer("added: " + instruction);
            }
        }


    }

    public static void printAnswer(String answer) {
        String Line = "____________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + Line);
        System.out.println(bigSpace + answer);
        System.out.println(smallSpace + Line + "\n");
    }
}
