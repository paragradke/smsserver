package com.example.authproject.mappers;

import com.example.authproject.mappers.models.Account;
import org.apache.ibatis.annotations.*;

public interface AccountMapper {

    @Insert("INSERT INTO account (auth_id, username) "
            + "VALUES"
            + "(#{account.authId},"
            + "#{account.username})")
    public int save(@Param("account") Account account);


    @Select("SELECT A.auth_id, A.username "
            + " FROM account A "
            + " where A.id = #{accountId}")
    @Results({
            @Result(property = "authId", column = "auth_id"),
            @Result(property = "username", column = "username")
    })
    public Account get(@Param("accountId") final int accountId);


    @Select("SELECT A.auth_id, A.username "
            + " FROM account A "
            + " where A.username = #{username} "
            + "AND "
            + "A.auth_id = #{authId}")
    @Results({
            @Result(property = "authId", column = "auth_id"),
            @Result(property = "username", column = "username")
    })
    public Account getAccount(@Param("username") final String username, @Param("authId") final String authId);

}
