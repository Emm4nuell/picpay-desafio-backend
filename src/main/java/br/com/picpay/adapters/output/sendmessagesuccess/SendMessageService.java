package br.com.picpay.adapters.output.sendmessagesuccess;

import br.com.picpay.adapters.output.util.LogMessageConvertJson;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.out.ISendMessageSuccessService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendMessageService implements ISendMessageSuccessService {

    private final SnsAsyncClient snsAsyncClient;
    private final LogMessageConvertJson convertJson;
    @Value("${aws.topic-arn}")
    private String topic;

    @Override
    public void execute(AccountDomain domain) {
        PublishRequest request = PublishRequest
                .builder()
                .topicArn(topic)
                .message("SUCCESS")
                .message(convertJson.converter(domain))
                .build();
        snsAsyncClient.publish(request)
                .thenAccept(message -> log.info("Topic enviar com sucesso para o AWS sns ", message.toString()))
                .exceptionally(ex -> {
                    log.error("Error ao enviar topico para o AWS. {}", ex.getMessage() );
                    return null;
                });
    }
}
