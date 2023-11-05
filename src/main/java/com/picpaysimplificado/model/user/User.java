package com.picpaysimplificado.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Users")
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName; // primeiro nome
    private String lastName; // sobrenome
    @Column(unique = true)
    private String document; // cpf ou cnpj
    @Column(unique = true)
    private String email; // e-mail
    private String password; // senha
    private BigDecimal balance; // saldo
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
