package cn.czy.nmtjy.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentRecordMapper {
    int deleteByPrimaryKey(Long id);


}