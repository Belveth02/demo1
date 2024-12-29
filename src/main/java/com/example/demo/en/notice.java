package com.example.demo.en;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("notice")
public class notice {
    @TableId(value = "noticeid", type = IdType.AUTO)
    private Integer noticeid;
    private String massage;
    private Date massagedate;
}
