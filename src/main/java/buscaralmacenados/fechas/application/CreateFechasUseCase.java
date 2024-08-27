package buscaralmacenados.fechas.application;

import buscaralmacenados.fechas.domain.entity.Fechas;
import buscaralmacenados.fechas.domain.service.FechasService;

public class CreateFechasUseCase {
    private final FechasService fechasService;

    public CreateFechasUseCase(FechasService fechasService) {
        this.fechasService = fechasService;
    }

    public void execute(Fechas fechas) {
        fechasService.findFechasByCreatedAt(fechas.getCreated_at(), fechas.getUpdated_at());
    }
}
