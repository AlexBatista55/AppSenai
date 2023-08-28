const btnConta = document.getElementById("btnConta");
let tipo_conta = document.getElementById("tipo_conta");
let comboPessoa = document.getElementById("pessoa");

document.addEventListener("DOMContentLoaded", function() {
	function preencherPessoa() {
		fetch("/all")
			.then(response => response.json())
			.then(data => {
				let option = document.createElement("option");
				option.value = null;
				option.textContent = "";
				comboPessoa.appendChild(option);
				data.forEach(pessoa => {
					let option = document.createElement("option");
					option.value = pessoa.id;
					option.textContent = pessoa.name;
					comboPessoa.appendChild(option);
				})

			})
	}

	preencherPessoa();


	var selectedIndex = '';
	tipo_conta.selectedIndex = selectedIndex;
});

function criarConta(personId, type) {
	let parametros = new URLSearchParams();
	parametros.set('personId', personId);
	parametros.set('type', type);

	fetch("/criarconta?" + parametros.toString(), {
		method: "POST"
	}).then(response => response.json())
		.then(data => {
			document.getElementById("tipo_conta").selectedIndex = data.type;
			document.getElementById("numero_conta").value = data.numeroConta;
		})
}
btnConta.addEventListener("click", function(event) {
	event.preventDefault();
	let personId = comboPessoa.value;
	let type = tipo_conta.value;
	criarConta(personId, type);
});