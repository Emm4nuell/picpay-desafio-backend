package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.exception.AccountNotFoundException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.IFindByIdAccountUseCase;
import br.com.picpay.application.port.out.IFindByIdAccountService;
import br.com.picpay.application.util.validator.ValueNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class FindByIdAccountUseCase implements IFindByIdAccountUseCase {

    private final IFindByIdAccountService iFindByIdAccountService;
    private final ValueNullValidator valueNullValidator;

    @Override
    public AccountDomain execute(Long id) {
        valueNullValidator.validate(id);
        return iFindByIdAccountService.execute(id)
                .orElseThrow(() -> new AccountNotFoundException("Account nao localizado na base de dados."));
    }
}
