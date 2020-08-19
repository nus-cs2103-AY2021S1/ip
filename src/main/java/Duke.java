import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Beginning of my own code:
        System.out.println("Hello! Duke at your serve. Please name your request.");
        Scanner sc = new Scanner(System.in);

        //Object to store the list
        ArrayList<String> userList = new ArrayList<>();
        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();

            //add items to list
            if (!userMessage.equals("bye") && !userMessage.equals("list")) {
                userList.add(userMessage);
                System.out.println("item added: " + userMessage);
            }

            //list down the contents in the list
            if (userMessage.equals("list")) {
                System.out.println("Here is your list: ");
                for (int i = 0; i < userList.size(); i++) {
                    System.out.println((i+1) + ". "+ userList.get(i));
                }
            }

            if (userMessage.equals("bye")) {
                System.out.println("Bye! Nice serving you. Hope to see you again soon! :D");
                break;
            }
        }


    }
}
