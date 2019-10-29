package sit.chains.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NodeService {
    private static String FILE_NAME = "nodes.json";
    private RestTemplate restTemplate = new RestTemplate();

    public void boardcasatPost(String endpoint, Object data) throws IOException {
        this.getKnownNodes().parallelStream().forEach(node -> restTemplate.postForLocation(node + "/" + endpoint + "/", data));
    }

    public List<String> getKnownNodes() throws IOException {
        return new ObjectMapper().readValue(new File(FILE_NAME), new TypeReference<List<String>>() {});
    }

    public boolean addKnownNode(String node) throws IOException {
        List<String> nodes = getKnownNodes();
        nodes.add(node);
        return this.save(nodes);

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
}
