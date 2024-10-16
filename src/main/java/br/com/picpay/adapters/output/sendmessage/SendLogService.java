package br.com.picpay.adapters.output.sendmessage;

import br.com.picpay.adapters.output.util.LogMessageConvertJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendLogService implements ISendLogService{

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final LogMessageConvertJson convertJson;
    @Value("${kafka.producer.topic-error}")
    private String topicerror;

    @Override
    public void send(Map<String, Object> logmessage){
        kafkaTemplate.send(topicerror, convertJson.converter(logmessage))
            .thenAccept(result -> log.info("Sucesso ao enviar o log para o kafka {}", result))
                .exceptionally(ex -> {
                    log.error("Erro ao enviar a log para o kafka, {}", ex.getMessage());
                    return null;
                });
    }
}
