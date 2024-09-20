public class Miner {

    private final int difficulty;
    private final String target;

    public Miner(int difficulty) {

        // difficulty equals to difficulty of blockchain
        this.difficulty = difficulty;

        // Create new array with difficulty length and replace array index with '0'
        this.target = new String(new char[difficulty]).replace('\0', '0');
    }
    public Block mineBlock(String transaction, String previousHash) {
        System.out.println("Mining block...");

        // nonce is a number by which has the final hash the required difficulty;
        // miners are primary solving for THIS number
        long nonce = 0;
        String hash = Hasher.calculateHash(previousHash, transaction, nonce);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = Hasher.calculateHash(previousHash, transaction, nonce);
        }
        System.out.println("I mined a block at " + nonce + " attempt. Hash: " + hash);
        System.out.println();

        return new Block(transaction, previousHash, hash, nonce);
    }
}
