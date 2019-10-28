package sit.chains.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import sit.chains.StringUtils;
import sit.chains.model.Block;
import sit.chains.model.Election;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BlockService {
    private static String FILE_NAME = "chain.json";

    public List<Block> getAll() throws IOException {
        List<Block> blockchain = new ObjectMapper().readValue(new File(FILE_NAME), new TypeReference<List<Block>>() {});
        return blockchain;
    }

    public Block getByHash(String hash) throws IOException {
        List<Block> blockchain = getAll();
        Block returnedBlock = null;
        for (Block block : blockchain) {
            if (block.getHash().equals(hash)) {
                returnedBlock = block;
            }
        }
        return returnedBlock;
    }

    private String getLastestHash() throws IOException {
        List<Block> blockchain = this.getAll();
        return blockchain.get(blockchain.size() - 1).getHash();
    }



    public boolean addBlock(Election election) throws IOException {
        List<Block> blockchain = this.getAll();

        String hashedVoter = StringUtils.applySha256(election.getVoter());
        election.setVoter(hashedVoter);
        Block block = new Block(election, this.getLastestHash());
        blockchain.add(block);
        return this.save(blockchain);
    }

    public boolean save(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(FILE_NAME), obj);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isChainValid() throws IOException {
        Block currentBlock;
        Block previousBlock;

        //loop through blockchain to check hashes:
        List<Block> blockchain = this.getAll();
        for (int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            boolean isValidHash = currentBlock.isHashEqual(currentBlock.calculateHash());
            if (!isValidHash){
                System.out.println(currentBlock.getHash() + "  " + currentBlock.calculateHash());
                System.out.println("current not valid");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.isHashEqual(currentBlock.getPreviousHash())) {
                System.out.println("previous is not valid");
                return false;
            }
        }
        return true;
    }

    public Boolean isHashAvailable(String hash) throws IOException {
        Block block = this.getByHash(hash);
        return block != null;
    }
}
