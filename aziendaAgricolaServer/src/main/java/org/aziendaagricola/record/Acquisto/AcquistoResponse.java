package org.aziendaagricola.record.Acquisto;

import java.time.LocalDate;

public record AcquistoResponse (int numeroFattura, float prezzo, LocalDate dataERogazione) {
}
