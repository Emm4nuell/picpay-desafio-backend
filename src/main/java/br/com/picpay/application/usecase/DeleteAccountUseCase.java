package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.exception.ErrorGenericException;
import br.com.picpay.application.port.in.IDeleteAccountUseCase;
import br.com.picpay.application.port.in.IFindByIdAccountUseCase;
import br.com.picpay.application.port.out.IDeleteAccountService;
import br.com.picpay.application.util.validator.IdNullValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class DeleteAccountUseCase implements IDeleteAccountUseCase {

    private final IDeleteAccountService iDeleteAccountService;
    private final IFindByIdAccountUseCase iFindByIdAccountUseCase;
    private final IdNullValidator idNullValidator;

    @Override
    public void execute(Long id) {
        idNullValidator.validate(id);
        iFindByIdAccountUseCase.execute(id);

        try {
            iDeleteAccountService.execute(id);
        }catch (Exception ex){
            throw new ErrorGenericException("Erro ao deletar a conta: " + ex.getMessage());
        }
    }
}
