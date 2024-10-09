package com.desafio.hotel.controller;

import com.desafio.hotel.domain.base.EntityId;
import com.desafio.hotel.domain.hospede.HospedeDTO;
import com.desafio.hotel.service.HospedeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path="/hospede")
@RequiredArgsConstructor
public class HospedeController {

    private final HospedeService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityId create(@RequestBody HospedeDTO hospedeDTO) {
        return EntityId.of(service.create(hospedeDTO).getId());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HospedeDTO findById(@PathVariable("id") UUID uuid) {
        return service.findById(uuid);
    }

    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.OK)
    public Page<HospedeDTO> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest pageRequeste = PageRequest.of(page, size);
        return service.findAll(pageRequeste);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public EntityId update(@RequestBody HospedeDTO hospedeDTO) {
        return EntityId.of(service.update(hospedeDTO).getId());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable("id") UUID uuid) {
        service.delete(uuid);
    }

    @GetMapping("check-in-expered")
    @ResponseStatus(HttpStatus.OK)
    public Page<HospedeDTO> findAllByCheckInExpered(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size){
        PageRequest pageRequeste = PageRequest.of(page, size);
        return service.findAllByCheckInExpered(pageRequeste);
    }
}
