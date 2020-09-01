package main.java.emily.command;

import main.java.emily.task.Task;

import java.util.ArrayList;


/**
 * Deals with interactions with the user
 * Receives commands from the user
 */
public class Ui {

    Ui() {

    }

    enum Command {
        LIST, DELETE, TASK, DONE, BLANK, FIND
    }


    /**
     * Read a line of input from the user and modify the task list accordingly
     *
     * @param input command from the user
     * @param ls    to be modified
     * @throws DukeException when there is invalid command
     */
    public String readsLine(String input, TaskList ls) throws DukeException {
        String outputDialogue = "";

        Task current;
        Parser p = new Parser();

        Command instruct = checksValidInput(input, ls.getTaskArrayList().size());
        int index;
        int counter;

        switch (instruct) {
            case LIST:

                int num = 1;
                for (Task c : ls.getTaskArrayList()) {
                    String item = "    " + num + ". " + c;
                    num++;
                    outputDialogue += "\n" + item + "\n";
                }

                break;
            case FIND:
                String keyword = input.substring(5);

                ArrayList<Task> arr = ls.findSameKeyword(keyword);

                outputDialogue += "\n    Here are the matching tasks in your list";
                counter = 1;
                for (Task t : arr) {
                    outputDialogue += ("\n    " + counter + ". " + t);
                    counter++;
                }
                break;
            case DONE:
                index = Character.getNumericValue(input.charAt(5)) - 1;
                current = ls.getTaskArrayList().get(index);
                current.setFinished(true);
                outputDialogue += ("\n    Nice work, I have marked this task as done: "
                        + current);
                break;
            case TASK:
                //call parser to convert to task
                current = p.parse(input);
                ls.addTask(current);
                outputDialogue+= "\n    Got it! I have added this task: " + current;
                outputDialogue += ("\n    Now you have " + (ls.getTaskArrayList().size())
                        + " tasks in the list");
                break;
            case DELETE:
                index = Character.getNumericValue(input.charAt(5)) - 1;
                current = ls.getTaskArrayList().get(index);
                ls.deleteTask(index);

                outputDialogue += ("\n    I have deleted this task for you: " + current
                        + "\n    You have " + ls.getTaskArrayList().size() + " tasks in your list now");
            default:
                throw new DukeException("Command is not recognised");
        }
        return outputDialogue;
    }

    public Command checksValidInput(String input, int maxIndexSize) throws DukeException {
        Command c = Command.BLANK;
        String shortened = input.trim();


        if (input.isEmpty() || shortened.isEmpty()) {
            throw new DukeException("Empty Input");
        } else if (input.equals("list")) {
            c = Command.LIST;
        } else if (input.contains("delete")) {
            if (shortened.equals("delete")) {
                throw new DukeException("Empty Index");
            }

            int index = Character.getNumericValue(input.charAt(5)) - 1;
            if (index > maxIndexSize) {
                throw new DukeException("Index is invalid");
            }
            c = Command.DELETE;

        } else if (input.contains("done")) {
            if (shortened.equals("done")) {
                throw new DukeException("Index cannot be found");
            }
            int index = Character.getNumericValue(input.charAt(5)) - 1;
            if (index > maxIndexSize) {
                throw new DukeException("Index is invalid");
            }

            c = Command.DONE;
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            if (shortened.equals("todo") || shortened.equals("deadline") || shortened.equals("event")
                    || shortened.equals("deadline/by") || shortened.equals("event/at")) {
                throw new DukeException("The description cannot be empty");
            }

            c = Command.TASK;
        }
        if (input.contains("find")) {
            if (shortened.equals("find")) {
                throw new DukeException("Missing keyword");
            }

            c = Command.FIND;
        }
        return c;
    }


}
