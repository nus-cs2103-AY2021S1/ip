import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String border = "----------------------------------------------------------------------------\n";
        String greeting = "Sorry :( Duke is getting some upgrades at the moment.\n"
                + "This is Tron, temporarily standing in for Duke, how may I assist you ?\n";
        String farewell = "Adios, pleasure to serve you!\n";
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(border + greeting + border);

        Scanner sc = new Scanner(System.in); //scans for input
        DisplayList displayList = new DisplayList();
        String input = sc.next();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(border + displayList.toString() + border);
            } else if (input.equals("done")) {
                int index = Integer.valueOf(sc.next()) - 1;
                displayList.completeTask(index);
                System.out.println(border
                        + "Making great progress master.\n"
                        + displayList.getTask(index) + "\n"
                        + border
                );
            } else {
                String task = input + sc.nextLine();
                displayList.addTask(task);
                System.out.println(border + "Added: " + task + "\n" + border);
            }
            input = sc.next();
        }

        System.out.println(border + farewell + border);


    }


}
