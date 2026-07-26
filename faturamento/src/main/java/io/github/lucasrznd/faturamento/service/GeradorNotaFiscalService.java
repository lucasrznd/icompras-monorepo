package io.github.lucasrznd.faturamento.service;

import io.github.lucasrznd.faturamento.dtos.request.CreateFileRequest;
import io.github.lucasrznd.faturamento.dtos.response.PedidoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeradorNotaFiscalService {

    private final NotaFiscalReportService notaFiscalReportService;
    private final BucketService bucketService;

    public void generate(PedidoResponse pedido) {
        byte[] content = notaFiscalReportService.generateReport(pedido);
        String fileName = String.format("nota-fiscal-%s.pdf", pedido.id());

        bucketService.upload(new CreateFileRequest(fileName, MediaType.APPLICATION_PDF_VALUE, content));
    }
}
