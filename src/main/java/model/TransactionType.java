package model;


public class TransactionType extends AbstractType {
    TransactionType(String name, String description) {
        super(name, description);
    }

    public TransactionType(Integer id, String name, String description) {
        super(id, name, description);
    }
}
