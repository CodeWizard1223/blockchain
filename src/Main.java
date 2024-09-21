import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Main {

    public static final ArrayList<Block> blockchain = new ArrayList<>();
    public static final int difficulty = 5;

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

        System.out.println("Is blockchain valid? " + Blockchain.isBlockchainValid());
    }

}