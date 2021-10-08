package main.java.com.asd.session.domain.model.person;

import main.java.com.asd.session.domain.model.account.AccountId;

import java.time.LocalDateTime;

public class Person {
    private PersonId personId;
    private Attendance attendance;
    private SessionRole sessionRole;
    private LocalDateTime createdAt;
    private AccountId accountId;

    public Person(PersonId personId, Attendance attendance, SessionRole sessionRole, LocalDateTime createdAt, AccountId accountId) {
        this.personId = personId;
        this.attendance = attendance;
        this.sessionRole = sessionRole;
        this.createdAt = createdAt;
        this.accountId = accountId;
    }
}
