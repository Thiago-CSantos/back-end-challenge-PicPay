package com.picpaysimplificado.services;

import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.model.user.UserType;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.LOGISTA) {
            throw new RuntimeException("Usuario do tipo LOGISTA não pode fazer transação!");
        }

        //verefica se o valor o Balance for menor que o valor do amount
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficente!");
        }
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public void saveUser(User entity) {
        repository.save(entity);
    }

}
