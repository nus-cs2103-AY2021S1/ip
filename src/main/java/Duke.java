import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        Boolean botRunning = true;
        ArrayList<String> taskList = new ArrayList<String>();
        String logo =
                "___.           __                         \n"
                        + "\\_ |__ _____ _/  |_  _____ _____    ____  \n"
                        + " | __ \\__  \\     __\\/     \\__  \\  /     \\\n"
                        + " | \\_\\ \\/ __ \\|  | |  Y Y  \\/ __ \\|   |  \\ \n"
                        + " |___  (____  /__| |__|_|  (____  /___|  /\n"
                        + "     \\/     \\/           \\/     \\/     \\/\n";
        System.out.println("Hello from\n" + logo);

        while (botRunning) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                botRunning = false;
            } else if (input.equals("list")) {
                for (String task : taskList) {
                    System.out.println(task);
                }
            } else {
                int listSize = taskList.size() + 1;
                taskList.add(listSize + ". " + input);
                System.out.println("Task added: " + input);
            }
        }
        System.out.println("SAYONARA HUMAN ಥ﹏ಥ");
    }
}
