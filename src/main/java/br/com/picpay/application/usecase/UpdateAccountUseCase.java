package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.IFindByIdAccountUseCase;
import br.com.picpay.application.port.in.IUpdateAccountUseCase;
import br.com.picpay.application.port.out.IUpdateAccountService;
import br.com.picpay.application.util.validator.AccountValidator;
import br.com.picpay.application.util.validator.ValueNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class UpdateAccountUseCase implements IUpdateAccountUseCase {

    private final IUpdateAccountService iUpdateAccountService;
    private final IFindByIdAccountUseCase iFindByIdAccountUseCase;
    private final AccountValidator accountValidator;
    private final ValueNullValidator valueNullValidator;

    @Override
    public AccountDomain execute(Long id, AccountDomain domain) {
        valueNullValidator.validate(id);
        valueNullValidator.validate(domain);
        accountValidator.validate(domain);
        iFindByIdAccountUseCase.execute(id);

        return iUpdateAccountService.execute(domain);
    }
}
