package java.com.asd.account.domain.model.account;

import java.io.Serializable;
import java.util.UUID;

public class AccountId implements Serializable {
    private final UUID id;

    public AccountId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
