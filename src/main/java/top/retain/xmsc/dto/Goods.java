package top.retain.xmsc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Retain
 * @date 2021/1/15 8:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {
    private Integer goodsId;
    private String goodsName;
    private Double goodsPrice;
    private Integer goodsNum;
    private String goodsDesc;
    private String goodsDetail;
    private String goodsImg;
    private Integer typeId;
}
