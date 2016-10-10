package koreatech.multifiaWeb.repository;


import koreatech.multifiaWeb.domain.Searchable;
import koreatech.multifiaWeb.domain.User;
import koreatech.multifiaWeb.repository.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Insert("INSERT INTO fia.USERS (ID, NAME, EMAIL, PASSWORD, AGE) VALUES (#{id}, #{name}, #{email}, #{password}, #{age})")
    void insert(User user);

    @Update("UPDATE fia.USERS SET NAME = #{name}, EMAIL = #{email}, PASSWORD = #{password}, AGE = #{age} WHERE ID = #{id}")
    void update(User user);

    @Select("SELECT * FROM fia.USERS WHERE ID = #{id}")
    User findOne(@Param("id") int id);

    @Select("SELECT * FROM fia.USERS WHERE EMAIL = #{email}")
    User findByEmail(@Param("email") String email);

    @Select("SELECT max(id) FROM fia.USERS")
    Integer findMaxId();

    @Delete("DELETE FROM fia.USERS WHERE ID = #{id}")
    void delete(@Param("id") int id);

    @SelectProvider(type = UserSqlProvider.class, method = "findAllByProvider")
    List<User> findByProvider(Searchable searchable);

    @Select("<script>"
            + "SELECT * FROM fia.USERS"
            + "<if test='name != null'> WHERE NAME = #{name}</if>"
            + "<if test='name != null and email != null'> OR EMAIL = #{email}</if>"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    List<User> findByScript(Searchable searchable);

}
