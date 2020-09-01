package duke.tool;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.task.TaskList;

/**
 * Parses the user input into command.
 */
public class Parser {
    private static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static DateTimeFormatter getValidFormat() {
        return validFormat;
    }

    /**
     * Parses the user input into different commands.
     * @param storage class containing the data.
     * @param ui handles system output.
     * @param taskList a list of tasks.
     * @param command handles different commands.
     */
    public void parse(Storage storage, Ui ui, TaskList taskList, Command command) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String instruction = sc.nextLine();
            int len = instruction.length();
            try {
                if (instruction.equals("list")) {
                    command.list(taskList);
                } else if (instruction.equals("bye")) {
                    storage.writeData(taskList.getList());
                    break;
                } else if (len >= 5 && instruction.substring(0, 5).equals("done ")) {
                    int num = Integer.parseInt(instruction.substring(5));
                    command.markAsDone(num, taskList);
                } else if (len >= 7 && instruction.substring(0, 7).equals("delete ")) {
                    int num = Integer.parseInt(instruction.substring(7));
                    command.delete(num, taskList);
                } else if (len >= 4 && instruction.substring(0, 4).equals("todo")) {
                    command.handleTodo(instruction, taskList, ui);
                } else if (len >= 4 && instruction.substring(0, 4).equals("find")) {
                    command.find(taskList, instruction);
                } else if (len >= 8 && instruction.substring(0, 8).equals("deadline")) {
                    command.handleDeadline(instruction, taskList, ui);
                } else if (len >= 5 && instruction.substring(0, 5).equals("event")) {
                    command.handleEvent(instruction, taskList, ui);
                } else {
                    command.invalidInput();
                }
            } catch (NumberFormatException ex) {
                System.out.println(new DukeException("OOPS!!! I' m sorry, but you have to enter an integer."));
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
    }
}
