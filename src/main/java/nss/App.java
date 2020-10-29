package nss;

import nss.config.BeanConfig;
import nss.entity.User;
import nss.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
/**
 * Main application to run
 *
 */
public class App 
{
  
    public static void main( String[] args ) {

        System.out.println( "Starting application......................!!!!!!! \n" );
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        testDeleteAll(userRepository);
        testInsert(userRepository);
        testGetAll(userRepository);
        testGetById(userRepository);
        testGetByName(userRepository);
        testGetByIdAndName(userRepository);
        testUpdateNameAndEmailById(userRepository);
        testDeleteById(userRepository);
        testGetAll(userRepository);
    }

    static void testDeleteAll(UserRepository userRepository){
        System.out.println("\n----------------  Clearing old records if exists : ------------------- ");
        userRepository.deleteAll();
        System.out.println(" Old data cleared.");
    }

    static void testInsert(UserRepository userRepository){
        System.out.println("\n---------------- Creating new users : ------------------- ");
        userRepository.createUser(101,"nss8889","initial@me.com");
        userRepository.createUser(102,"Warions","friend@somewhere.com");
        userRepository.createUser(103,"nss8889","other-user@here.org");
        System.out.println("New Users created .");
    }

    static void testGetAll(UserRepository userRepository){
        System.out.println("\n---------------- Getting All users : ------------------- ");
        List<User> userList = userRepository.getAllUsers();
        for (User user: userList) {
            System.out.println(user);
        }
    }

    static void testGetById(UserRepository userRepository){
        System.out.println("\n---------------- Getting user by Id 102 : ------------------- ");
        User user = userRepository.searchUserById(102);
        System.out.println(user);
    }

    static void testGetByName(UserRepository userRepository){
        System.out.println("\n----------------  Getting user by name nss8889 : ------------------- ");
        List<User> userList = userRepository.searchUserByName("nss8889");
        for (User user: userList) {
            System.out.println(user);
        }
    }

    static void testGetByIdAndName(UserRepository userRepository){
        System.out.println("\n----------------  Getting user by id 101 and name nss8889 : ------------------- ");
        List<User> userList = userRepository.searchUserByIdAndName(101,"nss8889");
        for (User user: userList) {
            System.out.println(user);
        }
    }

    static void testDeleteById(UserRepository userRepository){
        System.out.println("\n----------------  Deleting user with id 103 : ------------------- ");
        userRepository.deleteUser(103);
        System.out.println(" User with id 103 deleted");
    }

    static void testUpdateNameAndEmailById(UserRepository userRepository){
        System.out.println("\n---------------- Updating user with id 101 : ------------------- ");
        userRepository.updateNameAndEmailById(101,"New nss8889","new-mail@brandnew.com");
        System.out.println("User with id 101 updated .");
    }
}
