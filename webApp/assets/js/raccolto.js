/*
**** FRUTTA IN CAMPO™ 2026© - This code is protected

        •Questo script js serve per gestire tutte quelle funzioni che sono
            ad uso esclusivo dell'aggiunta ai raccolti.

*/

document.addEventListener("DOMContentLoaded", caricaProdotti());

async function caricaProdotti(){
    const response = await fetch("http://localhost:8080/api/prodotto",
        {
            method:"GET",
            headers:{
                "Content-Type":"application/json"
            }
        }
    );

    const message =  await response.json();

    if(response.status==200){
        mostraProdotti(message.prodotti);
    }else{
        alert('err');
    }
}


function mostraProdotti(response){

    const menu = document.getElementById('menu');

    response.forEach(a => {
        const option = document.createElement('option');

        option.textContent = a.nome;
        option.value = a.nome;

        menu.appendChild(option);
    });
}

async function aggiungiRaccolto(){
    const id = sessionStorage.getItem("idUtente");
    const response = await fetch("http://localhost:8080/api/raccolto", 
        {
            method:"POST",
            headers:
            {
                "Content-Type":"application/json"
            },
            body:JSON.stringify(
                {
                    totale: document.getElementById('kg-raccolto').value,
                    data: document.getElementById('data-raccolto').value,
                    nome: document.getElementById('menu').value,
                    idUtente: id
                }
            )
        }
    );

    if(response.status == 204){
        document.getElementById('yes').style.display = "block";
    }
}