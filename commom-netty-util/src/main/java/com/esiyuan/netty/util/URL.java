package com.esiyuan.netty.util;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @desc : 请求的完整路径
 * @author: 18010318
 * @date : 2018/10/12
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class URL {
    private String host;
    private int port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URL)) return false;
        URL url = (URL) o;
        return port == url.port &&
                Objects.equal(host, url.host);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(host, port);
    }
}
