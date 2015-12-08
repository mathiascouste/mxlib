package eu.couste.common.rolessafety;

import java.util.Set;


public interface RolesGranter {

    Set<String> grantUserRoleOn(Object user, Object object);

}
