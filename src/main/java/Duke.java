import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "I'm DukeForQ, your chatbot! You can enter everything you want to enter. If you want to exit, enter 'bye'!");

        ArrayList<Task> tasks = new ArrayList<>();
        String s;
        while (true) {
            s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("Bye, hope to see you again!");
                sc.close();
                System.exit(0);
            } 
            
            if (s.startsWith("done")) {
                try {
                    int i = Integer.parseInt(s.split(" ")[1]);
                    tasks.get(i - 1).markAsDone();
                    System.out.println(tasks.get(i - 1).getTaskInfo());
                    continue;
                }

                catch(IndexOutOfBoundsException e) {
                    System.out.println("Index is out of the list bound.");
                    continue;
                }
            }

            if (s.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for(int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + "." + tasks.get(i-1).getTaskInfo());
                }
            }
            
            else {
                tasks.add(new Task(s));
            }
            
        }
    }
}
