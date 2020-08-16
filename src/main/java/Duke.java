import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void chatbot() {
        Scanner sc = new Scanner(System.in);
        List<String> lst = new ArrayList<>();
        String listCommand = "list";

        String indent = "    ";
        String upperLine = indent + "____________________________________" + "\n";
        String lowerLine =  indent + "____________________________________" +"\n";
        String greetings = indent + "Hello! I'm Duke" + "\n"
                + indent + "What can I do for you?" +"\n";

        System.out.println(upperLine + greetings + lowerLine);
        int numberOfItems = 0;
        while (sc.hasNext()) {

            String input = sc.nextLine();
            String outputIndent = indent + " ";
            String output = input + "\n";
            String addedMessage = outputIndent + "added: ";

            if (input.equals("bye")) {
                String exit = outputIndent + "Bye. Hope to see you again soon!" + "\n";
                System.out.println(upperLine + exit + lowerLine);
                break;
            }

            if (input.equals(listCommand)) {
                StringBuilder concat = new StringBuilder();
                for (String s : lst) {
                    concat.append(s);
                }
                System.out.println(upperLine + concat.toString() + lowerLine);
            } else {
                numberOfItems++;
                lst.add(outputIndent + numberOfItems + "." + " " + output);
                System.out.println(upperLine + addedMessage
                        + input +"\n" + lowerLine);

            }


        }
    }


    public static void main(String[] args) {
        chatbot();
    }
}
