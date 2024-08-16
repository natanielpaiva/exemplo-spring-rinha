package rinha2023.exemplo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rinha2023.exemplo.entity.Pessoa;
import rinha2023.exemplo.repository.PessoaRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @PostMapping
    public ResponseEntity<?> criarPessoa(@Valid @RequestBody Pessoa pessoa) {
        if (pessoaRepository.findByApelido(pessoa.getApelido()).isPresent()) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Apelido já existe");
        }
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return ResponseEntity.created(URI.create("/pessoas/" + savedPessoa.getId())).body(savedPessoa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> obterPessoa(@PathVariable UUID id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada");
        }
    }

    @GetMapping
    public ResponseEntity<?> buscarPessoas(@RequestParam(required = false) String t) {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("nome").ascending());

        Page<Pessoa> pessoas;
        if (t == null || t.isBlank()) {
            return ResponseEntity.badRequest().body("");
        } else {
            pessoas = pessoaRepository.findByApelidoContainingIgnoreCaseOrNomeContainingIgnoreCaseOrStackContainingIgnoreCase(t, t, t, pageable);
        }
        return ResponseEntity.ok(pessoas.getContent());
    }

    @GetMapping("/contagem-pessoas")
    public ResponseEntity<String> contagemPessoas() {
        long count = pessoaRepository.count();
        return ResponseEntity.ok(String.valueOf(count));
    }
}