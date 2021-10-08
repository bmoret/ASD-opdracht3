package java.com.asd.reservation.domain.model.account;

import java.util.UUID;

public class AccountId {
    private final UUID id;

    public AccountId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
