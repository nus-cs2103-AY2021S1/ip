import java.util.Scanner;

public class Duke {

    public static void echoInput() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String inputData = sc.next();
            if (!inputData.equals("bye")) {
                System.out.println(" ____________________________________________________________\n " +
                        inputData +
                        "\n ____________________________________________________________");
            } else {
                String ending = " ____________________________________________________________\n " +
                        " Goodbye! See you again!\n" +
                        " ____________________________________________________________\n";

                System.out.println(ending);

                sc.close();
                break;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(" ____________________________________________________________\n " +
                logo +
                " Hello! I'm Duke\n" +
                " What can I do for you today?\n" +
                " ____________________________________________________________");

        echoInput();

    }
}
