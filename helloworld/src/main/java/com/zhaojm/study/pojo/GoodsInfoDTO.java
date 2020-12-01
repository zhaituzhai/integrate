package com.zhaojm.study.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhaojm.study.config.transform.BigDecimalSerialize;

import java.math.BigDecimal;

/**
 * <pre>类名：</pre>
 * <pre>描述：</pre>
 * <pre>版权: 深圳航天信息</pre>
 * <pre>日期: 2020-08-18 9:12</pre>
 *
 * @author zhaojm
 */
public class GoodsInfoDTO {

    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal price;

    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal quantity;

    private BigDecimal amount;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
