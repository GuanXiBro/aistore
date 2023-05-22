package com.ai.th.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-05-15
 */
@ApiModel(value = "Airole对象", description = "")
public class Airole implements Serializable {

    private static final long serialVersionUID = 1L;

      private Integer id;

    private String discription;

    private String name;

    
    public Integer getId() {
        return id;
    }

      public void setId(Integer id) {
          this.id = id;
      }
    
    public String getDiscription() {
        return discription;
    }

      public void setDiscription(String discription) {
          this.discription = discription;
      }
    
    public String getName() {
        return name;
    }

      public void setName(String name) {
          this.name = name;
      }

    @Override
    public String toString() {
        return "Airole{" +
              "id=" + id +
                  ", discription=" + discription +
                  ", name=" + name +
              "}";
    }
}
