package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.ICreateAccountUseCase;
import br.com.picpay.application.port.out.ICreateAccountService;
import br.com.picpay.application.port.out.IFindByAccountService;
import br.com.picpay.application.port.out.ISendMessageSuccessService;
import br.com.picpay.application.util.validator.AccountValidator;
import br.com.picpay.application.util.validator.ValueNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@Usecase
@RequiredArgsConstructor
public class CreateAccountUseCase implements ICreateAccountUseCase {

    private final ICreateAccountService iCreateAccountService;
    private final ISendMessageSuccessService successService;
    private final AccountValidator accountValidator;
    private final ValueNullValidator valueNullValidator;
    private final IFindByAccountService iFindByAccountService;

    @Override
    public AccountDomain execute(AccountDomain domain) {
        valueNullValidator.validate(domain);
        accountValidator.validate(domain);
        String accountRandom;

        do{
            accountRandom = String.format("%06d", ThreadLocalRandom.current().nextInt(1000000));
        }while (iFindByAccountService.execute(accountRandom));

        domain.setBalance(BigDecimal.valueOf(0));
        domain.setAccount(accountRandom);
        var request = iCreateAccountService.execute(domain);
        successService.execute(request);
        return request;
    }
}
