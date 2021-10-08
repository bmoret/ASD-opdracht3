package java.com.asd.account.domain.model.account;

import java.com.asd.account.domain.model.details.PersonDetailsId;

public class Account {
    private AccountId accountId;
    private Branch branch;
    private PersonDetailsId personDetailsId;

    public Account(AccountId accountId, Branch branch, PersonDetailsId personDetailsId) {
        this.accountId = accountId;
        this.branch = branch;
        this.personDetailsId = personDetailsId;
    }
}
