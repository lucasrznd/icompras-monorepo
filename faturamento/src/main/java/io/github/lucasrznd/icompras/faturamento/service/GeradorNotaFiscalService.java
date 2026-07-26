package io.github.lucasrznd.icompras.faturamento.service;

import io.github.lucasrznd.icompras.faturamento.dtos.request.CreateFileRequest;
import io.github.lucasrznd.icompras.faturamento.dtos.response.PedidoResponse;
import io.github.lucasrznd.icompras.faturamento.publisher.FaturamentoPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeradorNotaFiscalService {

    private final NotaFiscalReportService notaFiscalReportService;
    private final BucketService bucketService;
    private final FaturamentoPublisher publisher;

    public void generate(PedidoResponse pedido) {
        byte[] content = notaFiscalReportService.generateReport(pedido);
        String fileName = String.format("nota-fiscal-%s.pdf", pedido.id());

        bucketService.upload(new CreateFileRequest(fileName, MediaType.APPLICATION_PDF_VALUE, content));
        publisher.publish(pedido, bucketService.getUrl(fileName).url());
    }
}
