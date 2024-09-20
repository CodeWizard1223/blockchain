import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Main {

    private static final ArrayList<Block> blockchain = new ArrayList<>();
    private static final int difficulty = 5;

    private static boolean isBlockchainValid() {
        if (!blockchain.isEmpty()) {
            for (int i = 0; i < blockchain.size(); i++) {
                Block block = blockchain.get(i);
                String expectedHash = Hasher.calculateHash(block.getPreviousHash(), block.getTransaction(),
                        block.getNonce());
                // has valid hash
                if (!expectedHash.equals(block.getHash())) {
                    System.out.println("Block has invalid hash.");
                    return false;
                }
                // block was mined/solved
                String hashTarget = new String(new char[difficulty]).replace('\0', '0');
                if (!block.getHash().substring(0, difficulty).equals(hashTarget)) {
                    System.out.println("Block wasn't mined.");
                    return false;
                }
                // for every block except the first compare previousHash
                if (i > 0) {
                    Block previousBlock = blockchain.get(i - 1);
                    if (!block.getPreviousHash().equals(previousBlock.getHash())) {
                        System.out.println("Block has invalid previousHash.");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Blockchain is empty.");
            return true;
        }

        return true;
    }

    public static void main(String[] args) {

        Miner miner = new Miner(difficulty);

        // 1. Create transaction
        String firstTransaction = "Stefan pays Damon 5CC";

        // 2. Miner listens to this transaction and mines block
        // (miner is an instance of the class Miner, we use the mineBlock function called to this object
        // to return a new block with parameter hash, previousHash, transaction and nonce
        // this is the genesis block so we need to use a default value "0" which represent the previousHash
        Block firstBlock = miner.mineBlock(firstTransaction, "0");

        // 3. Block is added to the blockchain
        blockchain.add(firstBlock);

        String secondTransaction = "Nina pays Jeremy 8CC";
        Block secondBlock = miner.mineBlock(secondTransaction, firstBlock.getHash());
        blockchain.add(secondBlock);

        String thirdTransaction = "Bonnie pays Caroline 7CC";
        Block thirdBlock = miner.mineBlock(thirdTransaction, secondBlock.getHash());
        blockchain.add(thirdBlock);

        // library Gson to print our blockchain to console
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Blockchain:");
        System.out.println(blockchainJson);

        // test of correctness
        //secondBlock.setTransaction("Bonnie pays Caroline 5CC");

        System.out.println("Is blockchain valid? " + isBlockchainValid());
    }

}