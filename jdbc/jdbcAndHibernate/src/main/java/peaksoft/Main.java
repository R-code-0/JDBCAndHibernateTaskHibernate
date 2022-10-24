package peaksoft;

import peaksoft.dao.UserDao;
import peaksoft.dao.UserDaoHibernateImpl;
import peaksoft.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        UserDao service = new UserDaoHibernateImpl();
        service.createUsersTable();
        service.saveUser("1","11", (byte) 11);
        service.saveUser("2","22", (byte) 22);
        service.saveUser("3","33", (byte) 33);
        service.saveUser("4","44", (byte) 44);
        service.getAllUsers().forEach(System.out::println);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
