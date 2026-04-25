import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="acquisto")
public class Acquisto {
    @Id
    private int numeroFattura;
    @Column
    private LocalDate data;
    @Column
    private float totale;
    
}
