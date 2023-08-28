package com.example.appwebsenai.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appwebsenai.model.AccountType;
import com.example.appwebsenai.model.ContaCorrentePF;
import com.example.appwebsenai.model.Person;

@Service
public class BancoController implements ContaCorrente {

	@Autowired
	private BancoRepository bancoRepository;

	@Autowired
	private Controller controller;

	private Long number = 0L;
	StringBuilder message = new StringBuilder();

	@Override
	public Double consultaSaldo(ContaCorrentePF conta) {
		return null;
	}

	public ContaCorrentePF criarConta(Integer personId, String type) throws Exception {
		message.setLength(0);
		ContaCorrentePF contaCorrentePF = new ContaCorrentePF();

		if (type == null) {
			message.append("\nNecessário informar o tipo da conta!");
		} else {
			switch (type) {
			case "POUPANCA":
				contaCorrentePF.setAccountType(AccountType.CONTA_POUPANCA);
				break;
			case "CORRENTE":
				contaCorrentePF.setAccountType(AccountType.CONTA_CORRENTE);
				break;
			default:
				message.append("\nTipo da conta não é suportado!");
				break;
			}
		}

		Person person = controller.findPerson(personId);

		if (person != null && contaCorrentePF.getError() == null) {
			contaCorrentePF.setPerson(person);
			contaCorrentePF.setDataAtualizacao(new Date());

			List<ContaCorrentePF> lastAccount = bancoRepository.findTopByOrderByIdDesc();
			if (!lastAccount.isEmpty()) {
				ContaCorrentePF mostRecentAccount = lastAccount.get(0);
				int lastId = mostRecentAccount.getNumeroConta().intValue();
				int newId = lastId + 1;
				contaCorrentePF.setNumeroConta(Long.valueOf(newId));
			} else {
				contaCorrentePF.setNumeroConta(Long.valueOf(1));
			}

			bancoRepository.save(contaCorrentePF);
		} else if (contaCorrentePF.getError() == null) {
			message.append("\nPessoa com ID ").append(personId).append(" informada não foi cadastrada");
		}

		if (!message.isEmpty()) {
			contaCorrentePF.setError(message.toString());
		}

		return contaCorrentePF;
	}

	public ContaCorrentePF consultaConta(Long contaDestino) {
		List<ContaCorrentePF> contas = (List<ContaCorrentePF>) bancoRepository.findAll();

		for (ContaCorrentePF cc : contas) {
			if (cc.getPerson() != null && cc.getPerson().getId().equals(contaDestino.intValue())) {
				return cc;
			}
		}
		return null;
	}

	public String depositar(Double deposito, Long contaDestino) {
		message.setLength(0);
		consultaConta(contaDestino);
		ContaCorrentePF n2 = consultaConta(contaDestino);
		if (n2.getSaldo() != null) {
			Double total = n2.getSaldo() + deposito;
			n2.setSaldo(total);
			message.append("\nVocê depositou R$").append(deposito).append(", em sua conta");
			message.append("\nVocê possui R$").append(total).append(", em sua conta");
		} else {
			n2.setSaldo(deposito);
			Double total = deposito;
			message.append("\nVocê depositou R$").append(deposito).append(", em sua conta");
			message.append("\nVocê possui R$").append(total).append(", em sua conta");
		}
		bancoRepository.save(n2);
		return String.valueOf(message);
	}

	@Override
	public String transferir(Double quantidade, Long contaOrigem, Long contaDestino) {
		message.setLength(0);
		ContaCorrentePF n3 = consultaConta(contaOrigem);
		ContaCorrentePF n4 = consultaConta(contaDestino);

		if (n3 != null && n4 != null) {
			if (n3.getSaldo() >= quantidade) {
				Double totalOrigem = n3.getSaldo() - quantidade;
				Double totalDestino = (n4.getSaldo() != null ? n4.getSaldo() : 0.0) + quantidade;
				if (n3.getAccountType() != n4.getAccountType()) {
					Double Origem = totalOrigem;
					n3.setSaldo(Origem);
				} else {
					n3.setSaldo(totalOrigem);
				}

				n4.setSaldo(totalDestino);
				bancoRepository.save(n3);
				bancoRepository.save(n4);
				message.append("\nVocê transferiu R$").append(quantidade).append(", para ")
						.append(n4.getPerson().getName());
			} else {
				message.append("\nSaldo insuficiente para a operação.");
			}
		} else {
			message.append("\nConta(s) não encontrada(s).");
		}
		return String.valueOf(message);
	}

	@Override
	public Double sacar(Double sacar, Long contaDestino) {
		message.setLength(0);
		ContaCorrentePF conta = consultaConta(contaDestino);

		if (conta != null) {
			if (sacar > 0) {
				if (conta.getSaldo() >= sacar) {
					Double novoSaldo = conta.getSaldo() - sacar;
					conta.setSaldo(novoSaldo);
					bancoRepository.save(conta);
					message.append("\nVocê sacou R$").append(sacar).append(", seu saldo atual é R$").append(novoSaldo);
				} else {
					message.append("\nSaldo insuficiente para o saque.");
				}
			} else {
				message.append("\nO valor do saque deve ser maior que zero.");
			}
		} else {
			message.append("\nConta não encontrada.");
		}
		return null;
	}

	@Override
	public Double extrato(Long contaOrigem) {
		message.setLength(0);
		ContaCorrentePF n3 = bancoRepository.findById(contaOrigem).get();
		return n3.getSaldo();
	}
}