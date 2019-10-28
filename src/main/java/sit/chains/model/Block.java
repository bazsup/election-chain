package sit.chains.model;

import sit.chains.StringUtils;

import java.util.Date;

public class Block {

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public Object getData() {
        return blockState.getData(this);
    }

    Object getRealData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private String previousHash;
    private String hash;
    private Object data;
    private long timestamp;
    private static final BlockState DEFAULT_STATE = new ElectionState();
    private BlockState blockState;

    public Block() {
        this.blockState = DEFAULT_STATE;
    }

    public Block(Object data, String previousHash) {
        this.blockState = DEFAULT_STATE;
        this.setData(data);
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = this.calculateHash();
    }

    public String calculateHash() {
        String prepare = this.previousHash +
                        Long.toString(timestamp) +
                        this.getData();
        return StringUtils.applySha256(prepare);
    }

    public boolean isHashEqual(String hash) {
        return this.hash.equals(hash);
    }
}
