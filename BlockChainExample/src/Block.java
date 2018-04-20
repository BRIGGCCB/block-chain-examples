
import java.util.Date;

public class Block {

    public String hash; // holds blocks digital signature
    public String previousHash; // previous blocks hash
    private String data; //holds our data which will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.

   //variable incremented for mining to try and find a hash with a number of zeros infront depending on difficulty
    private int nonce;

    //Block Constructor.
    public Block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }

    //Calculate new hash based on blocks contents
    // apply to all parts of block you dont want tampered with previous hash/ data / timestamp
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }
}