package test.rolessafety;

import java.util.HashSet;
import java.util.Set;

import eu.couste.common.rolessafety.RolesChecker;
import eu.couste.common.rolessafety.RolesGranter;


public class UserService implements RolesGranter {

    private static long ID = 0;
    private Set<User> users = new HashSet<>();

    public UserService(User admin) {
        admin.getRoles().add("admin");
        ID++;
        admin.setId(ID);
        this.users.add(admin);
    }

    public User createUser(User user, User actor) {
        User validuser = null;
        if ((validuser = (User) RolesChecker.check(RolesChecker.CREATE, actor, user,
                this)) != null) {
            if (!this.users.contains(validuser)) {
                ID++;
                validuser.setId(ID);
                this.users.add(validuser);
            }
        }
        return validuser;
    }

    public void deleteUser(User user, User actor) {
        User validuser = null;
        if ((validuser = (User) RolesChecker.check(RolesChecker.DELETE, actor, user,
                this)) == null) {
            return;
        }
        this.users.remove(validuser);
    }

    public User getUserByName(String name, User actor) {
        for (User u : this.users) {
            if (u.getName().equals(name)) {
                User validuser = (User) RolesChecker.check(RolesChecker.READ, actor, u, this);
                if (validuser == null) {
                    System.out.println("error");
                    return null;
                } else {
                    System.out.println("OK");
                }
                return validuser;
            }
        }
        return null;
    }

    @Override
    public Set<String> grantUserRoleOn(Object user, Object object) {
        User uuser = (User) user;
        User uobject = (User) object;

        Set<String> roles = new HashSet<>();
        roles.addAll(((User) user).getRoles());
        if (uobject.getBoss() != null && uobject.getBoss().equals(uuser)) {
            roles.add("boss");
        }
        return roles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
