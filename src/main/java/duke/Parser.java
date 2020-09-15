package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    private TaskList tasks;

    /**
     * Class constructor specifying the TaskList.
     * @param tasks
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints the output after store keyword is found.
     * @param store
     */
    public void parseList(ArrayList<Task> store) {
        //Counter to keep track of the index of the Tasks.
        int counter = 1;
        System.out.println("-------------------------");
        for (Task task : store) {
            if (task.getDescription() != null) {
                System.out.println(counter + ". [" + task.getType()
                        + "][" + task.getStatusIcon() + "] " + task.getDescription()
                        + task.recurringPrinter());
                counter++;
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * Parses the specified String as required.
     * @param str the String to be parsed.
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void parse(String str) throws DukeException, IOException {

        ArrayList<Task> store = this.tasks.getList();

        if (str.equals("blah")) {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        while (str != null) {
            if (str.equals("list")) {
                this.parseList(store);
                break;
            } else if (str.contains("done")) {
                if (str.length() == 4) {
                    throw new DukeException("\u2639 OOPS!!! Please state what task has been completed.");
                }

                //To get the index of the corresponding task after they keyword "done".
                int num = Integer.parseInt(str.substring(5)) - 1;

                //Store the current Task to the TaskList after marking as done.
                Task curr = store.get(num);

                if (curr.recurring && curr instanceof Deadlines) {
                    LocalDate date = ((Deadlines) curr).getDate();
                    LocalDate newDate = date.plusWeeks(1);
                    String formattedDate = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ((Deadlines) curr).setDeadline(formattedDate);
                    System.out.println("-------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("The same task has to be completed next week!");
                    System.out.println("I have added the same task to the list for next week");
                    System.out.println("  [" + curr.getStatusIcon() + "] " + curr.getDescription());
                    System.out.println("-------------------------");
                    break;
                }

                if (curr.recurring && curr instanceof Events) {
                    LocalDate date = ((Events) curr).getDate();
                    LocalDate newDate = date.plusWeeks(1);
                    String formattedDate = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ((Events) curr).setStart(formattedDate);
                    System.out.println("-------------------------");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("The same task has to be completed next week!");
                    System.out.println("I have added the same task to the list for next week");
                    System.out.println("  [" + curr.getStatusIcon() + "] " + curr.getDescription());
                    System.out.println("-------------------------");
                    break;
                }
                curr.markAsDone();

                System.out.println("-------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  [" + curr.getStatusIcon() + "] " + curr.getDescription());
                System.out.println("-------------------------");
                break;
            } else if (str.contains("todo")) {
                try {
                    if (str.length() == 4) {
                        throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                    }

                    //The description of the Task occurs after the 5th String index.
                    String description = str.substring(5);

                    //Add the Task to the TaskList.
                    ToDos curr = new ToDos(description);
                    if (str.contains("recurring")) {
                        curr.recurring = true;
                        curr.removeRecurring();
                    }
                    store.add(curr);

                    System.out.println("-------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription() + curr.recurringPrinter());
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    System.out.println("-------------------------");

                    Path relativePath = Paths.get("duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder result = new StringBuilder("");

                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            result.append("[" + task.getType() + "]"
                                    + "[" + task.getStatusIcon() + "] " + task.getDescription()
                                    + task.recurringPrinter());
                            result.append("\n");
                        }
                    }

                    writer.append(result);
                    writer.close();
                    break;
                } catch (IOException e) {
                    File yourFile = new File("duke.txt");
                    yourFile.createNewFile();
                }
            } else if (str.contains("deadline")) {
                try {

                    //The description of the Task is given after the 9th String index.
                    Deadlines curr = new Deadlines(str.substring(9));
                    String description = curr.getDescription();

                    //To get the index of the beginning of the deadline.
                    int index = description.indexOf("/") + 4;
                    curr.setDeadline(description.substring(index));
                    curr.setDateTime();
                    if (str.contains("recurring")) {
                        curr.recurring = true;
                        curr.removeRecurring();
                    }

                    //Add the current Task to the TaskList.
                    store.add(curr);

                    System.out.println("-------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription().substring(0, description.indexOf("/"))
                            + "(by: " + curr.getDeadline() + ")" + curr.recurringPrinter());
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    System.out.println("-------------------------");

                    Path relativePath = Paths.get("duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder result = new StringBuilder("");

                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            result.append("[" + task.getType() + "]"
                                    + "[" + task.getStatusIcon() + "] " + task.getDescription()
                                    + task.recurringPrinter());
                            result.append("\n");
                        }
                    }

                    //Write to the duke.txt save file.
                    writer.append(result);
                    writer.close();
                    break;
                } catch (IOException e) {
                    File yourFile = new File("duke.txt");
                    yourFile.createNewFile();
                    break;
                }
            } else if (str.contains("event")) {
                try {
                    Events curr = new Events(str.substring(6));
                    String description = curr.getDescription();

                    //To get the index of the description after the keyword "event".
                    int index = description.indexOf("/") + 4;
                    curr.setStart(description.substring(index));
                    curr.setDateTime();
                    if (str.contains("recurring")) {
                        curr.recurring = true;
                        curr.removeRecurring();
                    }

                    //Add the current task to the TaskList.
                    store.add(curr);

                    System.out.println("-------------------------");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription().substring(0, description.indexOf("/"))
                            + "(at: " + curr.getStart() + ")"
                            + curr.recurringPrinter());
                    System.out.println("Now you have " + store.size() + " tasks in the list.");
                    System.out.println("-------------------------");

                    Path relativePath = Paths.get("data/duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder result = new StringBuilder("");

                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            result.append("[" + task.getType() + "]" + "[" + task.getStatusIcon()
                                    + "] |" + task.getDescription()
                                    + curr.recurringPrinter());
                            result.append("\n");
                        }
                    }

                    writer.append(result);
                    writer.close();
                    break;
                } catch (IOException e) {
                    File yourFile = new File("duke.txt");
                    yourFile.createNewFile();
                    break;
                }
            } else if (str.contains("delete")) {
                if (str.length() == 6) {
                    throw new DukeException("\u2639 OOPS!!! Please specify what task to delete.");
                }

                //To get the index of the corresponding Task to delete.
                int num = Integer.parseInt(str.substring(7)) - 1;
                Task curr = store.get(num);

                //Remove the Task from the TaskList.
                store.remove(curr);

                System.out.println("-------------------------");
                System.out.println("Noted! I've removed this task:");
                System.out.println(curr);
                System.out.println("Now you have " + store.size() + " tasks in the list.");
                System.out.println("-------------------------");
                break;
            } else if (str.contains("find")) {
                TaskList tasks = new TaskList(store);

                //The keyword to find appears after the space.
                int index = str.indexOf(" ") + 1;
                String query = str.substring(index);
                tasks.find(query);
                break;
            } else if (!str.equals("bye")) {
                System.out.println("-------------------------");
                System.out.println("added: " + str);
                System.out.println("-------------------------");
                store.add(new Task(str));
                break;
            } else {
                break;
            }
        }
    }

    /**
     * Returns the result string after parsing, same logic as parse.
     * @param str
     * @return the result string
     * @throws DukeException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public String parseStr(String str) throws DukeException, IOException {

        ArrayList<Task> store = this.tasks.getList();
        String result = "";

        while (str != null) {
            if (str.equals("list")) {
                int counter = 1;

                for (Task task : store) {
                    if (task.getDescription() != null) {
                        result = result + counter + ". " + task.toString()
                                + task.recurringPrinter() + "\n";
                        counter++;
                    }
                }

                return result;
            } else if (str.contains("done")) {
                if (str.length() == 4) {
                    throw new DukeException("\u2639 OOPS!!! Please state what task has been completed.");
                }

                int num = Integer.parseInt(str.substring(5)) - 1;
                Task curr = store.get(num);

                if (curr.recurring && curr instanceof Deadlines) {
                    LocalDate date = ((Deadlines) curr).getDate();
                    LocalDate newDate = date.plusWeeks(1);
                    String formattedDate = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ((Deadlines) curr).setDeadline(formattedDate);
                    return "Nice! I've marked this task as done: \n"
                        + "The same task has to be completed next week! \n"
                        + "I have added the same task to the list for next week"
                        + "  [" + curr.getStatusIcon() + "] " + curr.getDescription() + "\n";
                } else if (curr.recurring && curr instanceof Events) {
                    LocalDate date = ((Events) curr).getDate();
                    LocalDate newDate = date.plusWeeks(1);
                    String formattedDate = newDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ((Events) curr).setStart(formattedDate);
                    return "Nice! I've marked this task as done: \n"
                            + "The same task has to be completed next week! \n"
                            + "I have added the same task to the list for next week"
                            + "  [" + curr.getStatusIcon() + "] " + curr.getDescription() + "\n";
                }

                curr.markAsDone();
                result = result + "Nice! I've marked this task as done:\n";
                result = result + "  [" + curr.getStatusIcon() + "] " + curr.getDescription() + "\n";
                return result;
            } else if (str.contains("todo")) {
                try {
                    if (str.length() == 4) {
                        throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
                    }

                    ToDos curr = new ToDos(str.substring(5));
                    if (str.contains("recurring")) {
                        curr.recurring = true;
                        curr.removeRecurring();
                    }
                    store.add(curr);

                    result = result + "Got it. I've added this task:\n";
                    result = result + "  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription() + curr.recurringPrinter() + "\n";
                    result = result + "Now you have " + store.size() + " tasks in the list.\n";

                    Path relativePath = Paths.get("duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');

                    StringBuilder res = new StringBuilder("");
                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            res.append("[" + task.getType() + "]"
                                    + "[" + task.getStatusIcon() + "] " + task.getDescription()
                                    + task.recurringPrinter());
                            res.append("\n");
                        }
                    }

                    writer.append(res);
                    writer.close();
                    return result;
                } catch (IOException e) {
                    File yourFile = new File("duke.txt");
                    yourFile.createNewFile();
                }
            } else if (str.contains("deadline")) {
                try {
                    Deadlines curr = new Deadlines(str.substring(9));
                    String description = curr.getDescription();
                    int index = description.indexOf("/") + 4;

                    String deadline = description.substring(index);

                    if (deadline.length() < 15) {
                        return "You are missing the date or the time, or the format is incorrect!\n"
                                + "Do refer to the instructions provided for the formatting supported\n";
                    }

                    curr.setDeadline(deadline);
                    curr.setDateTime();

                    if (str.contains("recurring")) {
                        curr.recurring = true;
                        curr.removeRecurring();
                    }

                    store.add(curr);

                    result = result + "Got it. I've added this task:\n";
                    result = result + "  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription().substring(0, description.indexOf("/"))
                            + "(by: " + curr.getDeadline() + ")"
                            + curr.recurringPrinter() + "\n";
                    result = result + "Now you have " + store.size() + " tasks in the list.\n";

                    Path relativePath = Paths.get("duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();

                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');
                    StringBuilder res = new StringBuilder("");

                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            res.append("[" + task.getType() + "]"
                                    + "[" + task.getStatusIcon() + "] " + task.getDescription()
                                    + task.recurringPrinter());
                            res.append("\n");
                        }
                    }

                    writer.append(res);
                    writer.close();
                    return result;
                } catch (IOException e) {
                    File yourFile = new File("duke.txt");
                    yourFile.createNewFile();
                    break;
                }
            } else if (str.contains("event")) {
                try {
                    Events curr = new Events(str.substring(6));
                    String description = curr.getDescription();
                    int index = description.indexOf("/") + 4;

                    String startTime = description.substring(index);
                    if (startTime.length() < 15) {
                        return "You are missing the date or the time, or the format is incorrect!\n"
                                + "Do refer to the instructions provided for the formatting supported";
                    }
                    curr.setStart(startTime);
                    curr.setDateTime();

                    if (str.contains("recurring")) {
                        curr.recurring = true;
                        curr.removeRecurring();
                    }
                    store.add(curr);

                    result = result + "Got it. I've added this task:\n";
                    result = result + "  [" + curr.getType() + "][" + curr.getStatusIcon() + "] "
                            + curr.getDescription().substring(0, description.indexOf("/"))
                            + "(at: " + curr.getStart() + ")"
                            + curr.recurringPrinter() + "\n";
                    result = result + "Now you have " + store.size() + " tasks in the list.\n";

                    Path relativePath = Paths.get("data/duke.txt");
                    Path absolutePath = relativePath.toAbsolutePath();
                    BufferedWriter writer = new BufferedWriter(new FileWriter("" + absolutePath));
                    writer.append('\n');

                    StringBuilder res = new StringBuilder("");
                    for (Task task : store) {
                        if (task.getDescription() != null) {
                            res.append("[" + task.getType() + "]" + "[" + task.getStatusIcon()
                                    + "] |" + task.getDescription() + curr.recurringPrinter());
                            res.append("\n");
                        }
                    }

                    writer.append(res);
                    writer.close();
                    return result;
                } catch (IOException e) {
                    File yourFile = new File("duke.txt");
                    yourFile.createNewFile();
                    break;
                }
            } else if (str.contains("delete")) {

                assert store.size() > 0 : "There are already no tasks!";

                if (str.length() == 6) {
                    throw new DukeException("\u2639 OOPS!!! Please specify what task to delete.");
                }
                int num = Integer.parseInt(str.substring(7)) - 1;
                if (num >= store.size()) {
                    return "The number provided is greater than the number of tasks!";
                }
                Task curr = store.get(num);
                store.remove(curr);

                result = result + "Noted! I've removed this task:\n";
                result = result + curr.toString() + "\n";
                result = result + "Now you have " + store.size() + " tasks in the list.\n";
                return result;
            } else if (str.contains("find")) {
                TaskList tasks = new TaskList(store);
                int index = str.indexOf(" ") + 1;
                String query = str.substring(index);
                result = "Here's what I found:\n"
                        + tasks.finder(query);
                if (result.equals("")) {
                    return "There is no such task in the task list!";
                }
                return result;
            } else if (str.equals("bye")) {
                result = "Alright then cya again soon";
                return result;
            } else {
                result = "What did you just say to me?? Please follow the instructions given at the start";
                return result;
            }
        }
        return "I am unsure what command that is";
    }
}
