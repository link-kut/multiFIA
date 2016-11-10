package koreatech.multifiaWeb.repository;


import koreatech.multifiaWeb.domain.Searchable;
import koreatech.multifiaWeb.domain.ServiceProvider;
import koreatech.multifiaWeb.domain.User;
import koreatech.multifiaWeb.repository.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceMapper {
    @Insert("INSERT INTO fia.SERVICE_PROVIDERS (USER_ID, SERVICE_TYPE, NETWORK_QUALITY, SERVICE_CAPACITY, PLAN, CONTEXT_ID) " +
            "VALUES (#{userId}, #{type}, #{quality}, #{capacity}, #{plan}, #{contextId})")
    void insert(@Param("userId") int userId, @Param("type") String type, @Param("quality") String quality,
                @Param("capacity") String capacity, @Param("plan") String plan, @Param("contextId") String contextId);

    @Select("SELECT max(USER_ID) FROM fia.SERVICE_PROVIDERS")
    Integer findMaxuserId();

    @Update("UPDATE fia.USERS SET NAME = #{name}, EMAIL = #{email}, PASSWORD = #{password}, AGE = #{age} WHERE ID = #{id}")
    void update(ServiceProvider serviceProvider);

    @Select("SELECT * FROM fia.USERS WHERE ID = #{id}")
    User findOne(@Param("id") int id);

    @Select("SELECT NETWORK_QUALITY FROM  fia.SERVICE_PROVIDERS WHERE USER_ID = (SELECT max(USER_ID) FROM fia.SERVICE_PROVIDERS)")
    String findLatestQuality();

    @Select("<script>"
            + "SELECT * FROM fia.USERS"
            + "<if test='name != null'> WHERE NAME = #{name}</if>"
            + "<if test='name != null and email != null'> OR EMAIL = #{email}</if>"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    List<User> findByScript(Searchable searchable);

}
