package br.com.picpay.adapters.input.api;

import br.com.picpay.adapters.input.controller.dto.RequestAccount;
import br.com.picpay.adapters.input.controller.dto.ResponseAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("account/")
public interface IApiController {

    @PostMapping("create")
    ResponseEntity<ResponseAccount> create(@RequestBody RequestAccount request);

    @GetMapping("get/{id}")
    ResponseEntity<ResponseAccount> findById(@PathVariable Long id);

    @GetMapping("list")
    ResponseEntity<List<ResponseAccount>> findAll();

    @DeleteMapping("delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping("update/{id}")
    ResponseEntity<ResponseAccount> update(@PathVariable Long id, @RequestBody RequestAccount request);
}
