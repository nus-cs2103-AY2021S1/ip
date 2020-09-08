public class DurationException extends DukeException {

    @Override
    public String toString() {
        return "Please provide a duration to set in the following format: duration <index of task in list> <duration to set, e.g 2 hours>";
    }
}
