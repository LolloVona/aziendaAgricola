/*
**** FRUTTA IN CAMPO™ 2026© - This code is protected

        •Questo script js serve per gestire tutte quelle funzioni che sono
            ad uso esclusivo dell'amministratore, ovvero dopo aver effettuato il log-in.

            In questo caso, la gestione dei prodotti, con le funzioni di modifica, eliminazione e aggiunta.

*/

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("close-add-product").addEventListener("click", () =>{
        document.getElementById("add-product-overlay").classList.remove("show");
    });
});




async function prodotti(){
    //creare la GET per chiedere tutti i prodotti:
    const response = await fetch("http://localhost:8080/api/prodotto",
        {
            method: "GET"
        }
    );

    const data = await response.json();
    mostraProdotti(data.prodotti);
}

function mostraProdotti(a){

    const div = document.getElementById('container-prodotti');

    a.forEach(prodotto => {
        //creo la carta del prodotto
        const card = document.createElement('div');

        //aggiungo la classe per lo stile
        card.classList.add('card-prodotto');

        //Creo img:
        const img = document.createElement('img');
        img.src = "../../assets/img/"+prodotto.nome+".jpg";
        img.alt = prodotto.nome;

        //Creo titolo:
        const titolo = document.createElement('h3');
        titolo.textContent = prodotto.nome;

        //Creo il prezzo
        const prezzo = document.createElement("p");
        prezzo.textContent = prodotto.prezzo + "€";

        //Creo il bottone modifica
        const bottoneModifica = document.createElement("button");
        bottoneModifica.innerHTML = '<i class="fa-solid fa-pen"></i> Modifica';
        bottoneModifica.classList.add("bottone-modifica");
        
        //gestisco il click:
        bottoneModifica.addEventListener("click", () =>
        {
            apriModifica(); //TODO
        });

        //Creo bottone elimina:
        const bottoneElimina = document.createElement("button");
        bottoneElimina.innerHTML = '<i class="fa-solid fa-trash"></i> Elimina';
        bottoneElimina.classList.add("bottone-elimina");

        //gestisco il click:
        bottoneElimina.addEventListener("click", () =>
        {
            apriElimina(); //TODO
        });


        const areaAdmin = document.createElement("div");
        areaAdmin.classList.add("area-admin");

        areaAdmin.appendChild(bottoneModifica);
        areaAdmin.appendChild(bottoneElimina);        
 
/////////////////////////////////////////////////////////////////////////////
 

//Aggiungo al div CARD
        card.appendChild(img);
        card.appendChild(titolo);
        card.appendChild(prezzo);
        card.appendChild(areaAdmin);
        //Aggiungo al body
        div.appendChild(card);

    });

      //aggiungo card finale aggiungi prodotto:
    const aggiungiProdotto = document.createElement("div");
    const plus = document.createElement("div");
    const testo = document.createElement("h3");
    testo.textContent = "Aggiungi Prodotto";

    aggiungiProdotto.appendChild(testo);

    aggiungiProdotto.classList.add("card-aggiungi");
    plus.classList.add("plus-aggiungi");


            aggiungiProdotto.addEventListener("click", () => {
            addProdotto();
            });


    aggiungiProdotto.appendChild(plus);
    div.appendChild(aggiungiProdotto);
}  


function addProdotto(){
    document.getElementById("add-product-overlay").classList.add("show");
}

async function salvaProdotto(){

const id = sessionStorage.getItem("idUtente");

    const response = await fetch("http://localhost:8080/api/prodotto", 
       {
        method:"POST",
        headers:{
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            idUtente : id,
            nome:  document.getElementById('nome-prodotto').value,
            prezzo: document.getElementById('prezzo-prodotto').value,
            magazzino: document.getElementById('quantita-prodotto').value
        })
       }
    );

    const message = response.json();

    switch(response.status){
        case 201:
                document.getElementById("yes").style.display = "block";
                document.getElementById('err').style.display = "none";
                break;
        case 400:
                document.getElementById('err').style.display = "block";
                 document.getElementById('err').innerText = "codErrore"+response.status;
                document.getElementById("yes").style.display = "none";
                break;
        case 403:
                document.getElementById('err').style.display = "block";
                 document.getElementById('err').innerText = "codErrore"+response.status;
                document.getElementById("yes").style.display = "none";
                break;
    }
}



