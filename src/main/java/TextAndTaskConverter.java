import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TextAndTaskConverter {

    private final String text;

    TextAndTaskConverter(String text) {
        this.text = text;
    }

    public static Task textConverter(String text) {
        String[] split = text.split(" ## ");

        String description = split[2];

        if (split.length == 3) {
            System.out.print("length3");
            Todo task = new Todo(description);
            if (split[1].trim().equals("1")) {
                System.out.println(split[1]);
                task.setDone();
            }
            return task;

        } else if (split.length == 4) {
            Task task = null;
            System.out.println("elseif");
            if (split[0].trim().equals("D")) {
                LocalDate date = LocalDate.parse(split[3]);
                task = new Deadline(description, date);

                if (split[1].equals("1")) {
                    task.setDone();
                }

            } else if (split[0].trim().equals("E")) {
                String[] dateAndTime = split[3].split(" ", 2);

                LocalDate date = LocalDate.parse(dateAndTime[0]);
                LocalTime time = LocalTime.parse(dateAndTime[1]);

                task = new Event(description, date, time);

                if (split[1].equals("1")) {
                    task.setDone();
                }
                //return task;
            }
            return task;

        } else {
            System.out.println("else");
            return null;
        }
    }

    public static String getTaskName(String text) {
        String[] split1 = text.split("/", 2);
        return split1[0];
    }

    public static LocalDate getDate(String text) {
        String[] split1 = text.split("/", 2);
        String trimText = split1[1].trim();

        if (!trimText.contains(" ")) {
            return LocalDate.parse(trimText);

        } else {
            String[] split2 = trimText.split(" ", 2);
            return LocalDate.parse(split2[0]);
        }
    }

    public static LocalTime getTime(String text) {
        String[] split1 = text.split("/", 2);
        String trimText = split1[1].trim();

        if (trimText.split(" ", 2).length == 0) {
            System.out.println("trim text: " + trimText);
            return null;

        } else {
            String[] split2 = trimText.split(" ", 2);
            LocalTime t = LocalTime.parse(split2[1]);
            return t;
        }
    }
}

