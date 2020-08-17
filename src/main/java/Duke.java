import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";*/
        String doge = "░░░░░░░░░▄░░░░░░░░░░░░░░▄░░░░\n" +
                "░░░░░░░░▌▒█░░░░░░░░░░░▄▀▒▌░░░\n" +
                "░░░░░░░░▌▒▒█░░░░░░░░▄▀▒▒▒▐░░░\n" +
                "░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐░░░\n" +
                "░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐░░░\n" +
                "░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌░░░ \n" +
                "░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒▌░░\n" +
                "░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐░░\n" +
                "░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄▌░\n" +
                "░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▌░\n" +
                "▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒▐░\n" +
                "▐▒▒▐▀▐▀▒░▄▄▒▄▒▒▒▒▒▒░▒░▒░▒▒▒▒▌\n" +
                "▐▒▒▒▀▀▄▄▒▒▒▄▒▒▒▒▒▒▒▒░▒░▒░▒▒▐░\n" +
                "░▌▒▒▒▒▒▒▀▀▀▒▒▒▒▒▒░▒░▒░▒░▒▒▒▌░\n" +
                "░▐▒▒▒▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▒▄▒▒▐░░\n" +
                "░░▀▄▒▒▒▒▒▒▒▒▒▒▒░▒░▒░▒▄▒▒▒▒▌░░\n" +
                "░░░░▀▄▒▒▒▒▒▒▒▒▒▒▄▄▄▀▒▒▒▒▄▀░░░\n" +
                "░░░░░░▀▄▄▄▄▄▄▀▀▀▒▒▒▒▒▄▄▀░░░░░\n" +
                "░░░░░░░░░▒▒▒▒▒▒▒▒▒▒▀▀░░░░░░░░";
        System.out.println("--------------------------------------");
        System.out.println("Hello from doge\n" + doge);
        System.out.println("What can this good boi do for you?");
        System.out.println("--------------------------------------");

        TaskManager taskManager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while(sc.hasNextLine()) {
            String next = sc.nextLine();

            if(next.equals("bye") ) {
                System.out.println("Woof!");
                break;
            }

            else if(next.equals("list")) {
                taskManager.displayList();
            }

            else {
                System.out.println("--------------------------------------");
                taskManager.addToList(next);
                taskManager.taskPrint(next);
                System.out.println("--------------------------------------");
            }
        }

    }
}
