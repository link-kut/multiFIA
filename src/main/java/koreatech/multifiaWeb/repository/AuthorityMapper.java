package koreatech.multifiaWeb.repository;


import koreatech.multifiaWeb.domain.Authority;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityMapper {
    @Insert("INSERT INTO fia.AUTHORITIES (USER_ID, ROLE) VALUES (#{userId}, #{role})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(Authority authority);

    @Update("UPDATE fia.AUTHORITIES SET USER_ID = #{userId}, ROLE = #{role} WHERE ID = #{id}")
    void update(Authority authority);

    @Select("SELECT * FROM fia.AUTHORITIES WHERE USER_ID = #{userId}")
    List<Authority> findByUserId(@Param("userId") int userId);

    @Delete("DELETE FROM fia.AUTHORITIES WHERE ID = #{id}")
    void delete(Authority authority);

}
