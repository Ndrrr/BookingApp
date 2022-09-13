package app.data.entity;

import java.util.Objects;

public abstract class Entity {
    private final int id;
    Entity(){
        id = getIdCounter();
    }
    Entity(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    protected abstract int getIdCounter();
}
