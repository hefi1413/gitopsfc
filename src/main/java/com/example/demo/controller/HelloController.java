package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.Instant;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class HelloController {

  private LocalDateTime startedAt = LocalDateTime.now();

  @GetMapping("/")
  public String hello() {
    String name = System.getenv("NAME");
    String age = System.getenv("AGE");
    String s = String.format("Hello World. My name is %s my age is %s.", name, age); ;

    return s;
  }

  @GetMapping("/configmap")
  public String loadTxt() {

    String linha = "";

    try {

      FileReader arq = new FileReader("/app/myfamily/family.txt");
      BufferedReader lerArq = new BufferedReader(arq);

      // lê a primeira linha
      linha = lerArq.readLine();

      arq.close();
    } catch (IOException e) {
        System.err.printf("Erro na abertura do arquivo: %s.\n",
          e.getMessage());
    }

    return "My family: " + linha;
  }

  @GetMapping("/secret")
  public String secret() {
    String user = System.getenv("USER");
    String password = System.getenv("PASSWORD");
    String s = String.format("User: %s. \n Password: %s.", user, password); ;

    return s;
  }

  @GetMapping("/healthz")
  public ResponseEntity<String> healthz() {

    LocalDateTime end = LocalDateTime.now();
    Duration duration = Duration.between(startedAt, end);

    long seconds = duration.getSeconds();

    ResponseEntity<String> result;

    if( seconds < 5L ) {

        // Retorna a resposta com o status 500
        result = new ResponseEntity<>("Não OK.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    else {
        result = new ResponseEntity<>("OK.", HttpStatus.OK);
    }
    return result;
  }
}