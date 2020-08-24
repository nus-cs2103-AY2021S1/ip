package Duke;

import java.util.ArrayList;

public class Parser {
    private TaskList lines;
    private boolean carryOn = true;

    public Parser(ArrayList<String> lines) {
        this.lines = new TaskList(lines);
    }

    public boolean shouldContinueDuke() {
        return carryOn;
    }

    public ArrayList<String> finalizedLines() {
        return lines.getList();
    }

    public void parse(String inputString) throws DukeException {
        String divider = "************************************************\n";
        if (inputString.indexOf("done ") == 0) {
            try {
                int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                if (inputString.length() <= 5) {
                    throw new DukeException("You did not specify which task you are done with!");
                } else if (lines.getNumberOfItems() < itemNumber || itemNumber <= 0) {
                    throw new DukeException("Hey, no such task exists!");
                } else {
                    String doneTask = lines.getTask(itemNumber - 1);
                    lines.updateTask(Ui.done(doneTask), itemNumber - 1);
                }
            } catch (NumberFormatException e) {
                System.out.println(divider + "Invalid input for done command!" + "\n" + divider);
            }
        } else if (inputString.equals("list")) {
            Ui.listTasks(lines.getList());
        } else if (inputString.equals("bye")) {
            Ui.bye();
            carryOn = false;
        } else if (inputString.indexOf("delete ") == 0) {
            try {
                int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                if (inputString.length() <= 7) {
                    throw new DukeException("You did not specify which task you are deleting!");
                } else if (lines.getNumberOfItems() < itemNumber || itemNumber <= 0) {
                    throw new DukeException("Hey, no such task exists!");
                } else {
                    String task = lines.getTask(itemNumber - 1);
                    lines.removeTask(itemNumber - 1);
                    Ui.deletedTask(task, lines.getNumberOfItems());
                }
            } catch (NumberFormatException e) {
                System.out.println(divider + "Invalid input for delete command!" + "\n" + divider);
            }
        } else {
            Task task = null;
            if (inputString.indexOf("todo") == 0) {
                if (inputString.length() == 4 || inputString.length() == 5  && inputString.indexOf(" ") == 4) {
                    throw new DukeException("Hey! Your Todo is empty >:(");
                } else if (inputString.indexOf(" ") != 4) {
                    throw new DukeException("What are you even saying?!");
                } else {
                    task = new Todo(inputString.substring(5));
                }
            } else if (inputString.indexOf("deadline") == 0) {
                if (!inputString.contains(" /by ") || inputString.substring(inputString.indexOf(" /by ")).length() == 5) {
                    throw new DukeException("Oi, when is this deadline due??");
                }
                int byIndex = inputString.indexOf(" /by ");
                if (inputString.indexOf(" ") != 8) {
                    throw new DukeException("What are you even saying?!");
                } else if (inputString.contains("deadline /by ")) {
                    throw new DukeException("You aren't setting anything for your deadline?!");
                } else {
                    if (Deadline.checkDateFormat(inputString.substring(byIndex + 5))) {
                        task = new Deadline(inputString.substring(9, byIndex),
                                inputString.substring(byIndex + 5));
                    }
                }
            } else if (inputString.indexOf("event") == 0) {
                if (!inputString.contains(" /at ")
                        || inputString.substring(inputString.indexOf(" /at ")).length() == 5) {
                    throw new DukeException("Oi, when is this event on??");
                }
                int atIndex = inputString.indexOf(" /at ");
                if (inputString.indexOf(" ") != 5) {
                    throw new DukeException("What are you even saying?!");
                } else if (inputString.contains("event /at ")) {
                    throw new DukeException("You aren't setting anything as your event?!");
                } else {
                    task = new Event(inputString.substring(6, atIndex), inputString.substring(atIndex + 4));
                }
            } else {
                throw new DukeException("What are you even saying?!");
            }
            if (task != null) {
                String newTask = task.toString();
                lines.addTask(newTask);
                Ui.addedTask(task, lines.getNumberOfItems());
            }
        }
    }
}
