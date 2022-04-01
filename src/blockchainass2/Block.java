
package blockchainass2;

import static blockchainass2.Main.blockchain;
import static blockchainass2.Main.difficulty;
import java.util.Date;


public class Block {

    
    private String version;
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String version,String previousHash, String data) {
        this.version = version;
        this.previousHash = previousHash;
        this.data = data;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    
    //Create a hash of block contents using the sha256 library...
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                 version+  previousHash 
                + Long.toString(timeStamp) + Integer.toString(nonce)
                + data
        );
        return calculatedhash;
    }
    
    
    
    //Mine Block
    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    
    
    //Check function...
    public static Boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
        }
        return true;
    }

}
