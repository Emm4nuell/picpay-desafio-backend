package br.com.picpay.application.util.validator;

import br.com.picpay.application.domain.exception.NullValueException;
import br.com.picpay.infrastructure.config.Usecase;

@Usecase
public class IdNullValidator {
    public void validate(Long id){
        if (id == null){
            throw new NullValueException("O ID fornecido nao pode ser nulo");
        }
    }
}
