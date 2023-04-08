package com.faithfulolaleru.cachingwithpubsub.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientEntity implements Serializable {

    @Id
    private String id;

    private String name;

    @Builder.Default
    private Boolean isActive = false;
}
