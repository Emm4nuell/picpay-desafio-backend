package br.com.picpay.adapters.input.controller;

import br.com.picpay.adapters.input.api.IApiController;
import br.com.picpay.adapters.input.controller.dto.RequestAccount;
import br.com.picpay.adapters.input.controller.dto.ResponseAccount;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.ICreateAccountUseCase;
import br.com.picpay.application.port.in.IFindByIdAccountUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController implements IApiController {

    private final ICreateAccountUseCase iCreateAccountUseCase;
    private final IFindByIdAccountUseCase iFindByIdAccountUseCase;
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
    public ResponseEntity<List<ResponseAccount>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseAccount> update(Long id, RequestAccount request) {
        return null;
    }
}
