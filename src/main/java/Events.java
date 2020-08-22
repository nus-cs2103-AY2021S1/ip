import java.time.LocalDate;

public class Events extends Task {
    protected LocalDate date;
    protected int time;

    public Events(String task, String dateAndTime) {
//        // date = 'at Sunday 2-4pm'
//        String time = task.substring(task.indexOf("/") + 1, task.length());
//        time = Task.reformatDate(time); // (by: Sunday)
//
//        // task = project meeting
//        task = task.substring(0, task.indexOf("/") - 1);
        String[] dateAndTimeArr = dateAndTime.split(" ");
        this.task = task;
        this.date = LocalDate.parse(dateAndTimeArr[0]);
        this.time = Integer.parseInt(dateAndTimeArr[1]);
    }

    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        int dayOfMonth = this.date.getDayOfMonth();
        String month = this.date.getMonth().toString();
        int year = this.date.getYear();
        String date = dayOfMonth + " " + month + " " + year;
        String time = "";
        if (this.time < 100) {
            time = "12" + this.time + "am";
        } else if (this.time < 1200) {
            time = this.time + "am";
        } else {
            time = (this.time - 1200) + "pm";
        }

        return "[E]" + doneIndicator + " " + this.task + " (at: " + date + ", " + time + ")";
    }

}
