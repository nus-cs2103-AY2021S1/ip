import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String divider = "************************************************\n";
        String intro = "Hello! I'm Duke\nWhat can i do for you?\n";
        System.out.println(divider + intro + divider);
        boolean carryOn = true;
        ArrayList<Task> taskArray = new ArrayList<>();
        int numberOfItems = 0;
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
                        Task task = taskArray.get(itemNumber - 1);
                        task.updateTask(1);
                        System.out.println(divider + "Nice! I have marked this task as done:");
                        System.out.println(task + "\n" + divider);
                    }
                } catch (DukeException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                } catch (NumberFormatException e) {
                    System.out.println(divider + "Invalid input for done command!" + "\n" + divider);
                }
            } else if (inputString.equals("list")) {
                System.out.println(divider);
                System.out.println("Here are the tasks in your list!");
                for (int i = 0; i < taskArray.size(); i++) {
                    int numbering = i + 1;
                    Task task = taskArray.get(i);
                    System.out.println(numbering + "." + task);
                }
                System.out.println(divider);
            } else if (inputString.equals("bye")) {
                System.out.println(divider + "Bye! See you next time!" + "\n" + divider);
                carryOn = false;
                input.close();
            } else if (inputString.indexOf("delete ") == 0) {
                try {
                    int itemNumber = Integer.parseInt(inputString.substring(inputString.indexOf(" ") + 1));
                    if (inputString.length() <= 7) {
                        throw new DukeException("You did not specify which task you are deleting!");
                    } else if (numberOfItems < itemNumber || itemNumber <= 0) {
                        throw new DukeException("Hey, no such task exists!");
                    } else {
                        Task task = taskArray.get(itemNumber - 1);
                        System.out.println(divider + "Noted, the task has been deleted");
                        System.out.println(task + "\n" + divider);
                        numberOfItems -= 1;
                        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
                        taskArray.remove(itemNumber - 1);
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
                        taskArray.add(task);
                        numberOfItems += 1;
                        System.out.println(divider + "Got it, I've added this task:");
                        System.out.println(" " + task);
                        System.out.println("Now you have " + numberOfItems + " tasks in the list.");
                        System.out.println(divider);
                    } else {
                        System.out.println(divider + "Sorry, the list is full!\n" + divider);
                    }
                }
            }
        }
    }
}
