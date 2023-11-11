package com.picpaysimplificado.controller;

import com.picpaysimplificado.dtos.TransactionsDto;
import com.picpaysimplificado.model.transaction.Transaction;
import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.repositories.UserRepository;
import com.picpaysimplificado.services.TransactionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("v1/")
public class UserController {
    @Autowired
    private TransactionService service;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User dados) {
        return ResponseEntity.ok().body(userRepository.save(dados));
    }

    @PostMapping("/transaction")
    public ResponseEntity<HttpStatusCode> createTransactions(@RequestBody TransactionsDto dados) throws Exception {
        service.createTransaction(dados);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("create")
    public ResponseEntity<String> createTransactions() throws IOException, InterruptedException, JSONException {
        boolean a = service.authorizedTransaction();
        if (a) {
            return ResponseEntity.ok("Deu certo");
        } else {
            return ResponseEntity.ok("Deu errado");
        }
    }

}
