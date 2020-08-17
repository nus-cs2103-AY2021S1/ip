import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String line = "-------------------------------------";
    public static String defaultError = "Wat talking you?";
    public static ArrayList<Task> list = new ArrayList<>();

    public static void addItem(String input) {
        list.add(new Task(input));
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
            if (nextLine.contains("done")) {
                String arr[] = nextLine.split(" ", 2);
                int index = Integer.parseInt(arr[1]);

                if (index > list.size()) {
                    System.out.println(defaultError);
                } else {
                    list.get(index-1).markAsDone();

                    System.out.println(line);
                    System.out.println("Great choice! I have taken your order: ");
                    System.out.println(list.get(index-1));
                    System.out.println(line);
                }
                nextLine = scanner.nextLine();
            } else if (nextLine.equals("list")) {
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

