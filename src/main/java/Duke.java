import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("-------------------------------");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> todo = new ArrayList<Task>();
        printLine();
        System.out.println("What's new scooby doo?\n" + "How can I help you today?");
        printLine();
            while (sc.hasNext()) {
                String command = sc.next();
                switch(command) {
                    case "bye":
                        printLine();
                        System.out.println("See you later alligator ");
                        printLine();
                        System. exit(0);
                        break;
                    case "list":
                        printLine();
                        System.out.println("These are what you got: ");
                        for ( Task task:
                                todo) {
                            System.out.println( todo.indexOf(task)+1 + ". " + task.getStatusIcon() + " " + task.description);
                        }
                        if (todo.size() == 0) {
                            System.out.println("Your life is empty now bruh");
                        }
                        printLine();
                        break;
                    case "done":
                        int taskID = sc.nextInt() - 1;
                        Task task = todo.get(taskID);
                        task.markAsDone();
                        printLine();
                        System.out.println("Gratz, you finished this dawg :");
                        System.out.println(task.getStatusIcon()+ task.description);
                        printLine();
                        break;
                    default:
                        String fullTask = command + sc.nextLine();
                        todo.add(new Task(fullTask));
                        printLine();
                        System.out.println("okay you need to: " + fullTask);
                        printLine();
                }
            }
        }
    }
