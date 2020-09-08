import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * Class to store helper functions to validate variables
 */
public class Validator {

    // todo: fix ambiguous method call for requireNonNull(null) <- not sure which method to call

    public static void requireNonNull(Object object) {
        Objects.requireNonNull(object);
    }

    public static void requireNonNull(Object... objects) {
        Arrays.stream(objects).forEach(Objects::requireNonNull);
    }

    public static void requireNonNull(Collection<Object> objects) {
        objects.forEach(Objects::requireNonNull);
    }

}
