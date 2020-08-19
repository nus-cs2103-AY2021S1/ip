import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        ////Beginning of my own code:
        System.out.println("Hello! Duke at your serve. Please name your request.");
        Scanner sc = new Scanner(System.in);

        //Object to store the list
        ArrayList<Task> itemList = new ArrayList<>();


        while (sc.hasNextLine()) {
            String userMessage = sc.nextLine();


            //add items to list
            if (!userMessage.equals("bye") && !userMessage.equals("list") && !userMessage.contains("done")) {
                Task newItem = new Task(userMessage, Task.Status.PENDING);
                itemList.add(newItem);
                System.out.println("item added: " + newItem.name);
            }

            //list down the contents in the list
            if (userMessage.equals("list")) {
                System.out.println("Here is your list: ");
                for (int i = 0; i < itemList.size(); i++) {
                    Task task = itemList.get(i);
                    System.out.println((i+1) + ". "+ task.status.statusToSymbol() + task.name);
                }
            }


            //mark something as done
            if (userMessage.contains("done")) {
                int index = Character.getNumericValue(userMessage.charAt(5)) - 1;
                Task task = itemList.get(index);
                task.markAsDone();
                System.out.println("Good job! You have finished this task!");
                System.out.println(task.status.statusToSymbol() + task.name);
            }


            //exit
            if (userMessage.equals("bye")) {
                System.out.println("Bye! Nice serving you. Hope to see you again soon! :D");
                break;
            }
        }


    }
}
