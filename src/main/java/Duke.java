import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "\t-------------------------------------------------------";
        System.out.println(horizontalLine);
        System.out.println("\tHello! I'm Duke\n" + "\t" + "What can I do for you?");
        System.out.println(horizontalLine);
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int index = 0;
        while (scanner.hasNext()) {
            System.out.println(horizontalLine);
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                scanner.close();
                break;
            }
            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.println("\t" + list[i]);
                }
                System.out.println(horizontalLine);
            } else {
                list[index] = String.format("%d. %s", ++index, userInput);
                System.out.println("\tadded: " + userInput);
                System.out.println(horizontalLine);
            }
        }
    }
}
