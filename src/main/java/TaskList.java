import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskList {
    private Storage storage;
    private ArrayList<Task> database;

    TaskList() {
        this.storage = new Storage();
        try {
          this.database = this.storage.loadFromDatabase();
        } catch (DukeException e) {

        }
    }

    public void saveStateToDatabase() {
        this.storage.saveToDatabase(this.database);
    }

    private void add(Task task) {
        database.add(task);
        System.out.println("Looking good! I have added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.database.size() + " tasks in your list");
    }

    /**
     * Uses the given keyword to search for relevant tasks.
     * Relevant tasks will then be printed out.
     */
    public void find(Scanner sc) {
        String keyword = sc.next();
        int tasksFound = 0;

        for (int i = 0; i < this.database.size(); i++) {
            String currentTask = database.get(i).toString();
            if (currentTask.contains(keyword)) {
                tasksFound += 1;
                System.out.println(database.get(i));
            }
        }

        if (tasksFound == 0) {
            System.out.println("No such tasks matches the keyword");
        }
    }

    public ArrayList<Task> getDatabase() {
      return this.database;
    }

    /**
     * Prints all the tasks in the database.
     */
    public void list() {
        if (this.database.size() == 0) {
            System.out.println("There are no tasks in the database");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.database.size(); i++) {
                System.out.println((i + 1) + ". " + this.database.get(i));
            }
        }
    }

    public void onDeadlineCommand(Scanner sc) {
        String toParse = sc.nextLine();

        if (!toParse.equals("")) {
            try {
                String[] parsed = toParse.split("/by ");
                if (parsed.length == 1) {
                    throw new Exception("Please input the deadline in a valid format");
                } else {
                    String[] dateTime = parsed[1].trim().split(" ");
                    Deadline deadline = new Deadline(parsed[0].trim(), dateTime[0], dateTime[1]);
                    add(deadline);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Task description and deadline cannot be empty");
        }
    }

    public void onDeleteCommand(Scanner sc) {
        if (this.database.size() == 0) {
            System.out.println("There are no tasks in the database to delete");
        } else {
            try {
                int index = sc.nextInt();

                if (index > this.database.size()) {
                    throw new Exception("Index cannot be greater than size of database");
                }

                Task removedTask = this.database.remove(index - 1);
                System.out.println("Noted! I have removed this task:");
                System.out.println(removedTask);
                System.out.println("Now you have " + this.database.size() + " tasks in your list");
            } catch (InputMismatchException e) {
                System.out.println("Index must be a number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        sc.nextLine();
    }

    public void onDoneCommand(Scanner sc) {
        if (this.database.size() != 0) {
            try {
                int index = sc.nextInt();

                if (index > this.database.size()) {
                    throw new Exception("Index cannot be greater than size of database");
                }

                this.database.get(index - 1).markAsDone();
                System.out.println("Nice! I have marked this task as done!");
            } catch (InputMismatchException e) {
                System.out.println("Index must be a number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("There are no tasks in the database to mark as done");
        }

        sc.nextLine();
    }

    public void onEventCommand(Scanner sc) {
        String toParse = sc.nextLine();

        if (!toParse.equals("")) {
            try {
                String[] parsed = toParse.split("/at ");
                if (parsed.length == 1) {
                    throw new Exception("Please input the start and end timing in a valid format");
                } else {
                    String[] dateTime = parsed[1].trim().split(" ");
                    Event event = new Event(parsed[0].trim(), dateTime[0], dateTime[1]);
                    add(event);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Task description, start time and end time cannot be empty");
        }
    }

    public void onToDoCommand(Scanner sc) {
        String description = sc.nextLine();

        if (description.equals("")) {
            try {
                throw new DukeException("The description of a todo cannot be empty");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        } else {
            ToDo toDo = new ToDo(description.trim());
            this.add(toDo);
        }
    }

    public void noSuchCommand() {
        try {
            throw new DukeException("Oops! I am sorry but I don't understand what that means");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

}
