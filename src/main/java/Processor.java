import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Processor {
    List<Task> list = new ArrayList<>();
    String description;
    Commands command;
    boolean entered = false;
    String database;

    public void process(String next) throws IOException {
        try {
            findCommand(next);

            switch (command) {
                case DONE:
                    int pos = Integer.parseInt(this.description);
                    list.get(pos - 1).markAsDone();
                    System.out.println("Marked this task as done for you!\n" + list.get(pos - 1)
                            + "\nYou have " + list.size() + " tasks in the list.");
                    break;
                case LIST:
                    for (Task i : list) {
                        System.out.println(list.indexOf(i) + 1 + "." + i);
                    }
                    break;
                case TODO:
                    list.add(new Todo(this.description));
                    System.out.println("Hi there, I've added this task:\n"
                            + list.get(list.size() - 1) + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                case DEADLINE:
                    String[] descriptions = this.description.split("/by ");
                    list.add(new Deadline(descriptions[0], descriptions[1]));
                    System.out.println("Hi there, I've added this task:\n"
                            + list.get(list.size() - 1) + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                case EVENT:
                    String[] descriptions2 = this.description.split("/at ");
                    list.add(new Event(descriptions2[0], descriptions2[1]));
                    System.out.println("Hi there, I've added this task:\n"
                            + list.get(list.size() - 1) + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                case DELETE:
                    int pos2 = Integer.parseInt(this.description);
                    Task temp = list.get(pos2 - 1);
                    list.remove(pos2 - 1);
                    System.out.println("Removed this task for you!\n"
                            + temp + "\nYou have "
                            + list.size() + " tasks in the list.");
                    break;
                case DATE:
                    boolean dateExists = false;
                    for (Task i : list) {
                        if (i instanceof Deadline) {
                            if(((Deadline) i).hasDate(this.description)) {
                                System.out.println(i);
                                dateExists = true;
                            }
                        } else if (i instanceof Event) {
                            if (((Event) i).hasDate(this.description)) {
                                System.out.println(i);
                                dateExists = true;
                            }
                        }
                    }
                    if (!dateExists) {
                        System.out.println("No events/deadlines with this date!");
                    }
                    break;
                default:
                    System.out.println("Error! Please key in what type of task it is.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        save();

    }

    public void readFile() throws IOException, DukeException {
        String currentDirectory = System.getProperty("user.dir");
        File dataFolder = new File(currentDirectory + File.separator + "data");
        boolean directoryExists = dataFolder.exists() && dataFolder.isDirectory();
        if (directoryExists) {
            File database = new File(dataFolder.getAbsolutePath() + File.separator + "database.txt");
            try {
                Scanner s = new Scanner(database);
                while(s.hasNext()) {
                    String task = s.nextLine();
                    convertIntoTasks(task);
                }
                this.database = database.getAbsolutePath();
            } catch (FileNotFoundException e) {
                throw new DukeException("No file found sorry please make one");
            }
        } else {
            dataFolder.mkdir();
            File database = new File(dataFolder.getAbsolutePath() + File.separator + "database.txt");
            try {
                database.createNewFile();
                this.database = database.getAbsolutePath();
            } catch (IOException e) {
                throw new DukeException("File already exists");
            }
        }
    }

    private void convertIntoTasks(String s) {
        Task t;
        String[] descriptions = s.split("\\|");
        if (descriptions[0].equals("T")) {
            t = new Todo(descriptions[2]);
            if (descriptions[1].equals("T")) {
                t.markAsDone();
            }
            list.add(t);
        } else if (descriptions[0].equals("D")) {
            t = new Deadline(descriptions[2], descriptions[3]);
            if (descriptions[1].equals("T")) {
                t.markAsDone();
            }
            list.add(t);
        } else if (descriptions[0].equals("E")) {
            t = new Event(descriptions[2], descriptions[3]);
            if (descriptions[1].equals("T")) {
                t.markAsDone();
            }
            list.add(t);
        }
    }

    public void findCommand(String description) throws DukeException {
        if (description.length() == 0 && entered) {
            throw new DukeException("there's no commands");
        } else if (description.equals("list")) {
            if (list.size() == 0) {
                throw new DukeException("there's nothing on the list yet.");
            }
            command = Commands.LIST;
        } else if (description.length() >= 4 && description.substring(0, 4).equals("done")) {
            if (description.substring(3).split(" ").length == 1) {
                throw new DukeException("you need to give a number.");
            } else if (description.split(" ").length > 2) {
                throw new DukeException("Check one at a time pls and only one space between your 'done' and the task number.");
            } else {
                try {
                    int index = Integer.parseInt(description.split(" ")[1]);
                    if (this.list.size() < index || index <= 0) {
                        throw new DukeException("you don't have this task number.");
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("you need to put a number.");
                }
            }
            command = Commands.DONE;
            this.description = description.split(" ")[1];
        } else if (description.length() >= 4 && description.substring(0, 4).equals("todo")) {
            if (description.substring(3).split(" ").length == 1) {
                throw new DukeException(("you don't even know what you want to do."));
            }
            command = Commands.TODO;
            this.description = description.substring(5);
        } else if (description.length() >= 8 && description.substring(0, 8).equals("deadline")) {
            if (description.substring(7).split(" ").length == 1) {
                throw new DukeException("no deadline given so how you know when it is?");
            } else if (description.split("/by").length < 2) {
                throw new DukeException("for deadlines, please include a '/by' just before the deadline!");
            }
            command = Commands.DEADLINE;
            this.description = description.substring(9);
        } else if (description.length() >= 5 && description.substring(0, 5).equals("event")) {
            if (description.substring(4).split(" ").length == 1) {
                throw new DukeException("I don't see any event.");
            } else if (description.split("/at").length < 2) {
                throw new DukeException("for events, please include a '/at' just before the event!");
            }
            command = Commands.EVENT;
            this.description = description.substring(6);
        } else if (description.length() >= 6 && description.substring(0,6).equals("delete")) {
            if (description.substring(5).split(" ").length == 1) {
                throw new DukeException("you need to give a number.");
            } else if (description.split(" ").length > 2) {
                throw new DukeException("Delete one at a time pls and only have one space between 'delete' and the task number.");
            } else {
                try {
                    int index = Integer.parseInt(description.split(" ")[1]);
                    if (this.list.size() < index || index <= 0) {
                        throw new DukeException("you don't have this task number.");
                    }
                } catch (NumberFormatException e) {
                    throw new DukeException("you need to put a number.");
                }
            }
            command = Commands.DELETE;
            this.description = description.split(" ")[1];
        } else if (description.length() >= 4 && description.substring(0,4).equals("date")) {
            if (description.substring(3).split(" ").length == 1 || description.substring(3).split(" ").length > 2 ) {
                throw new DukeException(("you need to input a legit date for e.g: 29-01-19, no more and no less."));
            }
            command = Commands.DATE;
            this.description = description.substring(5);
        } else if (entered) {
            throw new DukeException("you gotta put in a correct command.");
        } else {
            throw new DukeException("type in 'todo', 'deadline', 'event' to start!\nAlso, type 'date' and key in a date in YYYY-MM-DD format to search for events/deadlines happening on that date!");
        }
        entered = true;
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(this.database);
        for (Task task : list) {
            if (task instanceof Todo) {
                if (task.getDone()) {
                    fw.write("T" + "|" + "T" + "|" + task.getDescription() + System.lineSeparator());
                } else {
                    fw.write("T" + "|" + "F" + "|" + task.getDescription() + System.lineSeparator());
                }
            } else if (task instanceof Deadline) {
                if (task.getDone()) {
                    fw.write("D" + "|" + "T" + "|" + task.getDescription() + "|" + ((Deadline) task).getDate() + System.lineSeparator());
                } else {
                    fw.write("D" + "|" + "F" + "|" + task.getDescription() + "|" + ((Deadline) task).getDate() + System.lineSeparator());
                }
            } else if (task instanceof Event) {
                if (task.getDone()) {
                    fw.write("E" + "|" + "T" + "|" + task.getDescription() + "|" + ((Event) task).getDate() + System.lineSeparator());
                } else {
                    fw.write("E" + "|" + "F" + "|" + task.getDescription() + "|" + ((Event) task).getDate() + System.lineSeparator());
                }
            } else {
            }
        }
        fw.close();
    }
}

