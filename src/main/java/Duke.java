import Task.Event;
import Task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Task.*;
import java.io.File;
import java.io.FileWriter;

public class Duke {
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke, como estas mi amigo?";
    private final static String ACTION_LIST = "list";
    private final static String TERMINATION = "bye";
    private final static String TERMINATION_MESSAGE = "Adios, amigos!";
    private final static String TASK_COMPLETED = "done";
    private final static String TASK_TODO = "todo";
    private final static String TASK_DEADLINE = "deadline";
    private final static String TASK_EVENT = "event";
    private final static String DELETE_EVENT = "delete";
    private final static String DEADLINE_DATE = "/by";
    private final static String EVENT_DATE = "/at";
    private static ArrayList<Task> userInputsList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        loadFile(System.getProperty("user.dir") + "/data/duke.txt");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String userInput = sc.next();
            switch (userInput) {
                case ACTION_LIST:
                    try {
                        if (userInputsList.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La lista está vacía. ¡Agregue una nueva tarea!");
                        }
                        System.out.println("Estas son las tareas de su lista:");
                        for (int i = 0; i < userInputsList.size(); i++) {
                            System.out.println((i + 1) + ". " + userInputsList.get(i).toString());
                        }
                    } catch (DukeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                case TERMINATION:
                    System.out.println(TERMINATION_MESSAGE);
                    userInputsList.clear();
                    break;
                case TASK_COMPLETED:
                    try {
                        userInput = sc.nextLine();
                        int positionDone = -1;
                        if (!userInput.isEmpty()) {
                            try {
                                positionDone = Integer.parseInt(userInput.substring(1));
                            } catch (NumberFormatException e) {
                                System.out.println("☹ OOPS !!! Incapaz de completar");
                            }
                        } else {
                            throw new DukeExceptions("☹ OOPS !!! Incapaz de completar");
                        }
                        positionDone = positionDone - 1;
                        if (positionDone < userInputsList.size() && positionDone >= 0) {
                            System.out.println("Agradable! He marcado esta tarea como hecha:");
                            userInputsList.get(positionDone).completeTask();
                            System.out.println(userInputsList.get(positionDone));
                            saveFile(false);
                        } else {
                            throw new DukeExceptions("☹ OOPS !!! ¡Esta tarea aún no existe!");
                        }
                    } catch (DukeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                case TASK_TODO:
                    try {
                        userInput = sc.nextLine();
                        if (userInput.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
                        }
                        int position = userInputsList.size() + 1;
                        Todo newTodo = new Todo(position, userInput);
                        userInputsList.add(newTodo);
                        System.out.println("Entendido. He agregado esta tarea:\n" +
                                newTodo);
                        saveFile(true);
                    } catch(DukeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                case TASK_DEADLINE:
                    try {
                        userInput = sc.nextLine();
                        if (userInput.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
                        }
                        if (!userInput.contains(DEADLINE_DATE)) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer una fecha límite para esta tarea.");
                        }
                        int byPosition = userInput.indexOf(DEADLINE_DATE);
                        int position = userInputsList.size() + 1;
                        String taskDescription = userInput.substring(0, byPosition);
                        String dateDescription = userInput.substring(byPosition + 3);
                        if (dateDescription.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer una fecha límite para esta tarea.");
                        }
                        Deadline newDeadline = new Deadline(position, taskDescription);
                        newDeadline.setTime(dateDescription);
                        userInputsList.add(newDeadline);
                        System.out.println("Entendido. He agregado esta tarea:\n" +
                                newDeadline);
                        saveFile(true);
                    } catch(DukeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                case TASK_EVENT:
                    try {
                        userInput = sc.nextLine();
                        if (userInput.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! La descripción de una tarea no puede estar vacía.");
                        }
                        if (!userInput.contains(EVENT_DATE)) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer la hora del evento para esta tarea.");
                        }
                        int atPosition = userInput.indexOf(EVENT_DATE);
                        int position = userInputsList.size() + 1;
                        String taskDescription = userInput.substring(0, atPosition);
                        String dateDescription = userInput.substring(atPosition + 3);
                        if (dateDescription.isEmpty()) {
                            throw new DukeExceptions("☹ OOPS !!! Debe establecer la hora del evento para esta tarea.");
                        }
                        Event newEvent = new Event(position, taskDescription);
                        newEvent.setTime(dateDescription);
                        userInputsList.add(newEvent);
                        System.out.println("Entendido. He agregado esta tarea:\n" +
                                newEvent);
                        saveFile(true);
                    } catch(DukeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                case DELETE_EVENT:
                    try {
                        userInput = sc.nextLine();
                        int positionDone = -1;
                        if (!userInput.isEmpty()) {
                            try {
                                positionDone = Integer.parseInt(userInput.substring(1));
                            } catch (NumberFormatException e) {
                                System.out.println("☹ OOPS !!! No se puede borrar");
                            }
                        } else {
                            throw new DukeExceptions("☹ OOPS !!! No se puede borrar");
                        }
                        positionDone = positionDone - 1;
                        if (positionDone < userInputsList.size() && positionDone >= 0) {
                            System.out.println("Célebre. He eliminado esta tarea:");
                            System.out.println(userInputsList.get(positionDone));
                            userInputsList.remove(positionDone);
                            saveFile(true);
                        } else {
                            throw new DukeExceptions("☹ OOPS !!! ¡Esta tarea aún no existe!");
                        }
                    } catch (DukeExceptions e) {
                        System.out.println(e.toString());
                    }
                    break;
                default:
                    try {
                        throw new DukeExceptions("OOPS !!! Lo siento, pero no sé qué significa eso :-(");
                    } catch(DukeExceptions e) {
                        System.out.println(e.toString());
                    }
            }
            if (userInput.equals(TERMINATION)) {
                sc.close();
                break;
            }
        }
    }

    public static void saveFile(boolean printLine) {
        if (printLine) {
            System.out.println("Ahora tienes " + userInputsList.size() + " tareas en la lista.");
        }
        String filepath = System.getProperty("user.dir") + "/data/duke.txt";
        try {
            FileWriter dukeFileWriter = new FileWriter(filepath, false);
            for (int i = 0; i < userInputsList.size(); i++) {
                dukeFileWriter.write(userInputsList.get(i).toString() + "\n");
            }
            dukeFileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void loadFile(String filepath) {
        File dukeFile = new File(filepath);
        if (dukeFile.exists()) {
            try {
                Scanner sc1 = new Scanner(dukeFile);
                while(sc1.hasNext()) {
                    String loadedInput = sc1.nextLine();
                    if (loadedInput.substring(0, 3).equals("[T]")) {
                        Todo newTodo = new Todo(userInputsList.size(), loadedInput.substring(7));
                        if (loadedInput.substring(4, 7).equals("[✓]")) {
                            newTodo.completeTask();
                        }
                        userInputsList.add(newTodo);
                    } else if (loadedInput.substring(0, 3).equals("[D]")) {
                        int byPosition = loadedInput.indexOf("by:");
                        Deadline newDeadline = new Deadline(userInputsList.size(),
                                loadedInput.substring(7, byPosition));
                        if (loadedInput.substring(4, 7).equals("[✓]")) {
                            newDeadline.completeTask();
                        }
                        newDeadline.setTime(loadedInput.substring(byPosition + 3));
                        userInputsList.add(newDeadline);
                    } else if (loadedInput.substring(0, 3).equals("[E]")) {
                        int atPosition = loadedInput.indexOf("at:");
                        Event newEvent = new Event(userInputsList.size(),
                                loadedInput.substring(7, atPosition));
                        if (loadedInput.substring(4, 7).equals("[✓]")) {
                            newEvent.completeTask();
                        }
                        newEvent.setTime(loadedInput.substring(atPosition + 3));
                        userInputsList.add(newEvent);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.toString());
            }
        } else {
            try {
                File dukeParent = new File(dukeFile.getParent());
                if (!dukeParent.exists()) {
                    dukeParent.mkdirs();
                }
                dukeFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.toString());
            }
        }
    }


}
