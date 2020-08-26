import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final static String bar = "____________________________________________________________";
    private final static String greeting = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final static String goodbye = "Bye. Hope to see you again soon!";
    //----- level 3 here -----
    private static ArrayList<Task> list = new ArrayList<>();
    private static int count = 0;
    private final static String done = "Nice! I've marked this task as done:";
    //----- level 4 here -----
    private final static String add = "Got it. I've added this task:";
    //----- level 6 here -----
    private final static String delete = "Noted. I've removed this task:";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + Duke.logo);
        System.out.println(Duke.bar);
        System.out.println(Duke.greeting);
        System.out.println(Duke.bar);

        //---- INITIALISE FROM FILE HERE ----
        File dir = new File(System.getProperty("user.dir") + "/data/");
        File outputFile = new File(dir, "Duke.txt");

        try {
            if (outputFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(outputFile));
                String currLine;
                while ((currLine = reader.readLine()) != null) {
                    char startChar = currLine.charAt(0);
                    boolean isDone = currLine.charAt(4) == '1';
                    switch (startChar) {
                        case 'T': {
                            String description = currLine.substring(8);
                            ToDo taskToAdd = new ToDo(description, isDone);
                            Duke.count++;
                            Duke.list.add(taskToAdd);
                            break;
                        }
                        case 'D': {
                            String descriptionAndDeadline = currLine.substring(8);
                            int stringBreak = descriptionAndDeadline.indexOf('|');
                            String deadline = descriptionAndDeadline.substring(stringBreak + 2);
                            String description = descriptionAndDeadline.substring(0, stringBreak - 1);
                            Deadline taskToAdd = new Deadline(description, deadline, isDone);
                            Duke.count++;
                            Duke.list.add(taskToAdd);
                            break;
                        }
                        case 'E': {
                            String descriptionAndDate = currLine.substring(8);
                            int stringBreak = descriptionAndDate.indexOf('|');
                            String date = descriptionAndDate.substring(stringBreak + 2);
                            String description = descriptionAndDate.substring(0, stringBreak - 1);
                            Event taskToAdd = new Event(description, date, isDone);
                            Duke.count++;
                            Duke.list.add(taskToAdd);
                            break;
                        }
                        default:

                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to read existing TODO list. Duke will initialise blankly.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            boolean isUpdated = false;
            System.out.println(Duke.bar);
            try {
                //DONE PORTION HERE----------------------------------------------------------
                if (userInput.length() >= 4 && userInput.substring(0, 4).equals("done")) {
                    if (userInput.length() <= 5) {
                        throw new MissingDoneArgumentException();
                    }
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    if (index >= Duke.count || index < 0) {
                        throw new DoneOutOfRangeException();
                    }
                    Duke.list.get(index).markAsDone();
                    System.out.println(Duke.done);
                    System.out.println("  " + Duke.list.get(index));
                    isUpdated = true;

                //DELETE PORTION HERE---------------------------------------------------------
                } else if (userInput.length() >= 6 && userInput.substring(0, 6).equals("delete")) {
                    if (userInput.length() <= 7) {
                        throw new MissingDeleteArgumentException();
                    }
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    if (index >= Duke.count || index < 0) {
                        throw new DeleteOutOfRangeException();
                    }
                    Task toDelete = Duke.list.get(index);
                    Duke.list.remove(index);
                    Duke.count--;
                    System.out.println(Duke.delete);
                    System.out.println("  " + toDelete);
                    System.out.println("Now you have " + Duke.count + (Duke.count==1?" task ":" tasks ") + "in the list.");
                    isUpdated = true;

                //TOD0 PORTION HERE-----------------------------------------------------------
                } else if (userInput.length() >= 4 && userInput.substring(0, 4).equals("todo")) {
                    if (userInput.length() == 4) {
                        throw new EmptyTodoException();
                    }
                    String description = userInput.substring(5);
                    if (description.length() == 0) {
                        throw new EmptyTodoException();
                    }
                    ToDo taskToAdd = new ToDo(description);
                    Duke.count++;
                    Duke.list.add(taskToAdd);
                    System.out.println(add);
                    System.out.println("  " + taskToAdd);
                    System.out.println("Now you have " + Duke.count + (Duke.count==1?" task ":" tasks ") + "in the list.");
                    isUpdated = true;

                //DEADLINE PORTION HERE--------------------------------------------------------
                } else if (userInput.length() >= 8 && userInput.substring(0, 8).equals("deadline")) {
                    int index = userInput.indexOf("/");
                    if (index == -1) {
                        throw new MissingDeadlineDateException();
                    }
                    if (userInput.length() == 8) {
                        throw new EmptyDeadlineException();
                    }
                    String description = userInput.substring(9,index-1);
                    if (description.length() == 0) {
                        throw new EmptyDeadlineException();
                    }
                    String date = userInput.substring(index+4);
                    if (date.length() == 0) {
                        throw new MissingDeadlineDateException();
                    }
                    Deadline taskToAdd = new Deadline(description, date);
                    Duke.count++;
                    Duke.list.add(taskToAdd);
                    System.out.println(add);
                    System.out.println("  " + taskToAdd);
                    System.out.println("Now you have " + Duke.count + (Duke.count==1?" task ":" tasks ") + "in the list.");
                    isUpdated = true;

                //EVENT PORTION HERE-----------------------------------------------------------
                } else if (userInput.length() >= 5 && userInput.substring(0, 5).equals("event")) {
                    int index = userInput.indexOf("/");
                    if (index == -1) {
                        throw new MissingEventDateException();
                    }
                    if (userInput.length() == 5) {
                        throw new EmptyEventException();
                    }
                    String description = userInput.substring(6,index-1);
                    if (description.length() == 0) {
                        throw new EmptyEventException();
                    }
                    String date = userInput.substring(index+4);
                    if (date.length() == 0) {
                        throw new MissingEventDateException();
                    }
                    Event taskToAdd = new Event(description, date);
                    Duke.count++;
                    Duke.list.add(taskToAdd);
                    System.out.println(add);
                    System.out.println("  " + taskToAdd);
                    System.out.println("Now you have " + Duke.count + (Duke.count==1?" task ":" tasks ") + "in the list.");
                    isUpdated = true;

                //LIST PORTION HERE------------------------------------------------------------
                } else if (userInput.equals("list")) {
                    for (int i = 0; i < Duke.count; i++) {
                        System.out.println(i+1 + ". " + list.get(i));
                    }
                } else {
                    throw new UnknownCommandException();
                }
                if(isUpdated) {
                    //WRITE TO FILE HERE
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    outputFile.createNewFile();

                    FileWriter writer = new FileWriter(System.getProperty("user.dir") + "/data/Duke.txt");
                    for (int i = 0; i < count; i++) {
                        writer.write(list.get(i).fileText()+"\n");
                    }
                    writer.close();
                }

            } catch (MissingDoneArgumentException e) {
                System.out.println(e);
            } catch (UnknownCommandException e) {
                System.out.println(e);
            } catch (EmptyEventException e) {
                System.out.println(e);
            } catch (MissingEventDateException e) {
                System.out.println(e);
            } catch (MissingDeadlineDateException e) {
                System.out.println(e);
            } catch (EmptyDeadlineException e) {
                System.out.println(e);
            } catch (EmptyTodoException e) {
                System.out.println(e);
            } catch (DoneOutOfRangeException e) {
                System.out.println(e);
            } catch (MissingDeleteArgumentException e) {
                System.out.println(e);
            } catch (DeleteOutOfRangeException e) {
                System.out.println(e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println("\uD83D\uDE41 OOPS! Date should be in the format: YYYY-MM-DD");
            }


            System.out.println(Duke.bar);
            userInput = sc.nextLine();
        }
        sc.close();
        System.out.println(Duke.bar);
        System.out.println(Duke.goodbye);
        System.out.println(Duke.bar);
    }
}
