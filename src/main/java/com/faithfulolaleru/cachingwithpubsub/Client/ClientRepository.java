package com.faithfulolaleru.cachingwithpubsub.Client;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<ClientEntity, String> {
}
