import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws IOException {
        Storage storage = new Storage("src/main/data/", "src/main/data/data.txt");
        storage.processData();
        ArrayList<String> lines = storage.getData();
        int numberOfItems = lines.size();
        String divider = "************************************************\n";
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        Scanner input = new Scanner(System.in);
        System.out.println(divider + intro + divider);
        boolean carryOn = true;
        while(carryOn) {
            String inputString = input.nextLine();
            if (inputString.indexOf("done ") == 0) {
                try {
                    int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                    if (inputString.length() <= 5) {
                        throw new DukeException("You did not specify which task you are done with!");
                    } else if (numberOfItems < itemNumber || itemNumber <= 0) {
                        throw new DukeException("Hey, no such task exists!");
                    } else {
                        String doneTask = lines.get(itemNumber - 1);
                        String updatedLine = doneTask.substring(0, 6) + "\u2713" + doneTask.substring(7);
                        lines.set(itemNumber - 1, updatedLine);
                        System.out.println(divider + "Nice! I have marked this task as done:");
                        System.out.println(updatedLine + "\n" + divider);
                    }
                } catch (DukeException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                } catch (NumberFormatException e) {
                    System.out.println(divider + "Invalid input for done command!" + "\n" + divider);
                }
            } else if (inputString.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < lines.size(); i++) {
                    int numbering = i + 1;
                    String task = lines.get(i);
                    System.out.println(numbering + "." + task);
                }
                System.out.println(divider);
            } else if (inputString.equals("bye")) {
                System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
                carryOn = false;
                input.close();
                storage.saveData(lines);
            } else if (inputString.indexOf("delete ") == 0) {
                try {
                    int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                    if (inputString.length() <= 7) {
                        throw new DukeException("You did not specify which task you are deleting!");
                    } else if (numberOfItems < itemNumber || itemNumber <= 0) {
                        throw new DukeException("Hey, no such task exists!");
                    } else {
                        String task = lines.get(itemNumber - 1);
                        System.out.println(divider + "Noted, the task has been deleted");
                        System.out.println(task + "\n" + divider);
                        numberOfItems -= 1;
                        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
                        lines.remove(itemNumber - 1);
                    }
                } catch (DukeException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                } catch (NumberFormatException e) {
                    System.out.println(divider + "Invalid input for done command!" + "\n" + divider);
                }
            } else {
                Task task = null;
                if (inputString.indexOf("todo") == 0) {
                    try {
                        if (inputString.length() == 4 || inputString.length() == 5  && inputString.indexOf(" ") == 4) {
                            throw new DukeException("Hey! Your Todo is empty >:(");
                        } else if (inputString.indexOf(" ") != 4) {
                            throw new DukeException("What are you even saying?!");
                        } else {
                            task = new Todo(inputString.substring(5));
                        }
                    } catch (DukeException e) {
                        System.out.println(divider + e.getMessage() + "\n" + divider);
                    }
                } else if (inputString.indexOf("deadline") == 0) {
                    try {
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
                    } catch (DukeException e) {
                        System.out.println(divider + e.getMessage() + "\n" + divider);
                    }
                } else if (inputString.indexOf("event") == 0) {
                    try {
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
                    } catch (DukeException e) {
                        System.out.println(divider + e.getMessage() + "\n" + divider);
                    }
                } else {
                    try {
                        throw new DukeException("What are you even saying?!");
                    } catch (DukeException e) {
                        System.out.println(divider + e.getMessage() + "\n" + divider);
                    }
                }
                if (task != null) {
                    if (numberOfItems < 100) {
                        String newTask = task.toString();
                        numberOfItems += 1;
                        System.out.println(divider + "Got it, I've added this task:");
                        System.out.println(" " + task);
                        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
                        System.out.println(divider);
                        lines.add(newTask);
                    } else {
                        System.out.println(divider + "Sorry, the list is full!\n" + divider);
                    }
                }
            }
        }
    }
}
