var salvar = document.getElementById('j_idt5:salvar');
var salvarEditar = document.getElementById('j_idt8:salvar');

if(salvar != null){
	salvar.onclick = function (e) {
	  alert('Item salvo com sucesso!');
	}
}

if(salvarEditar != null){
	salvarEditar.onclick = function (e) {
	  alert('Item Editado com sucesso!');
	}
}

