package main.java.emily.command;

import main.java.emily.task.Task;

/**
 * Deals with interactions with the user
 * Receives commands from the user
 */

public class Ui {

    Ui() {

    }


    /**
     * Read a line of input from the user and modify the task list accordingly
     * @param input command from the user
     * @param ls to be modified
     * @throws DukeException when there is invalid command
     */
    public void reading(String input, TaskList ls) throws DukeException {

        Task current;
        Parser p = new Parser();
        String divider = "    ---------------";

        if (input.isEmpty() || input.trim().isEmpty()) {
            throw new DukeException("input is empty");
        } else if (input.contains("list")) {
            System.out.println(divider);

            int num = 1;
            for (Task c : ls.retrieve()) {
                String item = "    " + num + ". " + c;
                num++;
                System.out.println(item);
            }

            System.out.println(divider);
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {

            if (input.trim().equals("todo") || input.trim().equals("deadline") || input.trim().equals("event")
                    || input.trim().equals("deadline/by") || input.trim().equals("event/at")) {

                throw new DukeException("The description of " + input + " cannot be empty");

            } else {

                System.out.println(divider);
                //call parser to convert to task
                current = p.process(input);
                ls.add(current);
                System.out.println("    Now you have " + (ls.retrieve().size()) + " tasks in the list" +
                        "\n" + divider);
            }

        } else if (input.contains("done") || input.contains("delete")) {
            if (input.contains("done")) {
                int index = Character.getNumericValue(input.charAt(5)) - 1;
                current = ls.retrieve().get(index);
                current.setFinished(true);
                System.out.println(divider + "\n    Nice work, I have marked this task as done: " + current
                        + "\n" + divider);

            } else {
                if (input.trim().equals("delete")) {
                    throw new DukeException("missing index");
                }

                int index = Character.getNumericValue(input.charAt(7)) - 1;
                if ((index + 1) > ls.retrieve().size()) {
                    throw new DukeException("Invalid index");
                }
                current = ls.retrieve().get(index);
                ls.delete(index);

                System.out.println(divider + "\n    I have deleted this task for you: " + current);
                System.out.println("    You have " + ls.retrieve().size() + " tasks in your list now" +
                        "\n" + divider);
            }

        } else {
            throw new DukeException("Command is not recognised");
        }
    }
}
