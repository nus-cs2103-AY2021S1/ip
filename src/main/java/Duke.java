import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void listItems(ArrayList<Task> xs){
        int counter = 1;
        for (Task t: xs) {
            System.out.println("    " + counter + ": " + t.toString());
            counter += 1;
        }
    }


    public static void main(String[] args) {
        ArrayList<Task> listOfTask = new ArrayList<>();
        String breakline = "    ______________________________________________________";
        String logo = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Welcome to \n" + logo + "\n    Your personal assistant :)");
        System.out.println(breakline);
        System.out.println(breakline);

        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String userCommand = sc.nextLine();
            String[] userWord = userCommand.split(" ");

            if (userCommand.equals("bye")) {
                System.out.println(breakline);
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println(breakline);
                break;
            }
            switch (userWord[0]) {
                case "list":
                    System.out.println(breakline);
                    listItems(listOfTask);
                    System.out.println(breakline);
                    break;

                case "done":
                    int index = Integer.parseInt(userWord[1]) - 1;
                    if (index >= 0 && index < listOfTask.size()) {
                        Task temp = listOfTask.get(index);
                        temp.completed();
                        System.out.println(breakline);
                        System.out.println("    Nice! I've marked this task as done:");
                        System.out.println("    " + temp.toString());
                        System.out.println(breakline);
                    } else {
                        System.out.println(breakline);
                        System.out.println("    Invalid index entry");
                        System.out.println(breakline);
                    }
                    break;

                default:
                    Task newT = new Task(userCommand);
                    listOfTask.add(newT);
                    System.out.println(breakline);
                    System.out.println("    added: " + userCommand);
                    System.out.println(breakline);
                    break;
            }
        }
        sc.close();
    }
}
