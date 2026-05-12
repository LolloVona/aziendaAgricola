/*
**** FRUTTA IN CAMPO™ 2026© - This code is protected

        •Questo script js serve per gestire tutte quelle funzioni che sono,
            INTERNE, ovvero dopo aver effettuato il log-in.

*/
//MODIFICHE DA EFFETTUARE SU IF  A RIGA 72 E 93
var idUtente = sessionStorage.getItem("idUtente");
console.log(idUtente);
var carrello = []; //variabile temporanea.

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
        img.src = "../assets/img/"+prodotto.nome+".jpg";
        img.alt = prodotto.nome;

        //Creo titolo:
        const titolo = document.createElement('h3');
        titolo.textContent = prodotto.nome;

        //Creo il prezzo
        const prezzo = document.createElement("p");
        prezzo.textContent = prodotto.prezzo + "€";

        //Creo IL DIV DOVE GESTISCO LA QUANTITA':
        const rigaQuantita = document.createElement("div");
        rigaQuantita.classList.add("riga-quantita");
            //Creo il bottone -
            const btnMeno = document.createElement("button");
            btnMeno.textContent = "-";
            //creo input
            const inputQuantita = document.createElement("input");
            inputQuantita.type = "number";
            inputQuantita.value = 1;
            inputQuantita.min = 1;
            inputQuantita.max = prodotto.disponibilita;
            //Creo bottone +
            const btnPiu = document.createElement("button");
            btnPiu.textContent = "+";
        //GESTISCO + e -
        btnMeno.addEventListener("click", () => {
            if(inputQuantita.value > 1){
                inputQuantita.value--;
            }
        });

        btnPiu.addEventListener("click", () => {
            if(Number(inputQuantita.value) < prodotto.disponibilita){
                inputQuantita.value++;
            }
        });

        //Aggiungo al div
        rigaQuantita.appendChild(btnMeno);
        rigaQuantita.appendChild(inputQuantita);
        rigaQuantita.appendChild(btnPiu);

        //Creo aggiungi al carello
        const btnCarrello = document.createElement("button");
        btnCarrello.classList.add("btn-carrello");
        btnCarrello.textContent = "Aggiungi al carrello";



    /////////////////////////////////////////////////////////////////////////    
        //QUANDO CLICCO SUL PULSANTE:
        btnCarrello.addEventListener("click", () => {
           const quantita = Number(inputQuantita.value);
           
           if(quantita <= prodotto.disponibilita   && quantita>0){//MODIFICA

                const prodottoNelCarrello = carrello.find(p => p.nome === prodotto.nome) //find per cercare ed non creare eventuali copioni [scorre il vettore]

                if(prodottoNelCarrello){ //se entra, CI SONO DOPPIONI
                    prodottoNelCarrello.quantita += quantita; //aggiorno SOLO la quantità
                }else{
                    carrello.push({
                        nome: prodotto.nome,
                        quantita:quantita,
                        prezzo: prodotto.prezzo,
                        img: "../assets/img/"+prodotto.nome+".jpg"
                    })
                }

                aggiornaMiniCarrello(); //carrellino in alto

                //MOSTRO AGGIUNTA
            btnCarrello.textContent = "Aggiunto ✓";
            btnCarrello.classList.add("added");

            setTimeout(function(){
                btnCarrello.textContent = "Aggiungi al carrello";
                btnCarrello.classList.remove("added");
            },1500);
           }else
                    alert('quantità non disponibile');
        });
/////////////////////////////////////////////////////////////////////////////
 

//Aggiungo al div CARD
        card.appendChild(img);
        card.appendChild(titolo);
        card.appendChild(prezzo);
        card.appendChild(rigaQuantita);
        card.appendChild(btnCarrello);
        //Aggiungo al body
        div.appendChild(card);

    });
}  

