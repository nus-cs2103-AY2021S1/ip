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
            // assert
            assert(command == Command.LIST
                    || command == Command.BYE
                    || command == Command.DONE
                    || command == Command.DELETE
                    || command == Command.TODO
                    || command == Command.DEADLINE
                    || command == Command.EVENT
                    || command == Command.FIND);
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
                // assert
                assert(doneTaskId > 0);
                output = tasks.taskDone(doneTaskId);
                break;
            case DELETE:
                Parser.input(command, splitOrder.length, false);
                Parser.info(command, splitOrder[1], false);
                int deleteTaskId = Parser.index(splitOrder[1], splitOrder.length);
                // assert
                assert(deleteTaskId > 0);
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
                output = "Hey!!! I'm sorry, but Mug don't know what that means :3";
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
