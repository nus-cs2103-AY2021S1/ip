import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UI {

    public static void startUpMessage() {
        System.out.println("Hi! I'm Duke" + "\n" + "What can I do for you?");
    }

    public static void readList() {
        List<Task> tasks = TaskList.getList();
        if (tasks.size() == 0) {
            System.out.println("You currently have no tasks");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ". " + tasks.get(i));
            }
        }
    }

    public static void readFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        //Prints out the tasks
        if (scanner.hasNext()) {
            //If there's saved tasks, print and add to list
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                System.out.println(nextLine);
            }
            System.out.println("That's the end of your current tasks!" + "\n");
        } else {
            //No saved tasks
            System.out.println("You currently have no tasks" + "\n");
        }
    }

    public static void showError(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void processInput() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

            while (!Parser.isTerminateCommand(input)) {
                try {
                    int parseResult = Parser.parse(input);
                    if (parseResult == 1) {
                        UI.readList();
                    } else if (parseResult == 2) {
                        String[] parts = input.split(" ");
                        int index = Integer.parseInt(parts[1]) - 1;

                        TaskList.markDone(index);

                        UI.read("Nice! I've marked this task as done:" + "\n" + "  " + TaskList.get(index).toString());
                    } else if (parseResult == 3) {
                        String[] parts = input.split(" ");
                        int index = Integer.parseInt(parts[1]) - 1;

                        UI.read("Noted. I've removed this task:" + "\n" + "  " + TaskList.get(index)
                                + "\n" + "Now you have " + (TaskList.size() - 1) + " tasks in the list.");
                        TaskList.delete(index);
                    } else {
                        Task newTask = Parser.getTask(input);
                        TaskList.add(newTask);
                        UI.read("Got it. I've added this task:" + "\n" + "  " + TaskList.get(TaskList.size() - 1)
                                + "\n" + "Now you have " + TaskList.size() + " tasks in the list.");
                    }
                    input = scanner.nextLine();
                } catch (InvalidCommandException e) {
                    System.out.println(e + "\n" + "Please enter a valid command");
                    input = scanner.nextLine();
                } catch (InvalidInputException e) {
                    System.out.println(e + "\n" + "Please enter a valid input");
                    input = scanner.nextLine();
                }
            }
        System.out.println("Bye! See you again!");
        Storage.save(TaskList.getList());
    }

    public static void read(String input) {
        System.out.println(input);
    }

}
