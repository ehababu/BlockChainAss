
package blockchainass2;

import static blockchainass2.Block.isChainValid;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 2; 

    public static void main(String[] args) {

        
        //Genesis Block....
        blockchain.add(new Block("1", "Hi im the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("2", "Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("3", "Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).mineBlock(difficulty);

        blockchain.add(new Block("4", "Hey im the Four block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block ... ");
        blockchain.get(3).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        //To print the contents of the blockchain the gson library was used...
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
        
        
        
        //To write the contents of the blockchain into a text file...
   
        try {
            FileWriter myWriter = new FileWriter("BlockChain.txt");
            myWriter.write(blockchainJson);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
