package com.mag.conduit.infrastructure.mybatis.mapper;

import com.mag.conduit.application.dto.UserData;
import com.mag.conduit.core.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Optional;
import java.util.UUID;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM conduit_user WHERE id = #{id}")
    Optional<UserData> findById(UUID id);
    @Select("SELECT * FROM conduit_user WHERE username = #{username}")
    Optional<UserData> findByUsername(String username);

    @Select("SELECT * FROM conduit_user WHERE email = #{email}::email")
    Optional<UserData> findByEmail(String email);

    @Insert("INSERT INTO conduit_user (id, username, email, password, bio, image) "
            + "VALUES (#{id}, #{username}, #{email}, #{password}, #{bio}, #{image})")
    void save(User user);

    @Insert({
            "<script>",
            "UPDATE conduit_user",
            "<set>",
                "<if test='username != null'>username = #{username},</if>",
                "<if test='password != null'>password = #{password},</if>",
                "<if test='email != null'>email = #{email}</if>,",
                "<if test='bio != null'>bio = #{bio},</if>",
                "<if test='image != null'>image = #{image}</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    void update(UUID id, String username, String email, String password, String bio, String image);

    @Select("SELECT password FROM conduit_user WHERE email = #{email}::email")
    Optional<String> getPasswordHashFromEmail(String email);
}
