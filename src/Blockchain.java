public class Blockchain {

    private static boolean isHashValid() {
        if (!Main.blockchain.isEmpty()) {
            for (int i = 0; i < Main.blockchain.size(); i++) {
                Block block = Main.blockchain.get(i);
                String expectedHash = Hasher.calculateHash(block.getPreviousHash(), block.getTransaction(),
                        block.getNonce());
                // has valid hash
                if (!expectedHash.equals(block.getHash())) {
                    System.out.println("Block has invalid hash.");
                    return false;
                }
            }
        } else {
            System.out.println("Blockchain is empty.");
            return true;
        }
        return true;
    }

    private static boolean minedBlock() {
        if (!Main.blockchain.isEmpty()) {
            for (int i = 0; i < Main.blockchain.size(); i++) {
                Block block = Main.blockchain.get(i);
                // block was mined/solved
                String hashTarget = new String(new char[Main.difficulty]).replace('\0', '0');
                if (!block.getHash().substring(0, Main.difficulty).equals(hashTarget)) {
                    System.out.println("Block wasn't mined.");
                    return false;
                }
            }
        } else {
            System.out.println("Blockchain is empty.");
            return true;
        }
        return true;
    }

    private static boolean comparePreviousHash() {
        if (!Main.blockchain.isEmpty()) {
            for (int i = 0; i < Main.blockchain.size(); i++) {
                Block block = Main.blockchain.get(i);
                // for every block except the first compare previousHash
                if (i > 0) {
                    Block previousBlock = Main.blockchain.get(i - 1);
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

    public static boolean isBlockchainValid() {
       return isHashValid() && minedBlock() && comparePreviousHash();
    }
}