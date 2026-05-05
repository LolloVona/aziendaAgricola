-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Mag 05, 2026 alle 19:04
-- Versione del server: 10.4.32-MariaDB
-- Versione PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `azienda_agricola`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `acquisto`
--

CREATE TABLE `acquisto` (
  `numero_fattura` int(11) NOT NULL,
  `data` date NOT NULL,
  `totale` float NOT NULL CHECK (`totale` > 0),
  `id_utente` int(11) DEFAULT NULL,
  `data_erogazione` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `aggiornamento`
--

CREATE TABLE `aggiornamento` (
  `id_aggiornamento` int(11) NOT NULL,
  `tipo` varchar(1) NOT NULL CHECK (`tipo` in ('C','U','D')),
  `data` date NOT NULL,
  `attributo_modificato` varchar(50) DEFAULT NULL,
  `nuovo_valore` varchar(50) DEFAULT NULL,
  `vecchio_valore` varchar(50) DEFAULT NULL,
  `id_utente` int(11) DEFAULT NULL,
  `id_prodotto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `prodotto`
--

CREATE TABLE `prodotto` (
  `id_prodotto` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `prezzo` float NOT NULL CHECK (`prezzo` > 0),
  `magazzino` float NOT NULL,
  `disponibilita` float NOT NULL
) ;

--
-- Dump dei dati per la tabella `prodotto`
--

INSERT INTO `prodotto` (`id_prodotto`, `nome`, `prezzo`, `magazzino`, `disponibilita`) VALUES
(6, 'banana', 10, 54, 23),
(7, 'pera', 1, 22, 10);

-- --------------------------------------------------------

--
-- Struttura della tabella `prossimoraccolto`
--

CREATE TABLE `prossimoraccolto` (
  `id_raccolto` int(11) NOT NULL,
  `disponibilita` float NOT NULL CHECK (`disponibilita` >= 0),
  `totale` float NOT NULL,
  `data` date NOT NULL,
  `id_prodotto` int(11) DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Struttura della tabella `relativo`
--

CREATE TABLE `relativo` (
  `numero_fattura` int(11) NOT NULL,
  `id_prodotto` int(11) NOT NULL,
  `quantita` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Struttura della tabella `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `id_utente` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tipo` varchar(1) NOT NULL CHECK (`tipo` in ('C','A'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id_utente`, `nome`, `username`, `password`, `tipo`) VALUES
(1, 'Pino', 'Gino', 'prova123', 'A'),
(2, 'Luca', 'Pino', 'prova', 'C'),
(3, 'Dino', 'Dino', 'prova', 'C'),
(4, 'Lino', 'Lino', 'prova', 'C');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `acquisto`
--
ALTER TABLE `acquisto`
  ADD PRIMARY KEY (`numero_fattura`),
  ADD KEY `id_utente` (`id_utente`);

--
-- Indici per le tabelle `aggiornamento`
--
ALTER TABLE `aggiornamento`
  ADD PRIMARY KEY (`id_aggiornamento`),
  ADD KEY `id_utente` (`id_utente`),
  ADD KEY `id_prodotto` (`id_prodotto`);

--
-- Indici per le tabelle `prodotto`
--
ALTER TABLE `prodotto`
  ADD PRIMARY KEY (`id_prodotto`),
  ADD UNIQUE KEY `nome` (`nome`);

--
-- Indici per le tabelle `prossimoraccolto`
--
ALTER TABLE `prossimoraccolto`
  ADD PRIMARY KEY (`id_raccolto`),
  ADD KEY `id_prodotto` (`id_prodotto`);

--
-- Indici per le tabelle `relativo`
--
ALTER TABLE `relativo`
  ADD PRIMARY KEY (`numero_fattura`,`id_prodotto`),
  ADD KEY `id_prodotto` (`id_prodotto`);

--
-- Indici per le tabelle `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indici per le tabelle `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id_utente`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `acquisto`
--
ALTER TABLE `acquisto`
  MODIFY `numero_fattura` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `aggiornamento`
--
ALTER TABLE `aggiornamento`
  MODIFY `id_aggiornamento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `prodotto`
--
ALTER TABLE `prodotto`
  MODIFY `id_prodotto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `prossimoraccolto`
--
ALTER TABLE `prossimoraccolto`
  MODIFY `id_raccolto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `id_utente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `acquisto`
--
ALTER TABLE `acquisto`
  ADD CONSTRAINT `acquisto_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `aggiornamento`
--
ALTER TABLE `aggiornamento`
  ADD CONSTRAINT `aggiornamento_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utente` (`id_utente`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `aggiornamento_ibfk_2` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `prossimoraccolto`
--
ALTER TABLE `prossimoraccolto`
  ADD CONSTRAINT `prossimoraccolto_ibfk_1` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Limiti per la tabella `relativo`
--
ALTER TABLE `relativo`
  ADD CONSTRAINT `relativo_ibfk_1` FOREIGN KEY (`numero_fattura`) REFERENCES `acquisto` (`numero_fattura`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `relativo_ibfk_2` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
