
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.*;

public class Duke {
    private final static String EXIT_COMMAND = "bye";
    private final static String MARK_DONE_COMMAND = "done";
    private final static String DISPLAY_TASKS_COMMAND = "list";
    private final static String TODO_COMMAND = "todo";
    private final static String DEADLINE_COMMAND = "deadline";
    private final static String EVENT_COMMAND = "event";
    private final static String DELETE_COMMAND = "delete";
    private final String DATA_DIRECTORY = "data";
    private final String SAVED_FILE_PATH = "data/duke.txt";
    private List<Task> tasks;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        duke.start();
        duke.bye();
    }

    private Duke() {
        tasks = new ArrayList<>();
    }

    private void start() {
        this.loadSavedFile();
        this.handle();
    }

    private void handle() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(EXIT_COMMAND)){
            if (input.equals(DISPLAY_TASKS_COMMAND)) {
                this.displayTasks();
            } else {
                String[] inputWords = input.split(" ", 2);
                String command = inputWords[0];
                String argument;
                switch (command) {
                    case MARK_DONE_COMMAND:
                        try {
                            argument = inputWords[1];
                            this.markDone(Integer.parseInt(argument));
                        } catch (IndexOutOfBoundsException e) {
                            sayException(new EmptyDoneException());
                        }
                        break;
                    case TODO_COMMAND:
                        try {
                            argument = inputWords[1];
                            this.addTodo(argument);
                        } catch (IndexOutOfBoundsException e) {
                            sayException(new EmptyTodoException());
                        }
                        break;
                    case DEADLINE_COMMAND:
                        try {
                            argument = inputWords[1];
                            this.addDeadline(argument);
                        } catch (IndexOutOfBoundsException e) {
                            sayException(new EmptyDeadlineException());
                        }
                        break;
                    case EVENT_COMMAND:
                        try {
                            argument = inputWords[1];
                            this.addEvent(argument);
                        } catch (IndexOutOfBoundsException e) {
                            sayException(new EmptyEventException());
                        }
                        break;
                    case DELETE_COMMAND:
                        try {
                            argument = inputWords[1];
                            this.deleteTask(Integer.parseInt(argument));
                        } catch (IndexOutOfBoundsException e) {
                            sayException(new EmptyDeleteException());
                        }
                        break;
                    default:
                        sayException(new UnknownCommandException());
                }
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private void say(String text) {
        System.out.println("------------------------------------------------------------");
        System.out.println(text);
        System.out.println("------------------------------------------------------------\n");
    }

    private void sayException(Exception e) {
        System.out.println("------------------------------------------------------------");
        System.out.println(e);
        System.out.println("------------------------------------------------------------\n");
    }

    private void hello() {
        say("Hello! I'm Duke\nWhat can I do for you?");
    }

    private void addTodo(String description) {
        Task toAdd = new ToDo(description);
        this.tasks.add(toAdd);
        sayAddedTask(toAdd);
        try {
            this.save();
        } catch (IOException e) {
            this.sayException(e);
        }
    }

    private void addDeadline(String text) {
        String description = text.split(" /by ", 2)[0];
        String deadline = text.split(" /by ", 2)[1];
        Task toAdd = new Deadline(description, deadline);
        this.tasks.add(toAdd);
        sayAddedTask(toAdd);
        try {
            this.save();
        } catch (IOException e) {
            this.sayException(e);
        }
    }

    private void addEvent(String text) {
        String description = text.split(" /at ", 2)[0];
        String time = text.split(" /at ", 2)[1];
        Task toAdd = new Event(description, time);
        this.tasks.add(toAdd);
        sayAddedTask(toAdd);
        try {
            this.save();
        } catch (IOException e) {
            this.sayException(e);
        }
    }

    private void sayAddedTask(Task task) {
        say("Got it. I've added this task:\n" + task + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }

    private void sayDeletedTask(Task task) {
        say("Noted. I've removed this task:\n" + task + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }

    private void displayTasks() {
        String text = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            boolean isLastTask = i == this.tasks.size() - 1;
            text += ((i + 1) + ". " + this.tasks.get(i).toString() + (isLastTask ? "" : "\n"));
        }
        say(text);
    }

    private void loadSavedFile() {

        File directory = new File(DATA_DIRECTORY);
        directory.mkdir(); // create the directory if it not existed

        File savedFile = new File(SAVED_FILE_PATH);
        try {
            savedFile.createNewFile();
            Scanner sc = new Scanner(savedFile);
            while (sc.hasNext()) {
                String taskString = sc.nextLine();
                String[] taskComponents = taskString.split(" - ");
                String taskType = taskComponents[0];
                boolean isDone = taskComponents[1].equals("1");
                String description = taskComponents[2];

                Task toAdd;
                switch (taskType) {
                    case "T":
                        toAdd = new ToDo(description, isDone);
                        break;
                    case "D":
                        toAdd = new Deadline(description, taskComponents[3], isDone);
                        break;
                    case "E":
                        toAdd = new Event(description, taskComponents[3], isDone);
                        break;
                    default:
                        throw new IllegalArgumentException("Saved file contains incorrect format");
                }
                this.tasks.add(toAdd);
            }
        } catch (IOException e) {
            sayException(e);
        }

    }

    private void save() throws IOException {
        FileWriter fw = new FileWriter(SAVED_FILE_PATH);
        for (Task task : this.tasks) {
            fw.write(task.simplifiedTaskString() + "\n");
        }
        fw.close();
    }

    private void bye() {
        say("Bye. Hope to see you again soon!");
    }

    private void markDone(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        try {
            Task toDone = this.tasks.get(zeroBasedIndex);
            toDone.markAsDone();
            String text = "Nice! I've marked this task as done:\n" + toDone;
            say(text);
            try {
                this.save();
            } catch (IOException e) {
                this.sayException(e);
            }
        } catch (IndexOutOfBoundsException e) {
            sayException(new DoneOutOfBoundException(oneBasedIndex));
        }
    }

    private void deleteTask(int oneBasedIndex) {
        int zeroBasedIndex = oneBasedIndex - 1;
        try {
            Task toDelete = this.tasks.remove(zeroBasedIndex);
            sayDeletedTask(toDelete);
        } catch (IndexOutOfBoundsException e) {
            sayException(new DeleteOutOfBoundException(oneBasedIndex));
        }

    }

}

