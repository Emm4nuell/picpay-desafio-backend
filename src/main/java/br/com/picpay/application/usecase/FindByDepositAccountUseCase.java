package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.exception.AccountNotFoundException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.IFindByDepositAccountUseCase;
import br.com.picpay.application.port.out.IFindByDepositAccountService;
import br.com.picpay.application.port.out.IUpdateAccountService;
import br.com.picpay.application.util.validator.ValueNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class FindByDepositAccountUseCase implements IFindByDepositAccountUseCase {

    private final IFindByDepositAccountService iFindByDepositAccountService;
    private final ValueNullValidator valueNullValidator;
    private final IUpdateAccountService iUpdateAccountService;

    @Override
    public AccountDomain execute(AccountDomain domain) {
        valueNullValidator.validate(domain);
        var account = iFindByDepositAccountService.execute(domain.getAccount())
                .orElseThrow(() -> new AccountNotFoundException("Conta não localizado na base de dados: " + domain.getAccount()));
        if (account.getCpf().equals(domain.getCpf()) && account.getAgency().equals(domain.getAgency())){
            account.setBalance(account.getBalance().add(domain.getBalance()));
            return iUpdateAccountService.execute(account);
        }else {
            throw new AccountNotFoundException("Agência ou conta incorreta. Agência: " + domain.getAgency() + ", Conta: " + domain.getAccount() + ", Cpf: " + domain.getCpf());
        }
    }
}
