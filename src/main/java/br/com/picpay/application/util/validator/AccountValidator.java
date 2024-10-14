package br.com.picpay.application.util.validator;

import br.com.picpay.application.domain.exception.NullValueException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.infrastructure.config.Usecase;
import io.micrometer.common.util.StringUtils;

@Usecase
public class AccountValidator {
    public void validate(AccountDomain domain){
        if (domain == null){
            throw new NullValueException("O valor fornecido ao criar uma conta nao pode ser nulo");
        }

        if (StringUtils.isBlank(domain.getCpf()) || StringUtils.isBlank(domain.getEmail())){
            throw new NullValueException("O Email ou Cpf nao pode ser nulo.");
        }
    }
}
