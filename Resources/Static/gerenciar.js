document.addEventListener("DOMContentLoaded", function () {

    let comboPessoaDeposito = document.getElementById("pessoa_deposito");
    let comboPessoaSacar = document.getElementById("pessoa_sacar");
    let comboPessoaDestino = document.getElementById("pessoa_destino");
    let comboPessoaOrigem = document.getElementById("pessoa_origem");

    let btnSaque = document.getElementById("btnSaque");
    let btnDeposito = document.getElementById("btnDeposito");
    let btnTransferir = document.getElementById("btnTransferir");

    function preencherPessoaDeposito() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoaDeposito.appendChild(option);
                data.forEach(pessoa => {
                    let option = document.createElement("option")
                    option.value = pessoa.id;
                    option.textContent = pessoa.name;
                    comboPessoaDeposito.appendChild(option);
                })

            })
    }
    preencherPessoaDeposito();

    function preencherPessoaSacar() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoaSacar.appendChild(option);
                data.forEach(pessoaSacar => {
                    let option = document.createElement("option")
                    option.value = pessoaSacar.id;
                    option.textContent = pessoaSacar.name;
                    comboPessoaSacar.appendChild(option);
                })

            })
    }
    preencherPessoaSacar();

    function preencherPessoaDestino() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoaDestino.appendChild(option);
                data.forEach(pessoaDestino => {
                    let option = document.createElement("option")
                    option.value = pessoaDestino.id;
                    option.textContent = pessoaDestino.name;
                    comboPessoaDestino.appendChild(option);
                })

            })
    }
    preencherPessoaDestino();

    function preencherPessoaOrigem() {
        fetch("/all")
            .then(response => response.json())
            .then(data => {
                let option = document.createElement("option")
                option.value = null;
                option.textContent = "";
                comboPessoaOrigem.appendChild(option);
                data.forEach(pessoa3 => {
                    let option = document.createElement("option")
                    option.value = pessoa3.id;
                    option.textContent = pessoa3.name;
                    comboPessoaOrigem.appendChild(option);
                })

            })
    }
    preencherPessoaOrigem();

    function Saque(contaDestino, sacar) {
        let parametros = new URLSearchParams();
        parametros.set('contaDestino', contaDestino)
        parametros.set('sacar', sacar)
        fetch("/sacarconta?" + parametros.toString(), {
            method: "Put"
        })
            .then(response => response.json())
            .then(data =>{
                document.getElementById("pessoa1").textContent = data.contaDestino;
                document.getElementById("sacar").textContent = data.sacar;
            })

    }

    function Deposito(contaDestino, deposito) {
        let parametros = new URLSearchParams();
        parametros.set('contaDestino', contaDestino)
        parametros.set('deposito', deposito)
        fetch("/depositarconta?" + parametros.toString(), {
            method: "Put"
        })
            .then(response => response.json())
            .then(data =>{
                document.getElementById("pessoa").textContent = data.contaDestino;
                document.getElementById("deposito").textContent = data.deposito;
            })

    }

    function Transferir(contaOrigem, contaDestino, quantidade) {
        let parametros = new URLSearchParams();
        parametros.set('contaOrigem', contaOrigem)
        parametros.set('contaDestino', contaDestino)
        parametros.set('quantidade', quantidade)
        fetch("/transferirconta?" + parametros.toString(), {
            method: "Put"
        })
            .then(response => response.json())
            .then (data => {
                document.getElementById("pessoa_origem").textContent = data.contaOrigem;
                document.getElementById("pessoa_destino").textContent = data.contaDestino;
                document.getElementById("quantidade").textContent = data.quantidade;
            })
    };

    btnSaque.addEventListener("click", function () {
        let contaDestino = comboPessoaSacar.value;
        let sacar = parseFloat(document.getElementById("sacar").value);
        Saque(contaDestino, sacar);
    });

    btnDeposito.addEventListener("click", function () {
        let contaDestino = comboPessoaDeposito.value;
        let deposito = parseFloat(document.getElementById("deposito").value);
        Deposito(contaDestino, deposito);
    });

    btnTransferir.addEventListener("click", function () {
        let contaOrigem = comboPessoaOrigem.value;
        let contaDestino = comboPessoaDestino.value;
        let quantidade = parseFloat(document.getElementById("quantidade").value);
        Transferir(contaOrigem, contaDestino, quantidade);
    });
});