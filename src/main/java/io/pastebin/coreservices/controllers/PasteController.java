package io.pastebin.coreservices.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.pastebin.coreservices.client.KeyGeneratorServiceClient;
import io.pastebin.coreservices.model.Paste;
import io.pastebin.coreservices.services.PasteServices;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j // TODO: JJ - logger use lombok, constructor based injection
public class PasteController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasteController.class);
    private static final String DATA = "data";
    private final KeyGeneratorServiceClient keyGeneratorServiceClient;


    private final PasteServices pasteServices;


    public PasteController(KeyGeneratorServiceClient keyGeneratorServiceClient, PasteServices pasteServices) {
        this.keyGeneratorServiceClient = keyGeneratorServiceClient;
        this.pasteServices = pasteServices;
    }


    @PostMapping(path = "/save")
    public ResponseEntity<ObjectNode> savePaste(@RequestBody Paste paste, HttpServletRequest request) {
        LOGGER.info("inside save paste endpoint");
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonData = objectMapper.createObjectNode();
        String key = "";
        String clientIP = request.getRemoteAddr();
        ResponseEntity<Object> responseBody = keyGeneratorServiceClient.getGeneratedKey(clientIP);
        try {

            Map<String, String> responseMap = null;
            responseMap = objectMapper.readValue(
                    objectMapper.writeValueAsString(responseBody.getBody()),
                    new TypeReference<Map<String, String>>() {
                    }
            );
            if (!responseMap.containsKey("key")) {
                jsonData.put("message", "error in obtaining generated key");
                ResponseEntity.status(400).body(jsonData);
            }
            key = responseMap.get("key");
        } catch (Exception e) {
            e.printStackTrace();
        }
        pasteServices.savePaste(paste, key);
        jsonData.put("message", "paste saved");
        jsonData.put("pastebin url", key);
        return ResponseEntity.status(200).body(jsonData);
    }


    @PutMapping(path = "/{key}")
    public ResponseEntity<String> updatePaste(@PathVariable String key, @RequestBody Map<String, Object> jsonData) {
        LOGGER.info("inside update paste endpoint");
        Optional<Paste> p = pasteServices.isPresent(key);
        if (p.isEmpty()) {
            return ResponseEntity.status(404).body("paste not found");
        }
        for (String k : jsonData.keySet()) {
            if (k.equalsIgnoreCase(DATA)) {
                pasteServices.updatePaste(p.get(), (String) jsonData.get(DATA));
            } else {
                return ResponseEntity.status(400).body("bad request");
            }
        }

        return ResponseEntity.status(200).body("paste updated");

    }

    @GetMapping(path = "/{key}")
    public Paste getPaste(@PathVariable String key) {
        LOGGER.info("inside get endpoint");
        Optional<Paste> p = pasteServices.getPaste(key);
        return p.orElse(null);
    }

    @GetMapping(path = "/all")
    public List<Paste> getAllPastes() {
        return pasteServices.getAllPastes();
    }


}
