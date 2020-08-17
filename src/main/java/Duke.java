import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Initializing\n" + logo);
        System.out.println("Yo what's up man, it's ya boi DUKE \n" + "What can I do for you today, Sir?");

        ArrayList<Task> arraylst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        Processor processor = new Processor();

        while (sc.hasNext()) {
            String cmd = sc.nextLine();
            if (!cmd.equals("bye")) {
                try {
                    String[] stringarr = cmd.split(" ");
                    if (stringarr[0].equals("list")) {
                        processor.processorList(arraylst);
                    } else if (stringarr[0].equals("done")) {
                        int index = Integer.parseInt(stringarr[1]);
                        processor.processorDone(arraylst, index);
                    } else {
                        arraylst = processor.processorAdd(cmd, arraylst);
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("_________________________________________\n" + "Bye. Hope to see you again soon!" + "\n" + "_________________________________________");
                break;
            }
        }
    }
}
