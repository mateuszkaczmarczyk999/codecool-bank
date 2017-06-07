package model;


public abstract class AbstractType {

    private Integer typeId;
    private String name;
    private String description;

    AbstractType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    AbstractType(Integer id, String name, String description) {
        this.typeId = id;
        this.name = name;
        this.description = description;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
