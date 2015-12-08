package eu.couste.common.rolessafety;

import java.lang.reflect.Field;
import java.util.Set;


public class RolesChecker {

    public static final String ALL = "anyone";

    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";

    public static Object check(String level, Object user, Object object,
                               RolesGranter rolesGranter) {
        Set<String> roles = rolesGranter.grantUserRoleOn(user, object);
        RolesPermission annotation = object.getClass().getAnnotation(RolesPermission.class);
        String[] requiredRole = {};
        switch (level) {
        case CREATE:
            requiredRole = annotation.create();
            break;
        case READ:
            requiredRole = annotation.read();
            break;
        case UPDATE:
            requiredRole = annotation.update();
            break;
        case DELETE:
            requiredRole = annotation.delete();
            break;
        }
        if (!checkRoles(requiredRole, roles)) {
            return null;
        }
        return createObject(level, object, roles);
    }

    private static boolean checkRoles(String[] requiredRoles, Set<String> roles) {
        for (String requiredRole : requiredRoles) {
            if (requiredRole.equals(ALL)) {
                return true;
            }
            for (String role : roles) {
                if (role.equals(requiredRole)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Object createObject(String level, Object object, Set<String> roles) {
        Object newInstance = null;
        try {
            newInstance = object.getClass().newInstance();
            Field[] fields = object.getClass().getDeclaredFields();
            for (Field field : fields) {
                RolesPermission annotation = field.getAnnotation(RolesPermission.class);
                boolean copy = true;
                if (annotation != null) {
                    String[] requiredRole = {};
                    switch (level) {
                    case CREATE:
                        requiredRole = annotation.create();
                        break;
                    case READ:
                        requiredRole = annotation.read();
                        break;
                    case UPDATE:
                        requiredRole = annotation.update();
                        break;
                    case DELETE:
                        requiredRole = annotation.delete();
                        break;
                    }
                    if (!checkRoles(requiredRole, roles)) {
                        copy = false;
                    }
                }
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                if (copy) {
                    field.set(newInstance, field.get(object));
                } else {
                    field.set(newInstance, null);
                }
                field.setAccessible(isAccessible);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Roles checker error : " + object.getClass().getName()
                    + " does not contains a simple constructor \"public "
                    + object.getClass().getSimpleName() + "()\"");
        }
        return newInstance;
    }
}
