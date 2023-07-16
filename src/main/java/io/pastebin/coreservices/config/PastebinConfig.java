package io.pastebin.coreservices.config;

import io.pastebin.coreservices.model.Paste;
import io.pastebin.coreservices.model.PasteRepository;
import io.pastebin.coreservices.utilities.TimeUnit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class PastebinConfig {
    @Bean
    CommandLineRunner commandLineRunner(PasteRepository pasteRepository) {
        return args -> {

            Paste paste1 = new Paste("101", "sample content1", LocalDateTime.now(),LocalDateTime.now().plusMinutes(1000), TimeUnit.MINUTES, 20);
            Paste paste2 = new Paste("102", "sample content2",LocalDateTime.now(),LocalDateTime.now().plusMinutes(5000), TimeUnit.WEEKS, 2 );
            pasteRepository.saveAll(List.of(paste1, paste2));
        };
    }
}
