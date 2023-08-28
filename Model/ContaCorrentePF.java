package com.example.appwebsenai.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;

@Entity
@Table(name = "conta")
public class ContaCorrentePF{

    @Id
    @Column(name = "numero_conta")
    private Long numeroConta;
    private Double saldo;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name = "type")
    private AccountType accountType;

    
    @Column(name= "data_atualizacao")
    private Date dataAtualizacao;

    @Transient
    private String error;

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    /*public Person getPessoa() {
        return pessoa;
    }

    public void setPessoa(Person pessoa) {
        this.pessoa = pessoa;
    }*/

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

}
