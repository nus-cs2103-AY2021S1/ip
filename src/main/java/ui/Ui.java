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
            switch (command) {
            case LIST:
                assert(command == Command.LIST);
                output = tasks.readList();
                break;
            case BYE:
                assert(command == Command.BYE);
                output = "** Bye. Hope to see you soon!! **";
                break;
            case DONE:
                assert(command == Command.DONE);
                Parser.input(command, splitOrder.length, false);
                assert(splitOrder.length > 1);
                Parser.info(command, splitOrder[1], false);
                int doneTaskId = Parser.index(splitOrder[1], splitOrder.length);
                assert(doneTaskId > 0);
                output = tasks.taskDone(doneTaskId);
                break;
            case DELETE:
                assert( command == Command.DELETE);
                Parser.input(command, splitOrder.length, false);
                assert(splitOrder.length > 1);
                Parser.info(command, splitOrder[1], false);
                int deleteTaskId = Parser.index(splitOrder[1], splitOrder.length);
                assert(deleteTaskId > 0);
                output = tasks.deleteTask(deleteTaskId);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                boolean isTodo = command == Command.TODO;
                boolean isDeadline = command == Command.DEADLINE;
                boolean isEvent = command == Command.EVENT;
                assert( isTodo || isDeadline || isEvent);
                Parser.input(command, splitOrder.length, false);
                assert(splitOrder.length > 1);
                Parser.info(command, splitOrder[1], false);
                String info = splitOrder[1];
                output = tasks.addTask(command, info);
                break;
            case FIND:
                assert(command == Command.FIND);
                Parser.input(command, splitOrder.length, false);
                assert(splitOrder.length > 1);
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
