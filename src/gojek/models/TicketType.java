package gojek.models;

public enum TicketType {
    CHECK_WALLET_BALANCE("check-wallet-balance"),

    CHANGE_LANGUAGE("change-language"),

    OTHERS("others");

    public String value;

    TicketType(final String value) {
        this.value = value;
    }
}
