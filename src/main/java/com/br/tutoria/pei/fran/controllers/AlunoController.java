package com.br.tutoria.pei.fran.controllers;

import com.br.tutoria.pei.fran.dtos.*;
import com.br.tutoria.pei.fran.entities.Aluno;
import com.br.tutoria.pei.fran.entities.Usuario;
import com.br.tutoria.pei.fran.repository.AlunoRepository;
import com.br.tutoria.pei.fran.repository.UsuarioRepository;
import com.br.tutoria.pei.fran.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/alunos")
public class AlunoController {

    private final AlunoService service;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping("/simple")
    public ResponseEntity<?> cadastrarAluno(@RequestBody AlunoMinDTO dto) {
        try {
            if (dto.getUsuarioId() == null)
                return ResponseEntity.badRequest().body("Erro: usuarioId é obrigatório.");

            Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Aluno aluno = new Aluno();
            aluno.setNome(dto.getNome());
            aluno.setRa(dto.getRa());
            aluno.setUsuario(usuario);

            alunoRepository.save(aluno);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{ra}")
                    .buildAndExpand(aluno.getRa())
                    .toUri();

            return ResponseEntity.created(uri).body(aluno.getRa());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Erro ao salvar aluno: " + e.getMessage());
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<AlunoMinDTO>> listarPorUsuario(@PathVariable Long id) {
        List<Aluno> alunos = alunoRepository.findByUsuarioId(id);
        List<AlunoMinDTO> dtos = alunos.stream()
                .map(a -> new AlunoMinDTO(a.getRa(), a.getNome()))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    // Outros endpoints (mantidos)
    @GetMapping("/simple")
    public ResponseEntity<?> listarAlunosPorUsuario(@RequestParam(required = false) Long usuarioId) {
        try {
            if (usuarioId == null) {
                return ResponseEntity
                        .badRequest()
                        .body("Usuário não identificado. Faça login novamente.");
            }

            List<Aluno> alunos = alunoRepository.findByUsuarioId(usuarioId);

            if (alunos.isEmpty()) {
                return ResponseEntity.ok(Collections.emptyList());
            }

            return ResponseEntity.ok(alunos.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno ao buscar alunos: " + e.getMessage());
        }
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

    @PutMapping(value = "/{ra}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long ra, @Valid @RequestBody AlunoDTO dto) {
        dto = service.update(ra, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{ra}")
    public ResponseEntity<Void> delete(@PathVariable Long ra) {
        service.delete(ra);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{ra}/participacao")
    public ResponseEntity<?> addParticipacao(
            @PathVariable Long ra,
            @Valid @RequestBody ParticipacaoDTO dto) {

        ParticipacaoDTO saved = service.addParticipacao(ra, dto);
        return ResponseEntity.ok(saved.getId());
    }

    //Mudar isso depois
    @PutMapping(value = "/{ra}/participacao")
    public ResponseEntity<ParticipacaoDTO> editParticipacao(@PathVariable Long ra, @RequestBody ParticipacaoDTO participacaoDTO) {
        participacaoDTO = service.addParticipacao(ra, participacaoDTO);
        return ResponseEntity.ok(participacaoDTO);
    }

    @GetMapping(value = "/{ra}/participacao")
    public ResponseEntity<ParticipacaoDTO> getParticipacao(@PathVariable Long ra) {
        ParticipacaoDTO dto = service.getParticipacao(ra);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/{ra}/avaliacoes")
    public ResponseEntity<AvaliacaoDTO> addAvaliacao(@PathVariable Long ra, @Valid @RequestBody AvaliacaoDTO dto) {
        dto = service.addAvaliacao(ra, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "{ra}/avaliacoes")
    public ResponseEntity<List<AvaliacaoDTO>> getAllAvaliacoes(@PathVariable Long ra) {
        List<AvaliacaoDTO> dtos = service.getAllAvaliacoes(ra);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(value = "/{ra}/ocorrencias")
    public ResponseEntity<OcorrenciaDTO> getAllOcorrencias(@PathVariable Long ra, @Valid @RequestBody OcorrenciaDTO dto) {
        OcorrenciaDTO ocorrenciaDTO = service.addOcorrencia(ra, dto);
        dto = service.addOcorrencia(ra, ocorrenciaDTO);
        return ResponseEntity.ok(dto);
    }

    //Mudar isso depois
    @PutMapping("/{ra}/ocorrencias")
    public ResponseEntity<?> addOcorrencias(@PathVariable Long ra, @Valid @RequestBody OcorrenciaDTO dto) {
        OcorrenciaDTO saved = service.addOcorrencia(ra, dto);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping(value = "/{ra}/ocorrencias")
    public ResponseEntity<OcorrenciaDTO> getOcorrencias(@PathVariable Long ra) {
        OcorrenciaDTO dto = service.getOcorrencia(ra);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/{ra}/leituras")
    public ResponseEntity<LeituraDTO> addLeitura(@PathVariable Long ra, @Valid @RequestBody LeituraDTO dto) {
        dto = service.addLeitura(ra, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{ra}/leituras/{id}")
    public ResponseEntity<?> updateLeitura(@PathVariable Long ra, @PathVariable Long id, @Valid @RequestBody LeituraDTO dto) {
        LeituraDTO update = service.updateLeitura(ra, id, dto);
        return ResponseEntity.ok(update);
    }

    @GetMapping(value = "/{ra}/leituras")
    public ResponseEntity<List<LeituraDTO>> getAllLeituras(@PathVariable Long ra) {
        List<LeituraDTO> dtos = service.getAllLeituras(ra);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping(value = "/{ra}/tutoria")
    public ResponseEntity<TutoriaDTO> addTutoria(@PathVariable Long ra, @RequestBody TutoriaDTO dto) {
        dto = service.addTutoria(ra, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(value = "/{ra}/registroAtendimentos")
    public ResponseEntity<RegistroAtendimentoDTO> addRegistroAtendimento(@PathVariable Long ra, @RequestBody RegistroAtendimentoDTO dto) {
        dto = service.addRegistroAtendimento(ra, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping(value = "/{ra}/registroAtendimentos")
    public ResponseEntity<List<RegistroAtendimentoDTO>> getRegistroAtendimento(@PathVariable Long ra) {
        List<RegistroAtendimentoDTO> dto = service.getAllRegistroAtendimentos(ra);
        return  ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/{ra}/registroAtendimentos/{id}")
    public ResponseEntity<?> updateAtendimentos(@PathVariable Long ra, @PathVariable Long id, @Valid @RequestBody RegistroAtendimentoDTO dto) {
        dto = service.updateResgistroAtendimento(ra, id, dto);
        return ResponseEntity.ok(dto);
    }
}
