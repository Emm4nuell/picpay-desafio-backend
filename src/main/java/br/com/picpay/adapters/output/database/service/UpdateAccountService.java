package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.entity.AccountEntity;
import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.adapters.output.exception.ErrorGenericException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.out.IUpdateAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAccountService implements IUpdateAccountService {

    private final AccountRepository accountRepository;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public AccountDomain execute(AccountDomain domain) {
        try {
            var entity = accountRepository.save(mapper.convertValue(domain, AccountEntity.class));
            return mapper.convertValue(entity, AccountDomain.class);
        }catch (Exception ex){
            throw new ErrorGenericException("Erro ao atualizar a conta." + ex.getMessage());
        }
    }
}
