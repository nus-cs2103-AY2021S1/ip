import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        Greet.main(null);
        Echo echo = new Echo();

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            echo.addCommand(command);
            String response = echo.replyUser();
            System.out.println(response);
            if (echo.toExit()) {
                break;
            }
        }

        sc.close();
    }
}
