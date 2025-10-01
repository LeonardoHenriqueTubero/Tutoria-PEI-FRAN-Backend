package com.br.tutoria.pei.fran.service;

import com.br.tutoria.pei.fran.dtos.UsuarioDTO;
import com.br.tutoria.pei.fran.entities.Papel;
import com.br.tutoria.pei.fran.entities.Usuario;
import com.br.tutoria.pei.fran.projections.UserDetailsProjection;
import com.br.tutoria.pei.fran.repository.PapelRepository;
import com.br.tutoria.pei.fran.repository.UsuarioRepository;
import com.br.tutoria.pei.fran.service.exceptions.EntityAlreadyExistingException;
import com.br.tutoria.pei.fran.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final PapelRepository papelRepository;

    @Autowired
    public UsuarioService(UsuarioRepository repository, PasswordEncoder encoder, PapelRepository papelRepository) {
        this.repository = repository;
        this.encoder = encoder;
        this.papelRepository = papelRepository;
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        List<Usuario> list = repository.findAll();
        return list.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO insert(UsuarioDTO dto) {
        if (repository.existsByCpf((dto.getCpf()))){
            throw new EntityAlreadyExistingException("Entidade já criada!");
        }
        Usuario novo = new Usuario();
        dtoToEntity(novo, dto);
        Papel papel = papelRepository.findByAuthority("ROLE_PROFESSOR").orElseThrow(()
                -> new ResourceNotFoundException("Recurso nao encontrado"));
        novo.addPapel(papel);
        novo = repository.save(novo);
        return new UsuarioDTO(novo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUsuarioAndPapeisByNome(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        Usuario user = new Usuario();
        user.setNome(username);
        user.setCpf(result.getFirst().getCpf());
        for (UserDetailsProjection u : result) {
            user.addPapel(new Papel(u.getRoleId(), u.getAuthority()));
        }
        return user;
    }

    private void dtoToEntity(Usuario entity, UsuarioDTO dto) {
        entity.setCpf(encoder.encode(dto.getCpf()));
        entity.setNome(dto.getNome());
    }
}