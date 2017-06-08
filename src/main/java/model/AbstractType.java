package model;


public abstract class AbstractType {

    private Integer typeId;
    private String name;
    private String description;

    AbstractType(Integer id, String name, String description) {
        this.typeId = id;
        this.name = name;
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
