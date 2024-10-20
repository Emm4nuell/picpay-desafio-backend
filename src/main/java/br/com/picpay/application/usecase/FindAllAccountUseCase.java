package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.model.PageAccountDomain;
import br.com.picpay.application.port.in.IFindAllAccountUseCase;
import br.com.picpay.application.port.out.IFindAllAccountService;
import br.com.picpay.application.util.validator.ValueNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class FindAllAccountUseCase implements IFindAllAccountUseCase {

    private final IFindAllAccountService iFindAllAccountService;
    private final ValueNullValidator valueNullValidator;

    @Override
    public PageAccountDomain execute(PageAccountDomain domain) {
        valueNullValidator.validate(domain);
        return iFindAllAccountService.execute(domain);
    }
}
