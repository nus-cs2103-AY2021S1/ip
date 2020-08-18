import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        boolean isDone = false;

        System.out.println("Hello! I'm Eggy\n" + "How may I help you?");

        while (!isDone) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                isDone = true;
                System.out.println("Bye. See you soon!");
            } else if (command.equals("list")){
                int i = 1;
                for (String str : taskList) {
                    System.out.println(i + ". " + str);
                    i++;
                }
            } else {
                taskList.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}