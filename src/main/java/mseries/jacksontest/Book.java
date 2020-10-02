package mseries.jacksontest;

import java.util.Optional;
import java.util.UUID;

public class Book {

    private String title;
    private Optional<String> subTitle;
    private Optional<UUID> clientId;
    private UUID clientIdUUID;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Optional<String> optional) {
        this.subTitle = optional;
    }

	public Optional<UUID> getClientId() {
		return clientId;
	}

	public void setClientId(Optional<UUID> clientId) {
		this.clientId = clientId;
	}

	public UUID getClientIdUUID() {
		return clientIdUUID;
	}

	public void setClientIdUUID(UUID clientIdUUID) {
		this.clientIdUUID = clientIdUUID;
	}
}
