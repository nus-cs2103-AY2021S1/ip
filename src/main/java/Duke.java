import java.util.Scanner;

public class Duke {

    public static void chatbot() {
        Scanner sc = new Scanner(System.in);

        String indent = "    ";
        String upperLine = indent + "____________________________________" + "\n";
        String lowerLine =  indent + "____________________________________" +"\n";
        String greetings = indent + "Hello! I'm Duke" + "\n"
                + indent + "What can I do for you?" +"\n";

        System.out.println(upperLine + greetings + lowerLine);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String outputIndent = indent + " ";
            String output = outputIndent + input + "\n";

            if (input.equals("bye")) {
                String exit = outputIndent + "Bye. Hope to see you again soon!" + "\n";
                System.out.println(upperLine + exit + lowerLine);
                break;
            }
            System.out.println(upperLine + output + lowerLine);

        }
    }


    public static void main(String[] args) {
        chatbot();
    }
}
