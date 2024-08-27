package buscaralmacenados.fechas.infrastructure.in;

import buscaralmacenados.fechas.application.CreateFechasUseCase;
import buscaralmacenados.fechas.domain.service.FechasService;

import javax.swing.*;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FechasController {
    private CreateFechasUseCase createFechasUseCase;
    private FechasService fechasService;

    public FechasController(CreateFechasUseCase createFechasUseCase, FechasService fechasService) {
        this.createFechasUseCase = createFechasUseCase;
        this.fechasService = fechasService;
    }

    public void mostrarencuestas() {
        try {
            // Solicitar la fecha de inicio al usuario
            String fechaInicioStr = JOptionPane.showInputDialog(null, "Ingrese la fecha de inicio (YYYY-MM-DD):");
            if (fechaInicioStr == null || fechaInicioStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La fecha de inicio no puede estar vacía.");
                return;
            }
            Date fechaInicio = Date.valueOf(fechaInicioStr); // Convertir a Date

            // Solicitar la fecha de fin al usuario
            String fechaFinStr = JOptionPane.showInputDialog(null, "Ingrese la fecha de fin (YYYY-MM-DD):");
            if (fechaFinStr == null || fechaFinStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "La fecha de fin no puede estar vacía.");
                return;
            }
            Date fechaFin = Date.valueOf(fechaFinStr); // Convertir a Date

            // Ejecutar la consulta para encontrar las fechas
            ResultSet resultSet = fechasService.findFechasByCreatedAt(fechaInicio, fechaFin);

            if (resultSet != null) {
                StringBuilder result = new StringBuilder();
                while (resultSet.next()) {
                    result.append("ID: ").append(resultSet.getInt("id"))
                          .append("\nOption Value: ").append(resultSet.getInt("option_value"))
                          .append("\nCreated At: ").append(resultSet.getDate("created_at"))
                          .append("\nUpdated At: ").append(resultSet.getDate("updated_at"))
                          .append("\nComment Response: ").append(resultSet.getString("comment_response"))
                          .append("\nOption Text: ").append(resultSet.getString("option_text"))
                          .append("\nCategory Catalog ID: ").append(resultSet.getInt("categorycatalog_id"))
                          .append("\nParent Response ID: ").append(resultSet.getInt("parentresponse_id"))
                          .append("\nQuestion ID: ").append(resultSet.getInt("question_id"))
                          .append("\n\n");
                }
                resultSet.close(); // Cerrar el ResultSet
                if (result.length() > 0) {
                    JOptionPane.showMessageDialog(null, result.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontraron resultados.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta.");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "El formato de la fecha ingresada es incorrecto. Use el formato YYYY-MM-DD.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al procesar los resultados.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
