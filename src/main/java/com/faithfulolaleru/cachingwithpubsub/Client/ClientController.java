package com.faithfulolaleru.cachingwithpubsub.Client;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Cacheable(value = "clients", key = "clientsKey")
    @GetMapping("/all")
    public ResponseEntity<List<ClientEntity>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @CachePut(value = "clients", key = "clientsKey") // #response.id
    @PostMapping("/")
    public ResponseEntity<ClientEntity> createClient(@RequestBody ClientEntity entity) {
        ClientEntity response = clientService.createClient(entity);

        return ResponseEntity.ok(response);
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClientById(@PathVariable String clientId) {
        return ResponseEntity.ok(clientService.deleteClientById(clientId));
    }
}
