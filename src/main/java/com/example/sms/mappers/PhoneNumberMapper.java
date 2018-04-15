package com.example.sms.mappers;


import com.example.sms.mappers.models.PhoneNumber;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface PhoneNumberMapper {

    @Select("SELECT P.number, P.account_id, P.id "
            + " FROM phone_number P "
            + " where P.account_id = #{accountId} "
            + "AND "
            + "P.number = #{number}")
    @Results({
            @Result(property = "authId", column = "auth_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "id", column = "id")
    })
    public PhoneNumber getPhoneNumber(@Param("accountId") final int accountId,
                                      @Param("number") final String number);
}
