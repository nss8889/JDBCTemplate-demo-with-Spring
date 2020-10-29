package nss.repository;

import nss.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int createUser(Integer id, String name, String email)
    {
       return jdbcTemplate.update("insert into users ( id,  name,  email) values (?,?,?) ", id , name , email);
    }

    public List<User> getAllUsers()
    {
        List<User> userList = jdbcTemplate.query("select id,  name,  email from users ",((resultSet, i) -> {
            User user = new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            return user;
        }));

        return userList;
    }

    public int deleteUser(Integer id)
    {
        return jdbcTemplate.update("delete from users where id = ? ", id );
    }

    public User searchUserById(Integer id)
    {
        // queryForObject returns a single record
        return jdbcTemplate.queryForObject("select id,  name,  email from users where id = ?", new Object[]{id},(resultSet,i) ->
                new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));
    }

    public List<User> searchUserByName(String name)
    {
        List<User> userList = jdbcTemplate.query("select id,  name,  email from users where name = ?", new Object[]{name},(resultSet,i) ->
            new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));

        return userList;
    }

    public List<User> searchUserByIdAndName(Integer id,String name)
    {
        List<User> userList = jdbcTemplate.query("select id,  name,  email from users where id = ? and name = ? ", new Object[]{id,name},(resultSet,i) ->
                new User(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3)));

        return userList;
    }

    public void deleteAll()
    {
        jdbcTemplate.execute("delete from users");
    }

    public int updateNameAndEmailById(Integer id, String name, String email)
    {
        return jdbcTemplate.update("update users set name = ? , email = ?  where id = ?", name , email, id );
    }
}
