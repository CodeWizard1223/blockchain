public class Block {
    private final String hash;
    private final String previousHash;
    private String transaction;
    private final long nonce;

    public Block(String transaction, String previousHash, String hash, long nonce) {
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.hash = hash;
        this.nonce = nonce;
    }

    public String getHash() {

        return hash;
    }

    public String getPreviousHash() {

        return previousHash;
    }

    public String getTransaction() {

        return transaction;
    }

    public long getNonce() {
        return nonce;
    }
    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }
}
