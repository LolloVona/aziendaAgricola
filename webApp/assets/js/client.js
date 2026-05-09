/*
*** FRUTTA IN CAMPO™ 2026© - This code is protected

        •Questo script.js serve per il codice js per gestire,
            tutte le funzioni utili all'accesso e/o registrazione degli utenti.
            

*/



async function accedi(){
    var us = document.getElementById('us').value;
    var pw = document.getElementById('pw').value;

    //devo mandare i dati al server
    const response =await fetch("http://localhost:8080/api/utente/accesso",
        {   method: "POST",
            headers:{
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: us,
                password: pw
            })
    });

    //verifico che il server mi abbia risposto:
    const message = await response.text();

    switch(response.status){
        case 201:
                window.location.href = "client.html";
                break;
        case 400:
                document.getElementById('err').style.display = "block";
                document.getElementById('err').innerText = "Error server code: 400";
                break;
        case 401:
                document.getElementById('cred').style.display = "block";
                break;
        default:
                alert('entrato in default case'+ 'COD: '+response.status);
                break;
    }
}

async function registrati(){
    var nome = document.getElementById('nome').value;
    var user = document.getElementById('user').value;
    var pw = document.getElementById('pass').value;

    const response = await fetch("http://localhost:8080/api/utente/registrazione",
        {method: "POST",
            headers:{
                "Content-type":"application/json"
            },
            body: JSON.stringify({
                username: user,
                password: pw,
                nome: nome   
            })

    })

    //verifico che il server mi abbia risposto:
    const message = await response.text();

    switch(response.status){
        case 201:
                document.getElementById('yes').style.display = "block";
                break;
        case 400:
                document.getElementById('err').style.display = "block";
                document.getElementById('err').innerText = "Error server code: 400";
                break;
        case 409:
                document.getElementById('esi').style.display = "block";
                break;
        default:
                alert('default case');
                break;
    }
}