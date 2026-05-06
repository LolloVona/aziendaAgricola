package org.aziendaagricola.record.Prodotto;

import org.aziendaagricola.DTO.ProdottoReadDTO;

import java.util.ArrayList;

public record GetProdottiRecord(ArrayList<ProdottoReadDTO> prodotti, String message) {

}
