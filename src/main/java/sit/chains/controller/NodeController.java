package sit.chains.controller;

import org.springframework.web.bind.annotation.*;
import sit.chains.model.Node;
import sit.chains.service.NodeService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("nodes")
public class NodeController {
    private static NodeService nodeService = new NodeService();

    @GetMapping("/")
    public List<String> getNodes() throws IOException {
        return nodeService.getKnownNodes();
    }

    @PostMapping("/")
    public boolean addNode(@RequestBody @Valid Node knownNode) throws IOException {
        return nodeService.addKnownNode(knownNode.getNode());
    }
}
