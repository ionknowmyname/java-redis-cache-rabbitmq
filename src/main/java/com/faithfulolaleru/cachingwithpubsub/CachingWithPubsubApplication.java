package com.faithfulolaleru.cachingwithpubsub;

import ch.qos.logback.core.spi.LifeCycle;
import com.faithfulolaleru.cachingwithpubsub.Client.ClientEntity;
import com.faithfulolaleru.cachingwithpubsub.Client.ClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCaching
@AllArgsConstructor
@Slf4j
public class CachingWithPubsubApplication implements CommandLineRunner, LifeCycle {
    
    private final ClientRepository clientRepository;

//    private static ApplicationContext context;

	public static void main(String[] args) {
//        System.setProperty("server.servlet.context-path", "/");
		SpringApplication.run(CachingWithPubsubApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        clientRepository.deleteAll();

        List<ClientEntity> toSave = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            toSave.add(ClientEntity.builder()
                    .name("testing-" + i).isActive(true).build());
        }

        clientRepository.saveAll(toSave);
    }

    @Override
    public void start() {
        log.info("Application Started");
    }

    @Override
    public void stop() {
        log.info("Application Stopped");
        clientRepository.deleteAll();  // empty mongodb each time application stops
        log.info("Mongo contains: ", clientRepository.count());
    }

    @Override
    public boolean isStarted() {
        return true;
    }
}
