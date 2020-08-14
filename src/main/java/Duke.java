import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Chore> toDoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String inputLine = sc.nextLine().trim();
        while (!inputLine.equals("bye")) {
            String[] arr = inputLine.split(" ");
            if (inputLine.equals("list")) {
                displayList();
            }
            else if (arr.length == 2 && arr[0].equals("done") && isInteger(arr[1])) {
                int num = Integer.parseInt(arr[1]);
                if (num >= 1 && num <= toDoList.size()) {
                    doneTask(num);
                }
                else {
                    invalidRangeError();
                }
            }
            else {
                addToDo(inputLine);
            }
            inputLine = sc.nextLine();
        }
        goodbyeMessage();
        sc.close();
    }

    private static void hrTag() {
        System.out.println("____________________________________________________________");
    }

    private static void welcomeMessage() {
        hrTag();
        System.out.println("Hello! I am your mother!");
        System.out.println("What can I do for you son!");
        hrTag();
    }

    private static void addToDo(String toDo) {
        hrTag();
        System.out.println("Chore added: " + toDo);
        toDoList.add(new Chore(toDo));
        hrTag();
    }

    private static void displayList() {
        hrTag();
        System.out.println("Here are the chores you have left:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println((i+1) + "." + toDoList.get(i));
        }
        hrTag();
    }

    private static void goodbyeMessage() {
        hrTag();
        System.out.println("Goodbye my child!");
        hrTag();
    }

    private static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static void doneTask(int num) {
        hrTag();
        if (toDoList.get(num - 1).getIsDone()) {
            System.out.println(String.format("Sorry! You have already completed '%s'.",
                    toDoList.get(num - 1).getDescription()));
        } else {
            System.out.println(String.format("Great job son! I'll mark '%s' as done for you. ^^",
                    toDoList.get(num - 1).getDescription()));
            toDoList.get(num - 1).markAsDone();
        }
        hrTag();
    }

    private static void invalidRangeError() {
        hrTag();
        System.out.println("Uh oh! That number looks like it is out of range. Check again!");
        // can add feature for user to add todo
        hrTag();
    }
}
