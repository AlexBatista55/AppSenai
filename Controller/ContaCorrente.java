package com.example.appwebsenai.controller;

import com.example.appwebsenai.model.ContaCorrentePF;

public interface ContaCorrente {

    Double sacar(Double quantidade, Long contaDestino);

    String depositar(Double quantidade, Long contaDestino);

    String transferir(Double quantidade, Long contaOrigem, Long contaDestino);

    Double consultaSaldo(ContaCorrentePF conta);

    Double extrato(Long contaOrigem);

}