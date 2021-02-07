package top.retain.xmsc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Retain
 * @date 2021/1/12 18:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private Integer id;
    private String account;
    private String name;
    private String pwd;
    private String img;
    private String tel;
}
