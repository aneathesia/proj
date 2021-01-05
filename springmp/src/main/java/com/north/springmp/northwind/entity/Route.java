package com.north.springmp.northwind.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author WHJ
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Route implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("routeID")
    private Integer routeid;

    private String route;

    private String num;


}
