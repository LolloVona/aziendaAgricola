/*
*** FRUTTA IN CAMPO™ 2026© - This code is protected

        Ordini js, parte che si occupa esclusivamente della parte di caricamento
                    dati degli ordini 
            

*/

async function caricaDati(){

const idUtente = sessionStorage.getItem("idUtente");
const div = document.getElementById('t');
    const response = await fetch(`http://localhost:8080/api/acquisto/${idUtente}`, 
        {
            method:"GET"
        }
    );

    const message = await response.json();

    if(response.status == 200){
        const a = message.ordini
        console.log(message);

         let righe = 
         `
            <thead>
            <tr>
                <th>Numero Fattura</th>
                <th>Cliente</th>
                <th>Data Erogazione</th>
                <th>Totale</th>
            </tr>
        </thead> `;


if(a.length <= 0){
    document.getElementById('no').style.display = "block";
}else{
        a.forEach(p => {
        righe += `
            <tr>
                <td>
                    ${p.numeroFattura}
                </td>
                <td>
                    ${p.usernameCliente}
                </td>
                <td>
                    ${p.dataErogazione}
                </td>
                <td>
                    ${p.totale}
                </td>
            </tr>
        `;
    });

    const table = document.createElement('table');
    table.classList.add("tabella-fatture");
    table.innerHTML = righe;
    div.appendChild(table);

  }
    }else{
        alert('err cod: '+response.status);
    }
}