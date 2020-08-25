package main.java;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Task {
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String name, String eventDetail) throws DukeException{
        super(name);
        String[] input = eventDetail.split("\\s+");
        try {
            super.setDate(LocalDate.parse(input[0]));
            startTime = LocalTime.parse(input[1]);
            endTime = LocalTime.parse(input[2]);
        } catch (Exception err) {
            throw new DukeException("Error: Please key in as: \n" +
                    "event [title] /at YYYY-MM-DD [startTime] [endTime] where start and end time is in HH:MM ");
        }
    }

    @Override
    public String toSaveFormat() {
        return String.format("E%s | %s %s %s", super.toSaveFormat(), SAVE_DATE_FORMATTER.format(super.date.get()),
                Task.TIME_FORMATTER.format(startTime), Task.TIME_FORMATTER.format(endTime));
    }

    @Override
    public String toString() {
        String dateTime = Task.DATE_FORMATTER.format(super.date.get());
        dateTime += " " + Task.TIME_FORMATTER.format(startTime);
        dateTime += " - " + Task.TIME_FORMATTER.format(endTime);
        return String.format("[E]%s (at: %s)", super.toString(), dateTime);
    }
}
