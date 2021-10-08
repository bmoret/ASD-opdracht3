package java.com.asd.account.domain.model.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.com.asd.account.domain.model.account.Account;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
