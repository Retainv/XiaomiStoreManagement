package top.retain.xmsc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Retain
 * @date 2021/1/12 18:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Type {

    private Integer id;
    private String name;
    private String description;
}
