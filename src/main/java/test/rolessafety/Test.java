package test.rolessafety;

public class Test {

    public static void main(String[] args) {
        User admin = new User("admin", "admin_surname");
        User boss = new User("boss", "boss_surname");
        User employee = new User("employee", "employee_surname");
        User employee2 = new User("employee2", "employee2_surname");

        UserService us = new UserService(admin);
        boss = us.createUser(boss, admin);
        employee.setBoss(boss);
        employee = us.createUser(employee, admin);
        employee2 = us.createUser(employee2, employee);

        System.out.println(employee);

        System.out.println("#################################################");

        System.out.println("boss get employee :");
        System.out.println(us.getUserByName("employee", boss));
        System.out.println("employee get employee :");
        System.out.println(us.getUserByName("employee", employee));

        System.out.println("#################################################");

        us.deleteUser(employee, employee);
        us.deleteUser(employee, boss);
        us.deleteUser(employee, admin);

    }

}
