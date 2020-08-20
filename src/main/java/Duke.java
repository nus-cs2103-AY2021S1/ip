import java.util.Scanner;

public class Duke {
    private static String textFormat(String str) {
        return "\t" + "-------------------------------------------------------\n\t"
                + str + "\n"
                + "\t" + "-------------------------------------------------------\n";
    }
    public static void main(String[] args) {
        System.out.print(textFormat("Hello! I'm Duke\n" + "\t" + "What can I do for you?"));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(textFormat("Bye. Hope to see you again soon!"));
                scanner.close();
                break;
            }
            System.out.println(textFormat(userInput));
        }
    }
}
