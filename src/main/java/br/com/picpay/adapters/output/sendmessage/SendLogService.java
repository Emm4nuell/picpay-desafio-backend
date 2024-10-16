package br.com.picpay.adapters.output.sendmessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendLogService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper mapper;
    @Value("${kafka.producer.topic-error}")
    private String topicerror;

    public void send(Map<String, Object> logmessage){

        try {
            kafkaTemplate.send(topicerror, mapper.writeValueAsString(logmessage))
                    .thenAccept(result -> log.info("Sucesso ao enviar o log para o kafka {}", result))
                    .exceptionally(ex -> {
                        log.error("Erro ao enviar a log para o kafka, {}", ex.getMessage());
                        return null;
                    });
        } catch (JsonProcessingException ex) {
            log.error("Erro ao converter a mensagem em JSON. {}", ex.getMessage());
        } catch (Exception ex){
            log.error("Erro Interno ao enviar mensagem. {}", ex.getMessage());
        }
    }
}
