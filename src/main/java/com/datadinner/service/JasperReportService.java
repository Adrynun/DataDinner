package com.datadinner.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import com.datadinner.model.Pedido;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportService {

    public byte[] generarTicket(Pedido pedido) throws JRException {
        InputStream reportStream = getClass().getResourceAsStream("/reports/ticket.jasper");
        if (reportStream == null) {
            throw new RuntimeException("No se encontró el reporte ticket.jasper en /resources/reports/");
        }

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(pedido.getProductos());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("pedidoId", pedido.getId());
        parameters.put("fecha", pedido.getFechaHora());
        parameters.put("mesa", pedido.getMesa().getId());
        parameters.put("usuario", pedido.getUsuario().getNombreUsuario());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
