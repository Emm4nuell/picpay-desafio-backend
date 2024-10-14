package br.com.picpay.adapters.input.controller;

import br.com.picpay.adapters.input.api.IApiController;
import br.com.picpay.adapters.input.controller.dto.RequestAccount;
import br.com.picpay.adapters.input.controller.dto.ResponseAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController implements IApiController {
    @Override
    public ResponseEntity<ResponseAccount> create(RequestAccount request) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseAccount> findById(Long id) {
        return null;
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
