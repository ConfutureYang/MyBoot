package com.confuture.myboot.dao.mapper;

import com.confuture.myboot.dao.object.UserPassword;
import com.confuture.myboot.dao.object.UserPasswordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserPasswordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    long countByExample(UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int deleteByExample(UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int insert(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int insertSelective(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    List<UserPassword> selectByExample(UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    UserPassword selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int updateByExampleSelective(@Param("record") UserPassword record, @Param("example") UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int updateByExample(@Param("record") UserPassword record, @Param("example") UserPasswordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int updateByPrimaryKeySelective(UserPassword record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_password
     *
     * @mbg.generated Sat Apr 11 16:19:57 CST 2020
     */
    int updateByPrimaryKey(UserPassword record);
}