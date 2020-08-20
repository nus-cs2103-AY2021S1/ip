import java.util.ArrayList;
import java.util.Scanner;

public class RunYoo {

    public static void run(Scanner sc) {

        ArrayList<Task> al = new ArrayList<>();

        String input = sc.nextLine();

        while(!input.equals("bye")) {

            String temp[] = input.split(" ", 2);

            if (input.equals("list")) {
                displayList(al);

            } else if (temp[0].equals("done")) {
                int index = Integer.parseInt(temp[1]);
                markAsDone(al.get(index - 1));

            } else if (temp[0].equals("todo")) {
                Todo td = new Todo(temp[1]);
                System.out.println("I've added the following task! \n" + td);
                al.add(td);
                System.out.println("Now you have " + al.size() + " tasks in the list (´・ω・｀)");

            } else if (temp[0].equals("deadline")) {
                String[] a = temp[1].split("/by ", 2);
                Deadline dl = new Deadline(a[0], a[1]);
                System.out.println("I've added the following task! \n" + dl);
                al.add(dl);
                System.out.println("Now you have " + al.size() + " tasks in the list (´・ω・｀)");

            } else if (temp[0].equals("event")) {
                String[] a = temp[1].split("/at ", 2);
                Event e = new Event(a[0], a[1]);
                System.out.println("I've added the following task! \n" + e);
                al.add(e);
                System.out.println("Now you have " + al.size() + " tasks in the list (´・ω・｀)");

            } else {
                Task t = new Task(input);
                System.out.println("I've added [" + input + "] !");
                al.add(t);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Come back soon ( ･ω･)ﾉ");
    }

    public static void displayList(ArrayList<Task> al) {
        System.out.println("Here's your list!");
        for(int i = 1; i <= al.size(); i ++) {
            System.out.println(i + ". "
                    + al.get(i - 1));
        }
    }

    public static void markAsDone(Task t) {
        System.out.println("Good job completing the task! ╭( ･ㅂ･)و");
        t.markAsDone();
        System.out.println(t);
    }
}
