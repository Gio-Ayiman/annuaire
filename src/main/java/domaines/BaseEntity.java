package domaines;

import database.Database;

import java.sql.SQLException;

public abstract class BaseEntity {
    protected long id;

    public BaseEntity() throws SQLException {
        this.id = Database.nextVal();
    }

    public void setId(long identifiant) {
        this.id = identifiant;
    }
    public long getId() {
        return this.id;
    }

}
