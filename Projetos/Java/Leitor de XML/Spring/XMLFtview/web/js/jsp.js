var objRequest = new XMLHttpRequest();
var xx;

function createRequest() {

    objRequest = new XMLHttpRequest();
    if (objRequest === null) {
        alert("Deu merda");
    } else {
        alert("Tudo ok");
    }

    return objRequest;
}
;



window.onload = function () {

    objRequest = createRequest();
    console.log("Resultado: " + window.name);
    console.log("ADAM");

};

function handleClick(clickedId) {
    document.getElementById('btJS').value = "xxxxxx";
}
;

function click2() {

    alert('Botão 2 clicado!' + xx);

}
;

function click3() {

    alert("Status: " + document.status);
    alert("ready? " + document.readyState);
    alert("Largura:" + document.body.width);
    alert("Altura:" + document.body.height);

}
;

function chamaJSP() {

    alert("Criando requisição");
    createRequest();
    var url = "http://localhost:8080/XMLFtview/js";
    objRequest.open("GET", url + "?param0=ADAM_PARAM1", true);

    objRequest.onreadystatechange = alert("Chegando resposta!" + objRequest.responseText);
    objRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoder");
    // objRequest.onreadystatechange = updatePage();
    /*Carrega a JSP mapeada no web.xml*/
    objRequest.send();
//    while(objRequest.readyState!==4){
//        alert("Carregando...");
//    }
    alert(objRequest.responseText);
}
;

objRequest.onreadystatechange = function () {

    if (objRequest.readyState === 4) {
        alert("Ready? " + objRequest.readyState);
        if (objRequest.status === 200) {
            alert("Status? " + objRequest.status);
            alert(objRequest.responseText);
        }
    }
};


function msgResize() {

    console.log("Largura: " + document.body.clientWidth);
    console.log("Altura " + document.body.clientHeight);
    console.log("Altura_ " + document.body.height);
    document.getElementById("divTabela").style.color='#123456';
    document.getElementById("tabela").style.width = "2000" +"px";
        document.getElementById("tabela").style.clientWidth = "2000" +"px";

}
;


//Largura da tela
//    var largura = window.innerWidth;
//    console.log("Largura da janela: " + largura);
//    //Largura da div
//    var larguraDivTabela = document.getElementById("divTabela").clientWidth;
//    console.log("Largura da div da tabela: " + larguraDivTabela);
//    //Seta a div
//    document.getElementById("divTabela").width = "1800px";
//
//    larguraDivTabela = document.getElementById("divTabela").clientWidth;
//    console.log("Nova largura da div da tabela: " + larguraDivTabela);
//    //Diferen
