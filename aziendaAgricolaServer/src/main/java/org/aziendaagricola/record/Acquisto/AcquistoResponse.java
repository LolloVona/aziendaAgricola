package org.aziendaagricola.record.Acquisto;

import java.time.LocalDate;

public record AcquistoResponse (float prezzo, LocalDate dataErogazione) {
}
