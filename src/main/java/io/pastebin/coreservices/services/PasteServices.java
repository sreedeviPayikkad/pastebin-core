package io.pastebin.coreservices.services;

import io.pastebin.coreservices.model.Paste;
import io.pastebin.coreservices.model.PasteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PasteServices {
    @Autowired
    PasteRepository pasteRepository;

    public void savePaste(Paste paste, String key) {
        paste.setKey(key);
        paste.setCreatedDate();
        paste.setExpiryDate();

        pasteRepository.save(paste);
    }

    public Optional<Paste> getPaste(String key) {
        return this.isPresent(key);
    }

    public List<Paste> getAllPastes() {
        return pasteRepository.findAll();
    }

    public Optional<Paste> isPresent(String key) {
        return pasteRepository.findById(key);
    }

    @Transactional
    public void updatePaste(Paste paste, String data) {
        paste.setData(data);

    }
}
