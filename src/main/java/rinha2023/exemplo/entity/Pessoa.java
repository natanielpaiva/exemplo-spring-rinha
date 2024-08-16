package rinha2023.exemplo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pessoa {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @NotBlank
    @Size(max = 32)
    @Column(unique = true)
    private String apelido;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotNull
    private LocalDate nascimento;

    @ElementCollection
    @Size(max = 32)
    private List<@Size(max = 32) String> stack;
}