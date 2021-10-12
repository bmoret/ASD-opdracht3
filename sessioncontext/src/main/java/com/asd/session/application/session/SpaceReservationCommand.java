package main.java.com.asd.session.application.session;

import java.util.UUID;

public class SpaceReservationCommand {
    private UUID sessionId;
    private UUID spaceId;

    public SpaceReservationCommand(UUID sessionId, UUID spaceId) {
        this.sessionId = sessionId;
        this.spaceId = spaceId;
    }

    public UUID getSessionId() {
        return sessionId;
    }

    public void setSessionId(UUID sessionId) {
        this.sessionId = sessionId;
    }

    public UUID getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(UUID spaceId) {
        this.spaceId = spaceId;
    }
}
