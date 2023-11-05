package com.picpaysimplificado.services;

import com.picpaysimplificado.dtos.TransactionsDto;
import com.picpaysimplificado.model.transaction.Transaction;
import com.picpaysimplificado.model.user.User;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserService userService;


    public void createTransaction(TransactionsDto transactionsDto) throws Exception {
        User sender = userService.findById(transactionsDto.senderId());
        User receiver = userService.findById(transactionsDto.receiverId());

        userService.validateTransaction(sender, transactionsDto.amount());

        if (!authorizedTransaction()) {
            throw new Exception("Transação não autorizada");
        }

        //Criar uma nova transação
        Transaction newTransaction = new Transaction();

        newTransaction.setAmount(transactionsDto.amount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestap(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionsDto.amount())); // subtraindo do saldo da conta de quem esta enviando
        receiver.setBalance(receiver.getBalance().add(transactionsDto.amount())); // somando o saldo da conta

        //persistindo os dados no banco
        repository.save(newTransaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    public boolean authorizedTransaction() throws IOException, InterruptedException, JSONException {
        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("https://run.mocky.io/v3/ee1a2cd7-de27-4bc9-bea3-a08935cf4e9c"))
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(3)).build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(httpResponse.body());

        System.out.println(jsonObject.getString("service"));
        return true;
    }
}
