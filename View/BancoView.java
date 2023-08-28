package com.example.appwebsenai.view;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.appwebsenai.controller.BancoController;
import com.example.appwebsenai.model.AccountType;
import com.example.appwebsenai.model.ContaCorrentePF;

@RestController
public class BancoView {

	@Autowired
	private BancoController bancoController;

	@PostMapping("/criarconta")
	public ContaCorrentePF criarConta(@PathParam("personId") Integer personId, @PathParam("type") String type)
			throws Exception {
		return bancoController.criarConta(personId, type);
	}

	@GetMapping("/consultarconta")
	public ContaCorrentePF consultaConta(@PathParam("contaDestino") Long contaDestino) {
		return bancoController.consultaConta(contaDestino);
	}

	@PutMapping("/depositarconta")
	public String depositar(@PathParam("contaDestino") Long contaDestino, @PathParam("deposito") Double deposito) {
		return bancoController.depositar(deposito, contaDestino);
	}

	@PutMapping("/sacarconta")
	public Double sacar(@PathParam("contaDestino") Long contaDestino, @PathParam("sacar") Double sacar) {
		return bancoController.sacar(sacar, contaDestino);
	}

	@PutMapping("/transferirconta")
	public String transferir(@PathParam("contaOrigem") Long contaOrigem, @PathParam("contaDestino") Long contaDestino,
			@PathParam("quantidade") Double quantidade) {
		return bancoController.transferir(quantidade, contaOrigem, contaDestino);
	}

	@GetMapping("/type")
	public String listAccountType() {
		String text = AccountType.CONTA_CORRENTE + ", " + AccountType.CONTA_POUPANCA;
		return text;
	}

	@GetMapping("/extrato")
	public Double extrato(@PathParam("contaOrigem") Long contaOrigem) {
		return bancoController.extrato(contaOrigem);
	}
}