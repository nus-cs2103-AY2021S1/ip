import java.util.Scanner;

public class Duke {

    public static String line = "-------------------------------------";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nextLine = "";
        // Introduction
        System.out.println(line);
        System.out.println("Welcome to mel's drive-in!");
        System.out.println("What would you like to have?");
        System.out.println(line);

        nextLine = scanner.nextLine();

        while (!nextLine.equals("bye")) {
            System.out.println(line);
            System.out.println(nextLine);
            System.out.println(line);
            nextLine = scanner.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye! Please come again!");
        System.out.println(line);
    }
}

