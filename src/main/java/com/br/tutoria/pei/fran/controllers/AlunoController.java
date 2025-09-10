package com.br.tutoria.pei.fran.controllers;

import com.br.tutoria.pei.fran.dtos.AlunoDTO;
import com.br.tutoria.pei.fran.dtos.AlunoMinDTO;
import com.br.tutoria.pei.fran.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    private final AlunoService service;

    @Autowired
    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> insert(@RequestBody AlunoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getRa()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AlunoMinDTO>> getAllNamesAndImages() {
        List<AlunoMinDTO> result = service.getAllNames();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{ra}")
    public ResponseEntity<AlunoDTO> getAlunoByRa(@PathVariable Long ra) {
        AlunoDTO result = service.getAlunosByRa(ra);
        return ResponseEntity.ok(result);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AlunoDTO> insert(@PathVariable Long id, @RequestBody AlunoDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{ra}")
    public ResponseEntity<Void> delete(@PathVariable Long ra) {
        service.delete(ra);
        return ResponseEntity.noContent().build();
    }
}
