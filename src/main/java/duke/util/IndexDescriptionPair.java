package duke.util;

import java.util.Objects;

public class IndexDescriptionPair {
    private final int index;
    private final String description;

    /**
     * IndexDescriptionPair's constructor
     *
     * @param index       of the pair
     * @param description of the pair
     */
    public IndexDescriptionPair(int index, String description) {
        this.index = index;
        this.description = description;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IndexDescriptionPair that = (IndexDescriptionPair) o;
        return index == that.index && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, description);
    }

    @Override
    public String toString() {
        return "IndexDescriptionPair{"
            + "index=" + index
            + ", description='" + description + '\''
            + '}';
    }
}
