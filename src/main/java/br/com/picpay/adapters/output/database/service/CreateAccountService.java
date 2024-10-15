package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.entity.AccountEntity;
import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.adapters.output.exception.ErrorGenericException;
import br.com.picpay.adapters.output.exception.ValueUniqueException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.out.ICreateAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAccountService implements ICreateAccountService {

    private final AccountRepository accountRepository;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public AccountDomain execute(AccountDomain domain) {
        try {
            var entity = accountRepository.save(mapper.convertValue(domain, AccountEntity.class));
            return mapper.convertValue(entity, AccountDomain.class);
        }catch (DataIntegrityViolationException ex){
            if (ex.getMessage().contains("EMAIL")){
                throw new ValueUniqueException("EMAIL ja cadastrado na base de dados.");
            }else if(ex.getMessage().contains("CPF")){
                throw new ValueUniqueException("CPF ja cadastrado na base de dados.");
            }
            throw new ErrorGenericException("Erro ao criar conta. " + ex.getMessage());
        }
    }
}
