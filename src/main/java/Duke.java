import java.util.Scanner;

public class Duke {



    public static void main(String[] args) {

        System.out.println("Hello from Bikini Bottom!");
        System.out.println("____________________________________________________________\n"
            + "Hello! I'm Spongebob\n"
            + "What can I do for you?\n"
            + "____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Task[] lstOfTasks = new Task[100];
        int counter = 0;

        while (!str.equals("bye")) {
            System.out.println("____________________________________________________________\n");

            if (str.equals("list")) {
                for (int i = 0; i < 100; i++) {
                    if (lstOfTasks[i] != null) {
                        System.out.println(i + 1 + ". " + lstOfTasks[i]);
                    } else {
                        break;
                    }
                }
            } else if (str.startsWith("done")){
                int temp = Integer.parseInt(str.substring(5));
                lstOfTasks[temp - 1].doTask();
            } else {
                lstOfTasks[counter] = new Task(str);
                System.out.println("added: " + str);
                counter++;
            }
            System.out.println("____________________________________________________________\n");

            str = sc.nextLine();

        }

        System.out.println("____________________________________________________________\n"
            + "Bye. Hope to see you again soon! Bahahahaha!\n"
            + "____________________________________________________________\n");



    }
}
