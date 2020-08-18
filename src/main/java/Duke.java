import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Scanner input;
    private ArrayList<Task> list;

    Duke(Scanner input, ArrayList<Task> list) {
        this.input = input;
        this.list = list;
    }

    void commandHandler() {
        while (input.hasNextLine()) {
            String command = input.nextLine();

            System.out.println("___________________________________________________");
            try {
                if (command.equals("bye")) {
                    System.out.println("That's it? That's a shame. Well, see you later then.");
                    System.out.println("___________________________________________________");
                    this.input.close();
                    break;
                } else if (command.equals("list")) {
                    this.printList();
                } else if (command.split(" ")[0].equals("done")) {
                    if (command.split(" ").length == 1) {
                        throw new UnknownTaskException("No task number entered");
                    }
                    int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                    this.taskDone(taskNo);
                } else if (command.split(" ")[0].equals("todo")) {
                    if (command.length() <= 5) {
                        throw new EmptyDescriptionException("No Description entered");
                    }
                    String description = command.substring(5);
                    this.addToList(new ToDo(description));
                } else if (command.split(" ")[0].equals("deadline")) {
                    if (command.length() <= 9) {
                        throw new EmptyDescriptionException("No Description entered");
                    }
                    String[] splitArr = command.split("/");
                    if (splitArr.length == 1) {
                        throw new UnknownTimeException("No by time added");
                    }
                    String description = splitArr[0].substring(8);
                    if (splitArr[1].length() <= 3) {
                        throw new EmptyTimeException("No time entered");
                    }
                    String by = splitArr[1].substring(3);
                    this.addToList(new Deadline(description, by));
                } else if (command.split(" ")[0].equals("event")) {
                    if (command.length() <= 6) {
                        throw new EmptyDescriptionException("No Description entered");
                    }
                    String[] splitArr = command.split("/");
                    if (splitArr.length == 1) {
                        throw new UnknownTimeException("No by time added");
                    }
                    String description = splitArr[0].substring(5);
                    if (splitArr[1].length() <= 3) {
                        throw new EmptyTimeException("No time entered");
                    }
                    String at = splitArr[1].substring(3);
                    this.addToList(new Event(description, at));
                } else if (command.split(" ")[0].equals("delete")) {
                    if (command.split(" ").length == 1) {
                        throw new UnknownTaskException("No task number entered");
                    }
                    int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                    this.removeFromList(taskNo);
                } else {
                    throw new UnknownCommandException("Unknown command entered");
                }
            } catch (EmptyDescriptionException empty) {
                System.out.println("Mate, you've gotta let me know what you're gonna be doing.");
            } catch (UnknownCommandException com) {
                System.out.println("Um, are you sure that's not gibberish?");
            } catch (UnknownTimeException by) {
                System.out.println("You've gotta let me know the time.");
            } catch (EmptyTimeException at) {
                System.out.println("There has to be a time, surely. Don't leave it blank!");
            } catch (UnknownTaskException ex) {
                System.out.println("C'mon, I don't live in your head, you gotta tell me the task number!");
            } finally {
                System.out.println("___________________________________________________");
            }

        }
    }


    void addToList(Task task) {
        System.out.println("Alright matey, I've added this task:");
        this.list.add(task);
        System.out.println(task);
        System.out.println("Looks like you have " + this.list.size() + " tasks in total.");
    }

    void taskDone(int taskNo) {
        Task toBeDone = this.list.get(taskNo);
        toBeDone.markAsDone();
        this.list.set(taskNo, toBeDone);
        System.out.println("Good Job, this task is now done:");
        System.out.println(toBeDone);
    }

    void removeFromList(int taskNo) {
        Task removedTask = this.list.remove(taskNo);
        System.out.println("Well, if you insist. I've removed:");
        System.out.println(removedTask);
    }

    void printList() {
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println(i + ". " + this.list.get(i - 1));
        }
    }

    public static void main(String[] args) {
        System.out.println("___________________________________________________");
        System.out.println("Yo what's up! The name's Juke");
        System.out.println("What do you need?");
        System.out.println("___________________________________________________");

        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        Duke duke = new Duke(input, list);

        duke.commandHandler();
    }
}
