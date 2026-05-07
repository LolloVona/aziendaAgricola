/*
*** FRUTTA IN CAMPO™ 2026© - This code is protected

        •Questo script js serve per gestire tutte quelle funzioni che sono,
            INTERNE, ovvero dopo aver effettuato il log-in.

*/

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

        btnCarrello.addEventListener("click", () => {
            alert(
                "Hai aggiunto " +
                inputQuantita.value +
                " x " +
                prodotto.nome
            );
        });

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