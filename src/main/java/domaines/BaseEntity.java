package domaines;

public abstract class BaseEntity {
    protected Long id;
    public abstract void setId(long id);

    public BaseEntity(){}
    public Long getId() {
        return this.id;
    }
}
