import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Hello from\n" +
                        " ____        _        \n" +
                        "|  _ \\ _   _| | _____ \n" +
                        "| | | | | | | |/ / _ \\\n" +
                        "| |_| | |_| |   <  __/\n" +
                        "|____/ \\__,_|_|\\_\\___|\n" +
                "____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________\n");
        boolean stop = false;
        Userinput userinput = new Userinput();
        while(sc.hasNextLine()){
            try {
                String input = sc.nextLine();
                String output = userinput.getDukeResponse(input);
                System.out.println(output);
                stop = userinput.getTerminate();
                if (stop) {
                    sc.close();
                    break;
                }
            } catch (EmptyInputException ex) {
                System.out.println(ex);
            } catch (NoResponseException excep) {
                System.out.println(excep);
            }
        }
    }
}
