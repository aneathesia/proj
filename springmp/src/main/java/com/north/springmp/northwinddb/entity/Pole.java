package com.north.springmp.northwinddb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author WHJ
 * @since 2021-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Pole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("PID")
    private Integer pid;

    private Double coordx;

    private Double coordy;

    private Double height;

    @TableField("PoleCategery")
    private String polecategery;

    @TableField("Sum_dist")
    private Double sumDist;

    private Double distance;


}
