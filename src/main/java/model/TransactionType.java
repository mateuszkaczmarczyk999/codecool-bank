package model;


public class TransactionType extends AbstractType {
    TransactionType(String name, String description) {
        super(name, description);
    }

    TransactionType(Integer id, String name, String description) {
        super(id, name, description);
    }
}
