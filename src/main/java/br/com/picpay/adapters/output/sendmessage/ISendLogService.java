package br.com.picpay.adapters.output.sendmessage;

import java.util.Map;

public interface ISendLogService {
    void send(Map<String, Object> topicmessage);
}
