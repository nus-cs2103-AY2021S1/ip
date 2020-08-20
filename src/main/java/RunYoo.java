import java.util.ArrayList;
import java.util.Scanner;

public class RunYoo {

    public static void run(Scanner sc) throws Exception {

        ArrayList<Task> al = new ArrayList<>();
        String input = sc.nextLine();

        while(!input.equals("bye")) {

            String temp[] = input.split(" ", 2);

            if (input.equals("list")) {
                displayList(al);

            } else {
                try {
                    if (temp[0].equals("done")) {
                        markAsDone(al, temp);

                    } else if (temp[0].equals("todo")) {
                        addTodo(al, temp);

                    } else if (temp[0].equals("deadline")) {
                        addDeadline(al, temp);

                    } else if (temp[0].equals("event")) {
                        addEvent(al, temp);

                    } else {
                        throw new YooException("Sorry, I didn't get that (っ*´□`)っ");
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("You're missing a task description (っ*´□`)っ");
                }
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

    public static void markAsDone(ArrayList<Task> al, String[] temp) throws YooException {
        int index = Integer.parseInt(temp[1]);
        if (index > al.size()) {
            throw new YooException("No such task (>_<)");
        } else {
            System.out.println("Good job completing the task! ╭( ･ㅂ･)و");
            Task t = al.get(index - 1);
            t.markAsDone();
            System.out.println(t);
        }
    }

    public static void addTodo(ArrayList<Task> al, String[] temp) {
        Todo td = new Todo(temp[1]);
        System.out.println("I've added the following task! \n" + td);
        al.add(td);
        System.out.println("Now you have " + al.size() + " tasks in the list (´・ω・｀)");
    }

    private static void addDeadline(ArrayList<Task> al, String[] temp) {
        try {
            String[] a = temp[1].split("/by ", 2);
            Deadline dl = new Deadline(a[0], a[1]);
            System.out.println("I've added the following task! \n" + dl);
            al.add(dl);
            System.out.println("Now you have " + al.size() + " tasks in the list (´・ω・｀)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, your deadline time is missing (っ*´□`)っ");
        }
    }

    private static void addEvent(ArrayList<Task> al, String[] temp) {
        try {
            String[] a = temp[1].split("/at ", 2);
            Event e = new Event(a[0], a[1]);
            System.out.println("I've added the following task! \n" + e);
            al.add(e);
            System.out.println("Now you have " + al.size() + " tasks in the list (´・ω・｀)");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry, your event time is missing (っ*´□`)っ");
        }
    }
}