package br.com.picpay.adapters.input.api;

import br.com.picpay.adapters.input.controller.dto.RequestAccount;
import br.com.picpay.adapters.input.controller.dto.ResponseAccount;
import br.com.picpay.adapters.input.controller.dto.ResponseAccountPage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("account/")
public interface IApiController {

    @PostMapping("create")
    ResponseEntity<ResponseAccount> create(@RequestBody RequestAccount request);

    @GetMapping("get/{id}")
    ResponseEntity<ResponseAccount> findById(@PathVariable Long id);

    @GetMapping("list")
    ResponseEntity<ResponseAccountPage> findAll(@RequestParam(name = "size", defaultValue = "5") int size,
                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                @RequestParam(name = "sort", defaultValue = "name") String sort,
                                                @RequestParam(name = "direction", defaultValue = "asc") String direction);

    @DeleteMapping("delete/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping("update/{id}")
    ResponseEntity<ResponseAccount> update(@PathVariable Long id, @RequestBody RequestAccount request);
}
