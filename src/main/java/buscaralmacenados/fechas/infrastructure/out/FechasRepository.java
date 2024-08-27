package buscaralmacenados.fechas.infrastructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import buscaralmacenados.fechas.domain.service.FechasService;
import form.infrastructure.config.DatabaseConfig;

public class FechasRepository implements FechasService {

    @Override
    public ResultSet findFechasByCreatedAt(Date startDate, Date endDate) {
        String sql = "SELECT id, option_value, created_at, updated_at, comment_response, option_text, categorycatalog_id, parentresponse_id, question_id " +
                     "FROM response_options WHERE created_at BETWEEN ? AND ?";
        try {
            Connection connection = DatabaseConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
