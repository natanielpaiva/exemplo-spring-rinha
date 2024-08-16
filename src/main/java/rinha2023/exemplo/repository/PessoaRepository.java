package rinha2023.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rinha2023.exemplo.entity.Pessoa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
    Page<Pessoa> findByApelidoContainingIgnoreCaseOrNomeContainingIgnoreCaseOrStackContainingIgnoreCase(String apelido, String nome, String stack, Pageable pageable);

    Optional<Pessoa> findByApelido(String apelido);
}
