package duke.model;

public class Interval {
    private int start;
    private int end;

    /**
     * Constructs an interval object.
     *
     * @param start start of the interval.
     * @param end end of the interval.
     */
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "(" + start + "," + end + ")";
    }
}
