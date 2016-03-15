package se.coredev.atm.logic;

import java.util.UUID;

import se.coredev.atm.model.ATMCard;

public abstract class AbstractATMSession implements ATMSession {
	protected final ATMCard atmCard;
	protected final Bank bank;
	protected final String sessionId;

	public AbstractATMSession(ATMCard atmCard, Bank bank) {
		this.atmCard = atmCard;
		this.bank = bank;
		this.sessionId = UUID.randomUUID().toString();
	}

	@Override
	public final boolean equals(Object other) {
		if (other instanceof AbstractATMSession) {
			return ((AbstractATMSession) other).sessionId.equals(sessionId);
		}
		return false;
	}

	@Override
	public final int hashCode() {
		return sessionId.hashCode() * 37;
	}
}
