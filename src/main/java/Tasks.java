import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Command {
    LIST("list"),
    DONE("done"),
    DELETE("delete");

    private String command;

    private Command(String command) {
        this.command = command;
    }

    String getCommand() {
        return this.command;
    }

    @Override
    public String toString() {
        return this.command;
    }
}

enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    private String type;

    private TaskType(String type) {
        this.type = type;
    }

    String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}

public class Tasks {
    private List<Task> list;

    Tasks() {
        this.list = new ArrayList<>();
    }

    void processInput(String input) throws DukeException {
        String[] inputArray = input.split(" ");
        input = input.strip();
        if (input.equals(Command.LIST.getCommand())) {
            this.listTasks();
        } else if (input.equals(Command.DONE.getCommand()) || input.equals(Command.DELETE.getCommand())) {
            throw new InvalidCommandException("☹ OOPS!!! The index of a task cannot be empty.");
        } else if (inputArray[0].equals(Command.DONE.getCommand())) {
            this.markDone(Integer.parseInt(inputArray[1]));
        } else if (inputArray[0].equals(Command.DELETE.getCommand())) {
            this.deleteTask(Integer.parseInt(inputArray[1]));
        } else {
            String type = input.split(" ")[0];
            String temp = input.strip();
            if (temp.equals(TaskType.TODO.getType()) || temp.equals(TaskType.DEADLINE.getType()) || temp.equals(TaskType.EVENT.getType())) {
                throw new InvalidArgumentException("☹ OOPS!!! The description of a " + temp + " cannot be empty.");
            } else if (temp.equals("")) {
                throw new InvalidTaskTypeException("☹ OOPS!!! The type of a task cannot be empty.");
            }
            if (type == null || (!type.equals(TaskType.TODO.getType()) && !type.equals(TaskType.DEADLINE.getType()) && !type.equals(TaskType.EVENT.getType()))) {
                throw new InvalidTaskTypeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            String details = input.substring(type.length());
            if (type.equals(TaskType.TODO.getType())) {
                ToDo t = new ToDo(details.strip());
                this.list.add(t);
                echo(t);
            } else {
                String[] detailsArray = details.split("/");
                String[] keywords = detailsArray[1].split(" ");
                String datetime = detailsArray[1].substring(keywords[0].length() + 1);
                if (type.equals(TaskType.DEADLINE.getType())) {
                    Deadline d = new Deadline(detailsArray[0].strip(), keywords[0] + ": " + datetime);
                    this.list.add(d);
                    echo(d);
                } else {
                    Event e = new Event(detailsArray[0].strip(), keywords[0] + ": " + datetime);
                    this.list.add(e);
                    echo(e);
                }
            }
        }
    }

    void processStorage(File f, Scanner sc) throws IOException {
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] dataArray = data.split(" \\| ");
            switch (dataArray[0]) {
            case "T":
                list.add(new ToDo(dataArray[2], dataArray[1]));
                break;
            case "E":
                list.add(new Event(dataArray[2], dataArray[3], dataArray[1]));
                break;
            case "D":
                list.add(new Deadline(dataArray[2], dataArray[3], dataArray[1]));
                break;
            default:
            throw new IOException("Invalid data");
            }
        }
    }

    void listTasks() {
        System.out.println("\t___________________________________________________________________________");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + list.get(i));
        }
        System.out.println("\t___________________________________________________________________________\n");
    }

    void echo(Task t) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + t);
        System.out.println("\t Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("\t___________________________________________________________________________\n");
    }

    void markDone(int index) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Nice! I've marked this task as done:");
        this.list.get(index - 1).markAsDone();
        System.out.println("\t   " + this.list.get(index - 1));
        System.out.println("\t___________________________________________________________________________\n");
    }

    void deleteTask(int index) {
        System.out.println("\t___________________________________________________________________________");
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + this.list.get(index - 1));
        this.list.remove(index - 1);
        System.out.println("\t Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("\t___________________________________________________________________________\n");
    }

    void writeData() throws IOException {
        FileWriter fw = new FileWriter("data/data.txt");
        for (Task t: list) {
            String toWrite = "";
            toWrite += (t.getType() + " | " + (t.isCompleted ? "1" : "0") + " | " + t.description + (t.getDate() != null ? (" | " + t.getDate()) : "") + "\n");
            fw.write(toWrite);
        }
        fw.close();
    }
}
