package br.com.picpay.adapters.output.util;

import br.com.picpay.adapters.output.exception.ErrorGenericException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class LogMessageConvertJson {

    private final ObjectMapper mapper;

    public String converter(Map<String, Object> message){
        try {
            return mapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new ErrorGenericException("Erro ao converter log em json para ser enviado via kafka.");
        }
    }
}
