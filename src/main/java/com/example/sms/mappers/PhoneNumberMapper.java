package com.example.sms.mappers;


import com.example.sms.mappers.models.PhoneNumber;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PhoneNumberMapper {

    @Select("SELECT P.number, P.account_id, P.id "
            + "FROM phone_number P "
            + "WHERE P.account_id = #{accountId} "
            + "AND "
            + "trim(number) = #{number,javaType=java.lang.String,jdbcType=VARCHAR,typeHandler=org.apache.ibatis.type.StringTypeHandler}")
    @Results({
            @Result(property = "accountId", column = "account_id"),
            @Result(property = "number", column = "number"),
            @Result(property = "id", column = "id")
    })
    public PhoneNumber getPhoneNumber(@Param("accountId") final int accountId,
                                      @Param("number") final String number);

}
