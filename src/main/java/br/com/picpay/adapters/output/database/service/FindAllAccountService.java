package br.com.picpay.adapters.output.database.service;

import br.com.picpay.adapters.output.database.entity.AccountEntity;
import br.com.picpay.adapters.output.database.repository.AccountRepository;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.domain.model.PageAccountDomain;
import br.com.picpay.application.port.out.IFindAllAccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindAllAccountService implements IFindAllAccountService {

    private final AccountRepository accountRepository;
    private final ObjectMapper mapper;

    @Override
    public PageAccountDomain execute(PageAccountDomain domain) {
        Pageable pageable = PageRequest.of(
                domain.getPages(),
                domain.getSize(),
                Sort.by(Sort.Direction.fromString(domain.getDirection()), domain.getSort()));
        Page<AccountEntity> listEntity = accountRepository.findAll(pageable);
        List<AccountDomain> listDomain = listEntity.getContent().stream()
                .map(x -> mapper.convertValue(x, AccountDomain.class)).collect(Collectors.toList());

        return PageAccountDomain.builder()
                .listAccount(listDomain)
                .size(domain.getSize())
                .sort(domain.getSort())
                .direction(domain.getDirection())
                .totalPages(listEntity.getTotalPages())
                .totalElements(listEntity.getTotalElements())
                .build();
    }
}
