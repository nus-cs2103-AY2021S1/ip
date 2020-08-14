import java.util.*;

public class Duke {
    private static ArrayList<String> userList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int listNumber = 0;
                for (String str : userList) {
                    listNumber++;
                    System.out.println(listNumber + ". " + str);
                }
            } else {
                    userList.add(input);
                    System.out.println("added: " + input);
            }
        }
    }

}
