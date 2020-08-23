public class Ui {

    public void welcome() {
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

    private static String printDesign(String word) {
        return "**\n" + word + "\n**";
    }

    public String readCommand(String input, TaskList tasks) {
        String output;

        try {
            String[] splitOrder = input.split(" ", 2);
            Command command = Parser.command(splitOrder[0]);

            switch (command) {
                case LIST:
                    output = printDesign(tasks.readList());
                    break;
                case BYE:
                    output = "** Bye. Hope to see you soon!! **";
                    break;
                case DONE:
                    Parser.input(command, splitOrder.length, false);
                    Parser.info(command, splitOrder[1], false);
                    int doneTaskId = Parser.index(splitOrder[1], splitOrder.length);
                    output = printDesign(tasks.taskDone(doneTaskId));
                    break;
                case DELETE:
                    Parser.input(command, splitOrder.length, false);
                    Parser.info(command, splitOrder[1], false);
                    int deleteTaskId = Parser.index(splitOrder[1], splitOrder.length);
                    output = printDesign(tasks.deleteTask(deleteTaskId));
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Parser.input(command, splitOrder.length, false);
                    Parser.info(command, splitOrder[1], false);
                    String info = splitOrder[1];
                    output = printDesign(tasks.addTask(command, info));
                    break;
                default:
                    String errorCommand = "Hey!!! I'm sorry, but MUG don't know what that means :-()";
                    output = printDesign(errorCommand);
                    break;
            }
        } catch (DukeException ex){
            output = printDesign(ex.getMessage());
        } catch (ArrayIndexOutOfBoundsException ex) {
            output = printDesign("There is Something wrong with your Storage");
        }

        return output;
    }
}
