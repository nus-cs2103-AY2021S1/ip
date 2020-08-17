import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String line = "-------------------------------------";
    public static ArrayList<String> list = new ArrayList<>();

    public static void addItem(String input) {
        list.add(input);
    }

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
            if (nextLine.equals("list")) {
                System.out.println(line);
                for(int k = 0; k < list.size(); k++) {
                    System.out.println((k+1) + ": " + list.get(k));
                }
                System.out.println((line));
                nextLine = scanner.nextLine();
            } else {
                addItem(nextLine);
                System.out.println(line);
                System.out.println("added: " + nextLine);
                System.out.println(line);
                nextLine = scanner.nextLine();
            }
        }
        System.out.println(line);
        System.out.println("Bye! Please come again!");
        System.out.println(line);
    }
}

