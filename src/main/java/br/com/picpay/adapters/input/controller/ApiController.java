package br.com.picpay.adapters.input.controller;

import br.com.picpay.adapters.input.api.IApiController;
import br.com.picpay.adapters.input.controller.dto.*;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.domain.model.PageAccountDomain;
import br.com.picpay.application.port.in.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class ApiController implements IApiController {

    private final ICreateAccountUseCase iCreateAccountUseCase;
    private final IFindByIdAccountUseCase iFindByIdAccountUseCase;
    private final IDeleteAccountUseCase iDeleteAccountUseCase;
    private final IUpdateAccountUseCase iUpdateAccountUseCase;
    private final IFindAllAccountUseCase iFindAllAccountUseCase;
    private final IFindByDepositAccountUseCase iFindByDepositAccountUseCase;
    private final ObjectMapper mapper;

    @Override
    public ResponseEntity<ResponseAccount> create(RequestAccount request) {

        var domain = iCreateAccountUseCase.execute(mapper.convertValue(request, AccountDomain.class));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(domain.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ResponseAccount> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.convertValue(iFindByIdAccountUseCase.execute(id), ResponseAccount.class));
    }

    @Override
    public ResponseEntity<ResponseAccountPage> findAll(int size, int page, String sort, String direction) {
        var domain = PageAccountDomain
                .builder()
                .pages(page)
                .size(size)
                .sort(sort)
                .direction(direction)
                .build();

        var listDomain = iFindAllAccountUseCase.execute(domain);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.convertValue(iFindAllAccountUseCase.execute(domain), ResponseAccountPage.class));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        iDeleteAccountUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<ResponseAccount> update(Long id, RequestAccount request) {
        var domain = mapper.convertValue(request, AccountDomain.class);
        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.convertValue(iUpdateAccountUseCase.execute(id,domain), ResponseAccount.class));
    }

    @Override
    public ResponseEntity<ResponseAccountDeposit> deposit(RequestAccountDeposit request) {
        var domain = iFindByDepositAccountUseCase.execute(
                AccountDomain
                        .builder()
                        .cpf(request.getCpf())
                        .agency(request.getAgency())
                        .account(request.getAccount())
                        .balance(request.getValue())
                        .build());
        return ResponseEntity.status(HttpStatus.OK).body(mapper.convertValue(domain, ResponseAccountDeposit.class));
    }
}
