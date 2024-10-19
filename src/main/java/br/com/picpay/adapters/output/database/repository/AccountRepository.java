package br.com.picpay.adapters.output.database.repository;

import br.com.picpay.adapters.output.database.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByAccount(String account);

    @Query(value = "SELECT * FROM account_entity AS a WHERE a.account = :account", nativeQuery = true)
    Optional<AccountEntity> findByAccount(@Param("account") String account);
}
