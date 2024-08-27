package buscaralmacenados.fechas.domain.service;

import java.sql.Date;
import java.sql.ResultSet;

public interface FechasService {
    ResultSet findFechasByCreatedAt(Date startDate, Date endDate);
}
