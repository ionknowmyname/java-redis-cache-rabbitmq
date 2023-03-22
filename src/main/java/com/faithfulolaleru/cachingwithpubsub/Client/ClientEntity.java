package com.faithfulolaleru.cachingwithpubsub.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity {

    @Id
    private String id;

    private String name;

    private boolean isActive;
}
