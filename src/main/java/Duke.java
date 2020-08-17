import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lineWrapper("Hello! I'm Duke\n\tWhat can I do for you\n"));
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (true){
            input = sc.next();
            if (input.equals("bye")) break;
            System.out.println(lineWrapper(input));
        }
        System.out.println(lineWrapper("Bye. Hope to see you again soon!"));
    }

    /**
     * Takes text input from Duke and Wraps it in a Line Seperator
     * @param text
     * @return
     */
    private static String lineWrapper(String text){
        //placeholder until lines can be added
        String newLine = "\t"+System.getProperty("line.separator")+"_________________________________________________";
        return newLine+"\n\t"+text+newLine;
    }

}
