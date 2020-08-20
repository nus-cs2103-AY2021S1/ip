import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String hello = "Hello! I'm Duke";
        String question = "What can I do for you?";
        System.out.println(hello + "\n" + question);
        String ans = sc.nextLine();
        ArrayList<Task> listOfStuff = new ArrayList<>();
        while (ans != null) {
            if (ans.equals("list")){
                System.out.println("Here are the tasks n your list:" + "\n");
                for(int i = 0; i <listOfStuff.size(); i++){
                    Integer listNum = i + 1;
                    System.out.println(listNum.toString() + "." + listOfStuff.get(i).toString());
                }
            }else if (ans.substring(0, 4).equals("done")) {
                listOfStuff.get(Integer.parseInt(ans.substring(5)) - 1).markAsDone();
            }else if (ans.substring(0, 4).equals("todo")){
                Todo t = new Todo(ans.substring(5));
                listOfStuff.add(t);
                System.out.println("Got it. I've added this task:" + "\n" + " " + t.toString() + "\n"
                    + "Now you have " + listOfStuff.size() + " tasks in the list");
            }else if (ans.substring(0, 8).equals("deadline")){
                Deadline d = new Deadline(ans.substring(9, ans.indexOf('/') - 1),
                        ans.substring(ans.indexOf('/') + 4));
                listOfStuff.add(d);
                System.out.println("Got it. I've added this task:" + "\n" + " " +d.toString() + "\n"
                        + "Now you have " + listOfStuff.size() + " tasks in the list");
            }else if (ans.substring(0, 5).equals("event")){
                Event e = new Event(ans.substring(6, ans.indexOf('/') - 1),
                        ans.substring(ans.indexOf('/') + 4));
                listOfStuff.add(e);
                System.out.println("Got it. I've added this task:" + "\n" + " " + e.toString() + "\n"
                        + "Now you have " + listOfStuff.size() + " tasks in the list");
            }
            ans = sc.nextLine();
            if (ans.equals("bye")) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
