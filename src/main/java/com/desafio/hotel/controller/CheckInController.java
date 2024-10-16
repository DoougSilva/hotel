package com.desafio.hotel.controller;

import com.desafio.hotel.domain.base.EntityId;
import com.desafio.hotel.domain.checkin.CheckInDTO;
import com.desafio.hotel.service.CheckInService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/check-in")
@RequiredArgsConstructor
public class CheckInController {

    private final CheckInService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityId create(@RequestBody @Valid CheckInDTO checkInDTO) {
        return EntityId.of(service.create(checkInDTO).getId());
    }
}
