package model;


public abstract class AbstractStatus {

    private Integer statusId;
    private String name;
    private String description;

    public AbstractStatus(Integer id, String name, String description) {
        this.statusId = id;
        this.name = name;
        this.description = description;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
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
