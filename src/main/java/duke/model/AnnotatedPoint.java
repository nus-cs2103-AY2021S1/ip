package duke.model;

/**
 * Annotated point class to denote part of an interval.
 */
public class AnnotatedPoint implements Comparable<AnnotatedPoint> {
    private int value;
    private PointType type;

    /**
     * Constructs an annotated point object.
     *
     * @param value value of the point.
     * @param type type of the point.
     */
    public AnnotatedPoint(int value, PointType type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public PointType getType() {
        return type;
    }

    @Override
    public int compareTo(AnnotatedPoint other) {
        if (other.value == this.value) {
            return this.type.ordinal() < other.type.ordinal() ? -1 : 1;
        } else {
            return this.value < other.value ? -1 : 1;
        }
    }
}

