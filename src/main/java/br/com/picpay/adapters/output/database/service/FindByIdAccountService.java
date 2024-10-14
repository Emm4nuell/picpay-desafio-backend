package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.out.IFindByIdAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindByIdAccountService implements IFindByIdAccountService {

    private final AccountRepository accountRepository;
    private final ObjectMapper mapper;

    @Override
    public Optional<AccountDomain> execute(Long id) {
        return Optional.ofNullable(mapper.convertValue(accountRepository.findById(id), AccountDomain.class));
    }
}
