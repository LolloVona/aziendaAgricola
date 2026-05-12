package org.aziendaagricola.record.Acquisto;

import org.aziendaagricola.DTO.AcquistoReadDTO;

import java.util.ArrayList;

public record OrdineDaErogareResponse(ArrayList<AcquistoReadDTO> ordini) {
}