package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.application.port.out.IFindByAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindByAccountService implements IFindByAccountService {

    private final AccountRepository accountRepository;
    private final ObjectMapper mapper;

    @Override
    public boolean execute(String account) {
        return accountRepository.existsByAccount(account);
    }
}
