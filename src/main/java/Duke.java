import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hello. I am Claude! What may I do for you today?";
        String goodbye = "Goodbye! Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        System.out.println(welcome);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println(printList(taskList));
            } else {
                taskList.add(command);
                System.out.println("\n Task added: " + command);
            }
        }
        System.out.println(goodbye);
    }

    private static String printList(ArrayList<String> al) {
        if (al.isEmpty()) {
            return "List is empty!";
        } else {
            String s = "";
            for (int i = 0; i < al.size(); i++) {
                s = s + String.valueOf(i+1) + ". " + al.get(i) + "\n";
            }
            return s;
        }
    }
}
