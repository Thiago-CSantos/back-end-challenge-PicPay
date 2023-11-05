package com.picpaysimplificado.controller;

import com.picpaysimplificado.services.TransactionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("v1/")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("create")
    public ResponseEntity<String> createTransactions() throws IOException, InterruptedException, JSONException {
        boolean a = service.authorizedTransaction();
        if (a){
            return ResponseEntity.ok("Deu certo");
        }
        else {
            return ResponseEntity.ok("Deu errado");
        }
    }

}
