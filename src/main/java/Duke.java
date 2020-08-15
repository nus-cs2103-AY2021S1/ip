import java.util.Scanner;

public class Duke {

    private static String printDesign(String word) {
        return "**\n" + word + "\n**";
    }

    private static void order() {
        Scanner sc = new Scanner(System.in);
        WorkList lst = new WorkList();
        String output;

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] checkCommand = input.split(" ");
            if(checkCommand[0].equals("done")) {
                output = printDesign(
                        lst.updateTaskStatus(
                                Integer.parseInt(checkCommand[checkCommand.length - 1])
                        )
                );
            } else if(input.equals("list")) {
                output = printDesign(lst.readWork());
            } else if(input.equals("bye")) {
                output = "** Bye. Hope to see you soon!! **";
            } else {
                Task newTask = new Task(input);
                lst.addWork(newTask);
                output = printDesign("added: " + input);
            }

            System.out.println(output);
            if(input.equals("bye")) {
                break;
            }
        }


    }

    private static void welcome() {
        String logo = " ___    ___        ______\n"
                + "|   \\  /   |_    _|  ____|\n"
                + "|    \\/    | |  | |  |  _ \n"
                + "|          | |__| |  |_| |\n"
                + "|__/\\__/\\__|\\___,_|______|\n";

        String welcome = logo
                + "\n"
                + "** Hello! I'm MUG  **\n"
                + "** What can I do for you ?_? **";

        System.out.println(welcome);
    }

    public static void main(String[] args) {
        Duke.welcome();
        Duke.order();
    }
}
