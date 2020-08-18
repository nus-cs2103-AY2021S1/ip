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
            String[] userWord = userCommand.split(" ", 2);

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

                case "todo":
                    ToDo newT = new ToDo(userWord[1]);
                    listOfTask.add(newT);
                    System.out.println(breakline);
                    System.out.println("    Got it. I've added this task: \n     " + newT.toString());
                    System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
                    System.out.println(breakline);
                    break;

                case "deadline":
                    String[] content = userWord[1].split("/by", 2);
                    Deadline newD = new Deadline(content[0], content[1]);
                    listOfTask.add(newD);
                    System.out.println(breakline);
                    System.out.println("    Got it. I've added this task: \n     " + newD.toString());
                    System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
                    System.out.println(breakline);
                    break;

                case "event":
                    String[] content2 = userWord[1].split("/at", 2);
                    Event newE = new Event(content2[0], content2[1]);
                    listOfTask.add(newE);
                    System.out.println(breakline);
                    System.out.println("    Got it. I've added this task: \n     " + newE.toString());
                    System.out.println("    Now you have " + listOfTask.size() + " tasks in the list.");
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
                    System.out.println(breakline);
                    System.out.println("    Sorry! I'm not really sure what to do with this command yet ☹");
                    System.out.println(breakline);
                    break;
            }
        }
        sc.close();
    }
}
