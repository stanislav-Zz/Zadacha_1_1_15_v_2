package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {


        UserService userService = new UserServiceImpl();
//        userService.createUsersTable();


        userService.saveUser(" name1 ", " lastName1 ", (byte) 18);
        userService.saveUser(" name2 ", " lastName2 ", (byte) 35);
        userService.saveUser(" name3 ", " lastName3 ", (byte) 38);
        userService.saveUser(" name4 ", " lastName4 ", (byte) 36);
//
//        userService.removeUserById(2);
//
//        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();





    }
}
