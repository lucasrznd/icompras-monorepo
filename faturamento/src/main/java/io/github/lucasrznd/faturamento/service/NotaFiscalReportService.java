package io.github.lucasrznd.faturamento.service;

import io.github.lucasrznd.faturamento.dtos.response.ItemPedidoResponse;
import io.github.lucasrznd.faturamento.dtos.response.PedidoResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NotaFiscalReportService {

    @Value("classpath:report/nota-fiscal.jrxml")
    private Resource reportTemplate;

    public byte[] generateReport(PedidoResponse pedido) {
        try (InputStream inputStream = reportTemplate.getInputStream()) {
            Map<String, Object> parameters = getParameters(pedido);

            var dataSource = new JRMapCollectionDataSource(toItemMaps(pedido.itens()));

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            log.error("Erro ao gerar o relatório: {}", e.getMessage(), e);
        }
        return null;
    }

    private static List<Map<String, ?>> toItemMaps(List<ItemPedidoResponse> itens) {
        return itens.stream()
                .<Map<String, ?>>map(item -> {
                    Map<String, Object> row = new HashMap<>();
                    row.put("descricao", item.nomeProduto());
                    row.put("quantidade", item.quantidade());
                    row.put("valorUnitario", item.valorUnitario());
                    row.put("total", item.total());
                    return row;
                })
                .toList();
    }

    private static final DateTimeFormatter DATA_PEDIDO_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static @NonNull Map<String, Object> getParameters(PedidoResponse pedido) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("NOME", pedido.cliente().nome());
        parameters.put("ID", pedido.id().toString());
        parameters.put("CPF", formatCpf(pedido.cliente().cpf()));
        parameters.put("LOGRADOURO", pedido.cliente().logradouro());
        parameters.put("NUMERO", pedido.cliente().numero());
        parameters.put("BAIRRO", pedido.cliente().numero());
        parameters.put("EMAIL", pedido.cliente().email());
        parameters.put("TELEFONE", formatTelefone(pedido.cliente().telefone()));
        parameters.put("DATA_PEDIDO", formatDataPedido(pedido.createdAt()));
        parameters.put("TOTAL_PEDIDO", pedido.total());
        return parameters;
    }

    private static String formatDataPedido(String createdAt) {
        return LocalDateTime.parse(createdAt.replace(" ", "T")).format(DATA_PEDIDO_FORMATTER);
    }

    private static String formatCpf(String cpf) {
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    private static String formatTelefone(String telefone) {
        return telefone.length() == 11
                ? telefone.replaceFirst("(\\d{2})(\\d{5})(\\d{4})", "($1) $2-$3")
                : telefone.replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3");
    }
}
