package com.nowcoder.community.Dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(@Param("userId") int userId,@Param("offset") int offset,@Param("limit") int limit);
    //public List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);//这里这么写还是不行
    int selectDiscussPostRows(@Param("userId")int userId);
}
