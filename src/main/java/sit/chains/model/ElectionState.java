package sit.chains.model;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class ElectionState extends BlockState {
    Election getData(Block block) {
        Object data = block.getRealData();
        if (data instanceof LinkedHashMap) {
            HashMap map = (LinkedHashMap) data;
            Election election = new Election();
            election.setVoter((String) map.get("voter"));
            election.setContest((String) map.get("contest"));
            return election;
        }
        return (Election) block.getRealData();
    }
}
