package koreatech.multifiaWeb.repository;


import koreatech.multifiaWeb.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrarMapper {
    @Insert("INSERT INTO fia.IDENTIFIER_REGISTRAR (ORCHID, CONTEXT_ID, NAME, SCHEME) " +
            "VALUES (#{orchid}, #{contextId}, #{name}, #{scheme})")
    void identifierInsert(@Param("orchid") String orchid, @Param("contextId") String contextId, @Param("name") String name,
                @Param("scheme") String scheme);

    @Insert("INSERT INTO fia.LOCATOR_REGISTRAR (ORCHID, LOCATOR) " +
            "VALUES (#{orchid}, #{locator})")
    void locatorInsert(@Param("orchid") String orchid, @Param("locator") String locator);

    @Select("SELECT * FROM fia.IDENTIFIER_REGISTRAR")
    IdentifierRegistrar findByAllIdentifier();

    @Select("SELECT * FROM fia.LOCATOR_REGISTRAR")
    LocatorRegistrar findByAllLocator();

    @Select("SELECT LOCATOR FROM fia.LOCATOR_REGISTRAR WHERE ORCHID = #{orchid}")
    String findByLocator(@Param("orchid") String orchid);

    @Select("SELECT SCHEME FROM fia.IDENTIFIER_REGISTRAR WHERE ORCHID = #{orchid}")
    String findByScheme(@Param("orchid") String orchid);

    @Select("SELECT ORCHID FROM fia.IDENTIFIER_REGISTRAR WHERE ORCHID = #{orchid}")
    String findByOrchid(@Param("orchid") String orchid);

    @Delete("DELETE FROM fia.LOCATOR_REGISTRAR WHERE ORCHID = #{orchid}")
    void locatorDelete(@Param("orchid") String orchid);

    @Delete("DELETE FROM fia.IDENTIFIER_REGISTRAR WHERE ORCHID = #{orchid}")
    void identifierDelete(@Param("orchid") String orchid);

    @Select("<script>"
            + "SELECT * FROM fia.USERS"
            + "<if test='name != null'> WHERE NAME = #{name}</if>"
            + "<if test='name != null and email != null'> OR EMAIL = #{email}</if>"
            + "<if test='orderParam != null'>ORDER BY ${orderParam} DESC</if>"
            + "</script>")
    List<User> findByScript(Searchable searchable);

}
