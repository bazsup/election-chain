package sit.chains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.chains.model.Block;
import sit.chains.model.Election;
import sit.chains.model.Node;
import sit.chains.service.BlockService;
import sit.chains.service.NodeService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("election")
public class BlockController {

    private static BlockService blockService = new BlockService();
    private static NodeService nodeService = new NodeService();

    @GetMapping("/")
    public List<Block> getElectionChain() throws IOException {
        return blockService.getAll();
    }

    @PostMapping("/")
    public Boolean addBlock(@RequestBody Election data, @RequestParam(required = false) Boolean publish) throws IOException {
        if (publish != null && publish) {
            nodeService.boardcasatPost("election", data);
        }
        return blockService.addBlock(data);
    }

    @GetMapping("/{hash}")
    public Block getElectionByHash(@PathVariable("hash") String hash) throws IOException {
        return blockService.getByHash(hash);
    }

    @GetMapping("/{hash}/available")
    public Boolean isHashAvailable(@PathVariable("hash") String hash) throws IOException {
        return blockService.isHashAvailable(hash);
    }

    @GetMapping("/valid")
    public Boolean checkChainValidation() throws IOException {
        return blockService.isChainValid();
    }
}
