package synchronization.resolver;

public enum CurrencyEnum {

    PLN("PLN"),
    USD("USD"),
    EUR("EUR");

    private final String text;

    CurrencyEnum(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }
}
