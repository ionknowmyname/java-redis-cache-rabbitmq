package com.faithfulolaleru.cachingwithpubsub.Client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public record ClientService(ClientRepository clientRepository) {

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity createClient(ClientEntity entity) {

        return clientRepository.save(ClientEntity.builder()
                .name(entity.getName())
                .isActive(entity.getIsActive())
                .build());
    }

    public String deleteClientById(String clientId) {
        String toReturn;

        try{
            clientRepository.deleteById(clientId);
            toReturn = "Client with id successfully deleted";
        } catch(Exception ex) {
            log.error("error while deleting client by id --> ", ex.getMessage());
            toReturn = "Client with id failed to delete";
        }

        return toReturn;
    }
}
