package com.faithfulolaleru.cachingwithpubsub.Client;

import com.faithfulolaleru.cachingwithpubsub.response.AppResponse;
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

    public static final String MY_KEY = "clientList";
    public static final String MY_KEY2 = "singlePostClient";

    @Cacheable(value = "clients", key = "#root.target.MY_KEY")
    @GetMapping("/all")
    public AppResponse<List<ClientEntity>> getAllClients() {
        return AppResponse.<List<ClientEntity>>builder().data(clientService.getAllClients()).build();
    }

    @CachePut(value = "clients", key = "#root.target.MY_KEY2") // #root.methodName takes the method name as key
    @PostMapping("/")
    public AppResponse<ClientEntity> createClient(@RequestBody ClientEntity entity) {
        ClientEntity response = clientService.createClient(entity);

        return AppResponse.<ClientEntity>builder().data(response).build();
    }

    @CacheEvict(value = "clients", allEntries=true)
    @DeleteMapping("/{clientId}")
    public AppResponse<String> deleteClientById(@PathVariable String clientId) {
        return AppResponse.<String>builder().data(clientService.deleteClientById(clientId)).build();
    }
}
