const formPessoa = document.getElementById("cadastroPessoa");
const tabelaPessoa = document.getElementById("tabelaPessoas")
	.getElementsByTagName('tbody')[0];
const btnAll = document.getElementById("listarPessoas");

btnAll.addEventListener("click", listAll);

document.addEventListener("DOMContentLoaded", function() {
	listAll();
})

function listAll() {
	fetch("/all")
		.then(response => response.json())
		.then(data => {
			tabelaPessoa.innerHTML = "";
			if (data) {
				data.forEach(pessoa => {
					let row = tabelaPessoa.insertRow();
					row.insertCell(0).textContent = pessoa.id;
					row.insertCell(1).textContent = pessoa.name;
					row.insertCell(2).textContent = pessoa.sexo;

					let link = document.createElement("a");
					link.appendChild(document.createTextNode("Remover"));
					link.href = '#';
					link.addEventListener("click", function(event) {
						removePerson(event, pessoa.id, pessoa.name);
					});
					row.insertCell(3).appendChild(link);
				})
			}

		})
	setTimeout(clearInf, 5000);
};

formPessoa.addEventListener("submit", function(event) {
	event.preventDefault();
	let formDados = new FormData(formPessoa);
	let parametros = new URLSearchParams(formDados);
	fetch("/person?" + parametros.toString(), {
		method: "POST"
	}).then(response => response.json())
		.then(data => {
			document.getElementById("id").value = data.id;
			document.getElementById("inf").innerText = 'Pessoa ' + data.name + ' cadastrado com sucesso!!';
			listAll();
		})
});

removePerson = function(event, id, name) {
	event.preventDefault();

	fetch("/person?personId=" + id, {
		method: "DELETE"
	}).then()
	document.getElementById("id").value = '';
	document.getElementById("nome").value = '';
	document.getElementById("sexo").value = '';
	document.getElementById("inf").innerText = 'Pessoa ' + name + ' excluido com sucesso!!';
	setTimeout(listAll, 1000);
};

function clearInf() {
	document.getElementById("inf").innerText = '';
}