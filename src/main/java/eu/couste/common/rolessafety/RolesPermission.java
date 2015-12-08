package eu.couste.common.rolessafety;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.FIELD })
public @interface RolesPermission {

    public String[] create() default RolesChecker.ALL;

    public String[] read() default RolesChecker.ALL;

    public String[] update() default RolesChecker.ALL;

    public String[] delete() default RolesChecker.ALL;
}
