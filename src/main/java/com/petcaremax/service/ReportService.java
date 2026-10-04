// This class handles one part of the PetCareMAX application.
package com.petcaremax.service;

import com.petcaremax.util.DBConnection;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

// This service keeps the business rules separate from the user interface.
public class ReportService {

    public void showAppointmentReport() throws Exception {

        InputStream reportStream =
                getClass().getResourceAsStream(
                        "/reports/AppointmentReport.jrxml"
                );

        if (reportStream == null) {
            throw new Exception(
                    "AppointmentReport.jrxml was not found."
            );
        }

        JasperReport jasperReport =
                JasperCompileManager.compileReport(reportStream);

        try (Connection connection =
                     DBConnection.getInstance().getConnection()) {

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            new HashMap<>(),
                            connection
                    );

            JasperViewer.viewReport(
                    jasperPrint,
                    false
            );
        }
    }
}
