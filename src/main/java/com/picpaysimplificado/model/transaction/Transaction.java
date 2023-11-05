package com.picpaysimplificado.model.transaction;

import com.picpaysimplificado.model.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transations")
@Table(name = "tb_transation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount; // quantidade
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender; // quem enviou
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver; // quem recebeu
    private LocalDateTime timestap; // quando foi realizado a transação
}
