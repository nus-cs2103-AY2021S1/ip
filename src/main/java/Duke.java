import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {

    private static String printDesign(String word) {
        return "**\n" + word + "\n**";
    }

    private static void order() {
        Scanner sc = new Scanner(System.in);
        String output;
        TaskWriter tw = new TaskWriter("mug.txt");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            try {
                String[] splitOrder = input.split(" ", 2);
                Command command = Validator.command(splitOrder[0]);

                switch (command) {
                    case LIST:
                        output = printDesign(tw.readTask());
                        break;
                    case BYE:
                        output = "** Bye. Hope to see you soon!! **";
                        break;
                    case DONE:
                        Validator.info(command, splitOrder.length, false);
                        int doneTaskId = Validator.index(splitOrder[1], splitOrder.length);
                        output = printDesign(tw.doneTask(doneTaskId));
                        break;
                    case DELETE:
                        Validator.info(command, splitOrder.length, false);
                        int deleteTaskId = Validator.index(splitOrder[1], splitOrder.length);
                        output = printDesign(tw.deleteTask(deleteTaskId));
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        Validator.info(command, splitOrder.length, false);
                        String info = splitOrder[1];
                        output = printDesign(tw.appendTask(command, info));
                        break;
                    default:
                        String errorCommand = "Hey!!! I'm sorry, but MUG don't know what that means :-()";
                        output = printDesign(errorCommand);
                        break;
                }
            } catch (DukeException | IOException ex){
                output = printDesign(ex.getMessage());
            }

            System.out.println(output);
            if(input.equals("bye")) {
                break;
            }

        }


    }

    public static void welcome() {
        String logo = " ___    ___        ______\n"
                + "|   \\  /   |_    _|  ____|\n"
                + "|    \\/    | |  | |  |  _\n"
                + "|          | |__| |  |_| |\n"
                + "|__/\\__/\\__|\\___,_|______|\n";

        String welcome = logo
                + "\n"
                + "** Hello! I'm MUG  **\n"
                + "** What can MUG do for you ?_? **";

        System.out.println(welcome);
    }

    public static void main(String[] args) {
        try {
            File workList = new File("mug.txt");
            workList.createNewFile();
            Duke.welcome();
            Duke.order();
        } catch (IOException e) {
            System.out.println("An error occurred!!");
            e.printStackTrace();
        }

    }
}
