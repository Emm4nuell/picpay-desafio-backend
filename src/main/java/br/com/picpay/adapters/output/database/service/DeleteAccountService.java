package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.adapters.output.exception.ErrorGenericException;
import br.com.picpay.application.port.out.IDeleteAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteAccountService implements IDeleteAccountService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public void execute(Long id) {
        try {
            accountRepository.deleteById(id);
        }catch (Exception ex){
            throw new ErrorGenericException("Erro ao deletar a conta: " + ex.getMessage());
        }
    }
}
