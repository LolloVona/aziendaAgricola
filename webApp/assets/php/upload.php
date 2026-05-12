<?php

$cartella = "../img/";

if(!isset($_FILES["immagine"])){
    die("File mancante");
}

$nome = $_POST["nomeFile"];
$nomeFinale = $nome . ".jpg";
$path = $cartella . $nomeFinale;

if(move_uploaded_file($_FILES["immagine"]["tmp_name"], $path)){
    echo "OK salvata in: " . realpath($path);
}else{
    echo "ERRORE move_uploaded_file";
}