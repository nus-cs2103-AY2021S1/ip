import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yoo ( ﾟ▽ﾟ)/ \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> al = new ArrayList<>();

        String input = sc.nextLine();
        while(!input.equals("bye")) {

            if(input.equals("list")) {
                displayList(al);
            } else if (input.substring(0, 4).equals("done")) {
                int index = Integer.parseInt(input.substring(5));
                markAsDone(al.get(index - 1));

            } else {
                Task t = new Task(input);
                System.out.println("I've added [" + t + "] !");
                al.add(t);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! Come back soon ( ･ω･)ﾉ");
        sc.close();
    }

    public static void displayList(ArrayList<Task> al) {
        System.out.println("Here's your list!");
        for(int i = 1; i <= al.size(); i ++) {
            System.out.println(i + ". ["
                    + al.get(i - 1).getStatusIcon() + "] "
                    + al.get(i - 1));
        }
    }

    public static void markAsDone(Task t) {
        System.out.println("Good job completing the task! ╭( ･ㅂ･)و");
        t.markAsDone();
        System.out.println("[" + t.getStatusIcon() + "] " + t);
    }
}