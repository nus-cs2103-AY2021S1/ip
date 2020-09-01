package ui;

import command.Command;
import mugexception.MugException;
import parser.Parser;
import tasks.TaskList;

/**
 * User interaction.
 */
public class Ui {

    /**
     * Prints welcome messages.
     */
    public void welcome() {
        String logo = " ___    ___        ______\n"
                + "|   \\  /   |_    _|  ____|\n"
                + "|    \\/    | |  | |  |  _\n"
                + "|          | |__| |  |_| |\n"
                + "|__/\\__/\\__|\\___,_|______|\n";

        String welcome = logo
                + "\n"
                + "** Hello! I'm Mug  **\n"
                + "** What can Mug do for you ?_? **";

        System.out.println(welcome);
    }

    /**
     * Read the input command.
     *
     * @param input User input.
     * @param tasks Task list.
     * @return Messages according to the user input.
     */
    public String readCommand(String input, TaskList tasks) {
        String output;

        try {
            String[] splitOrder = input.split(" ", 2);
            Command command = Parser.command(splitOrder[0]);

            switch (command) {
            case LIST:
                output = tasks.readList();
                break;
            case BYE:
                output = "** Bye. Hope to see you soon!! **";
                break;
            case DONE:
                Parser.input(command, splitOrder.length, false);
                Parser.info(command, splitOrder[1], false);
                int doneTaskId = Parser.index(splitOrder[1], splitOrder.length);
                output = tasks.taskDone(doneTaskId);
                break;
            case DELETE:
                Parser.input(command, splitOrder.length, false);
                Parser.info(command, splitOrder[1], false);
                int deleteTaskId = Parser.index(splitOrder[1], splitOrder.length);
                output = tasks.deleteTask(deleteTaskId);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                Parser.input(command, splitOrder.length, false);
                Parser.info(command, splitOrder[1], false);
                String info = splitOrder[1];
                output = tasks.addTask(command, info);
                break;
            case FIND:
                Parser.input(command, splitOrder.length, false);
                Parser.info(command, splitOrder[1], false);
                String keyword = splitOrder[1];
                output = tasks.searchTask(keyword);
                break;
            default:
                String errorCommand = "Hey!!! I'm sorry, but Mug don't know what that means :3";
                output = errorCommand;
                break;
            }
        } catch (MugException ex) {
            output = ex.getMessage();
        } catch (ArrayIndexOutOfBoundsException ex) {
            output = "There is Something wrong with your Storage.Storage";
        }

        return output;
    }
}
