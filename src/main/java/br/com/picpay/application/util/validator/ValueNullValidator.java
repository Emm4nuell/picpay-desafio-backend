package br.com.picpay.application.util.validator;

import br.com.picpay.application.domain.exception.NullValueException;
import br.com.picpay.infrastructure.config.Usecase;

@Usecase
public class ValueNullValidator {
    public void validate(Object value){
        if (value == null){
            throw new NullValueException("O valor fornecido nao pode ser nulo. " + value);
        }
    }
}
