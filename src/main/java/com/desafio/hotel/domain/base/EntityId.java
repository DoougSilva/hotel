package com.desafio.hotel.domain.base;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityId {

    private UUID id;

    public static EntityId of(UUID uuid) {
        return new EntityId(uuid);
    }
}
