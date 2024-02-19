package it.epicode.w7d1.request;

import it.epicode.w7d1.enums.Disponibilita;
import it.epicode.w7d1.enums.Tipologia;
import it.epicode.w7d1.model.Dipendente;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DispositivoRequest {

    @NotNull(message = "Inserire una disponibilità")
    private Disponibilita disponibilita;

    @NotNull(message = "Inserire una tipologia")
    private Tipologia tipologia;

    private Dipendente dipendente;


}
