import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Hasher {
    // method for calculating hash for new block
    public static String calculateHash(String previousHash, String transaction, long nonce) {
        final String toHash = previousHash + transaction + nonce;
        return Hashing.sha256()
                .hashString(toHash, StandardCharsets.UTF_8)
                .toString();
    }
}
