    /*
    *** FRUTTA IN CAMPO™ 2026© - This code is protected

            •Questo script.js serve per il codice js per gestire,
                tutte le funzioni utili all'accesso e/o registrazione degli utenti.
                

    */

    async function caricaProdotti(){
        const response = await fetch("http://localhost:8080/api/prodotto",
            {
                method:"GET",
                headers:{
                    "Content-Type":"application/json"
                }
            }
        );
        
        const message = await response.json();
        mostraProdotti(message.prodotti);
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

    //Aggiungo al div CARD
            card.appendChild(img);
            card.appendChild(titolo);
            card.appendChild(prezzo);

            //Aggiungo al body
            div.appendChild(card);

        });
    }


    async function cercaProdotto(){
    const div = document.getElementById('container-prodotti-cercati');
    div.innerHTML = "";
    let nomeProdotto = String(document.getElementById('nome').value);
    nomeProdotto = nomeProdotto.toLowerCase();
        const response = await fetch(`http://localhost:8080/api/prodotto/${nomeProdotto}`,
            {
                method:"GET",
                headers:
                {
                    "Content-Type":"application/json"
                }
            }
        );

        const message = await response.json();
        document.getElementById('container-prodotti').style.display = "none";
        if(response.status != 200){
            alert('nessun prodotto trovato');
        }else if(response.status == 200){
            //mostra prodotti cercati
            const prodotto = message.prodotto;

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

            //Aggiungo al div CARD
                    card.appendChild(img);
                    card.appendChild(titolo);
                    card.appendChild(prezzo);

                    //Aggiungo al body
                    div.appendChild(card);
        }
    }