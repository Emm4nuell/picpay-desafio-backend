package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.application.port.out.IDeleteAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAccountService implements IDeleteAccountService {

    private final AccountRepository accountRepository;

    @Override
    public void execute(Long id) {
        accountRepository.deleteById(id);
    }
}
