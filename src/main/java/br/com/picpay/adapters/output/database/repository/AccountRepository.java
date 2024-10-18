package br.com.picpay.adapters.output.database.repository;

import br.com.picpay.adapters.output.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByAccount(String account);
}
