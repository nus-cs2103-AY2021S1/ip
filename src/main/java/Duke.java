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

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            Echo echo = new Echo(command);
            String response = echo.getResponse();
            System.out.println(response);
            if (echo.toExit()) {
                break;
            }
        }

        sc.close();
    }
}
