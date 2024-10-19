package br.com.picpay.application.usecase;

import br.com.picpay.application.port.in.IDeleteAccountUseCase;
import br.com.picpay.application.port.in.IFindByIdAccountUseCase;
import br.com.picpay.application.port.out.IDeleteAccountService;
import br.com.picpay.application.util.validator.ValueNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class DeleteAccountUseCase implements IDeleteAccountUseCase {

    private final IDeleteAccountService iDeleteAccountService;
    private final IFindByIdAccountUseCase iFindByIdAccountUseCase;
    private final ValueNullValidator valueNullValidator;

    @Override
    public void execute(Long id) {
        valueNullValidator.validate(id);
        iFindByIdAccountUseCase.execute(id);

        iDeleteAccountService.execute(id);
    }
}