function aggiornaMiniCarrello() {
    let totalePezzi = 0;
    let totalePrezzo = 0;

    carrello.forEach(p => {
        totalePezzi += p.quantita;
        totalePrezzo += p.quantita * p.prezzo;
    });

    document.getElementById("cart-count").textContent = totalePezzi;
    document.getElementById("cart-total").textContent = totalePrezzo.toFixed(2) + " €";
}



//PER APRIRE IL CHECKOUT *************************************************************
document.addEventListener("DOMContentLoaded", () => {

    document.getElementById("ca").addEventListener("click", apriCheckout);

    document.getElementById("close-checkout").addEventListener("click", () => {
        document.getElementById("checkout-overlay").classList.remove("show");
    });

});
//*************************************************************************************

//FAI LA RICHIESTA AL SERVER PER IL CHECKOUT:
async function apriCheckout() {

    if (carrello.length === 0) {
        alert("Il carrello è vuoto");
        return;
    }

    const bodyRequest = {
        idUtente: idUtente,
        prodotti: carrello.map(p => ({
            nome: p.nome,
            quantita: p.quantita
        }))
    };

    const response = await fetch("http://localhost:8080/api/acquisto", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(bodyRequest)
    });

    if (!response.ok) {
        alert("Errore nel calcolo del riepilogo");
        return;
    }

    const data = await response.json();

    mostraRecapCheckout(data.prezzo, data.dataErogazione);
}


////////////////////CREO LA TABELLA DEL CHECKOUT/////////////////////
function mostraRecapCheckout(totale, dataErogazione) {

    const checkoutContent = document.getElementById("checkout-content");

    let righe = "";

    carrello.forEach(p => {
        righe += `
            <tr>
                <td>
                    <div class="checkout-product">
                        <img src="${p.img}" alt="${p.nome}">
                        <span>${p.nome}</span>
                    </div>
                </td>
                <td>${p.quantita}</td>
                <td>${Number(p.prezzo).toFixed(2)} €</td>
                <td><button onclick="rimuovi('${p.nome}')" class="btn-rimuovi">-</button></td>
            </tr>
        `;
    });

    checkoutContent.innerHTML = `
        <table class="checkout-table">
            <thead>
                <tr>
                    <th>Prodotto</th>
                    <th>Quantità</th>
                    <th>Prezzo</th>
                    <th>Rimuovi</th>
                </tr>
            </thead>
            <tbody>
                ${righe}
            </tbody>
        </table>

        <div class="checkout-summary">
            <p><strong>Totale:</strong> ${Number(totale).toFixed(2)} €</p>
            <p><strong>Data erogazione:</strong> ${formattaData(dataErogazione)}</p>
        </div>
    `;

    document.getElementById("checkout-overlay").classList.add("show");
}


//formatto la data da db type en, a IT:
function formattaData(data) {
    return new Date(data).toLocaleDateString("it-IT");
}


/////////////RIMUOVI PRODOTTO DAL CARRELLO////////////////////////////
function rimuovi(nomeProdotto){
    const prodotto = carrello.find(p => p.nome === nomeProdotto);

    if(prodotto){
        prodotto.quantita--;
        if(prodotto.quantita<1){
            const indice = carrello.indexOf(prodotto);

            carrello.splice(indice, 1); //elimina
        }
        if(carrello.length == 0){
            //chiude:
            document.getElementById("checkout-overlay").classList.remove("show");
        }else
            apriCheckout();
        aggiornaMiniCarrello();
    }
}


/////////////////CONFERMA ORDINE//////////////////////////
async function confermaOrdine(){
    const bodyRequest = {
        idUtente: idUtente,
        prodotti: carrello.map(p => ({
            nome: p.nome,
            quantita: p.quantita
        }))
    };

    const response = await fetch("http://localhost:8080/api/acquisto/conferma",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify(bodyRequest)
    });

    switch(response.status){
        case 200:   
                    window.location.href = "paid.html";
                    break;
        case 400:
                    alert('Error 400');
                    break;
    }
}
