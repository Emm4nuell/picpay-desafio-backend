package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.exception.ErrorGenericException;
import br.com.picpay.application.domain.exception.ValueUniqueException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.ICreateAccountUseCase;
import br.com.picpay.application.port.out.ICreateAccountService;
import br.com.picpay.application.util.validator.AccountValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

@Usecase
@RequiredArgsConstructor
public class CreateAccountUseCase implements ICreateAccountUseCase {

    private final ICreateAccountService iCreateAccountService;
    private final AccountValidator accountValidator;

    @Override
    public AccountDomain execute(AccountDomain domain) {

        accountValidator.validate(domain);

        try {
            return iCreateAccountService.execute(domain);
        }catch (DataIntegrityViolationException ex){
            if (ex.getMessage().contains("CPF")){
                throw new ValueUniqueException("O cpf ja encontra-se cadastrado na base de dados!");
            }else if(ex.getMessage().contains("EMAIL")){
                throw new ValueUniqueException("O Email ja encontra-se cadastrado na base de dados");
            }
            throw new ErrorGenericException("Erro ao tentar salvar a conta" + ex.getMessage());
        }
    }
}
