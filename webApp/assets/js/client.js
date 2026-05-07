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
                alert('Login Effettuato');
                break;
        case 400:
                alert('errore server Code: 400');
                break;
        case 401:
                alert('Credenziali non valide');
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
                alert('Utente Creato!');
                break;
        case 400:
                alert('errore server Code: 400');
                break;
        case 409:
                alert('Utente già presente');
                break;
        default:
                alert('default case');
                break;
    }
}