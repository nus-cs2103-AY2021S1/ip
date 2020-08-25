package dependencies.storage;


/**
 * Fowler-No-Voll hash utility function.
 */
public class FNV64 {
    private static final long PRIME = 0x100000001b3L;
    private static final long OFFSET = 0xcbf29ce484222325L;

    /**
     * Hash a string.
     *
     * @param k hash a string
     * @return a 64 bit hash
     */
    public static long hash(String k) {
        long ret = OFFSET;
        int l = k.length();
        for (int i = 0; i < l; i++) {
            ret ^= k.charAt(i);
            ret *= PRIME;
        }
        return ret;
    }
}