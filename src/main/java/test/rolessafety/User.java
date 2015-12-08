package test.rolessafety;

import java.util.HashSet;
import java.util.Set;

import eu.couste.common.rolessafety.RolesPermission;


@RolesPermission(create = "admin", delete = "admin", update = { "admin", "boss" })
public class User {

    private long id;

    private String name;
    private User boss;
    @RolesPermission(read = "boss")
    private String surname;
    private Set<String> roles = new HashSet<>();

    public User() {

    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public User(User user) {
        this.name = user.name;
        this.surname = user.surname;
        this.roles.addAll(user.roles);
        this.boss = user.boss;
        this.id = user.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getBoss() {
        return boss;
    }

    public void setBoss(User boss) {
        this.boss = boss;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User other = (User) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", boss="
                + (boss != null ? boss.getName() : null) + ", surname=" + surname + ", roles="
                + roles + "]";
    }
}
