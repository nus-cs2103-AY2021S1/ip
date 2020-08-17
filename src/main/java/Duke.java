import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String line = "-------------------------------------";
    public static String defaultError = "Wat talking you?";
    public static String addedMsg = "Alright, I've added a new order: ";
    public static ArrayList<Task> list = new ArrayList<>();

    public static void addItem(Task input) {
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
            if (nextLine.contains("todo") || nextLine.contains("deadline") || nextLine.contains("event")) {
                Task curr = new Task("");
                if(nextLine.contains("todo")){ // Case 1: checking to-do item
                    String arr[] = nextLine.split(" ", 2);
                    curr = new ToDo(arr[1]);
                    addItem(curr);

//                    nextLine = scanner.nextLine();
                } else if (nextLine.contains("deadline")) { // Case 2: Checking deadline
                    String arr[] = nextLine.split(" ", 2);
                    String info[] = arr[1].split("/by", 2);
                    curr = new Deadline(info[0], info[1]);
                    addItem(curr);

//                    nextLine = scanner.nextLine();
                } else if (nextLine.contains("event")) { // Case 3: checking event
                    String arr[] = nextLine.split(" ", 2);
                    String info[] = arr[1].split("/at", 2);
                    curr = new Event(info[0], info[1]);
                    addItem(curr);

//                    nextLine = scanner.nextLine();
                }

                System.out.println(line);
                System.out.println(addedMsg);
                System.out.println(curr);
                System.out.println("You have ordered " + list.size() + " items.");
                System.out.println(line);
                nextLine = scanner.nextLine();
            } else if (nextLine.contains("done")) { // Case 4: checking off an item
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
            } else if (nextLine.equals("list")) { // Case 5: Displaying List
                System.out.println(line);
                System.out.println("Here's what you have ordered so far...");
                for(int k = 0; k < list.size(); k++) {
                    System.out.println((k+1) + ": " + list.get(k));
                }
                System.out.println((line));
                nextLine = scanner.nextLine();
            }
            else { // Case 6: Default errors
                System.out.println(line);
                System.out.println(defaultError);
                System.out.println(line);
                nextLine = scanner.nextLine();
            }
        }

        // Ending the bot
        System.out.println(line);
        System.out.println("Bye! Please come again!");
        System.out.println(line);
    }
}

