package ribbonconfiguration;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.ym.content.config.NacosWeightedRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Ribbon规则
 */
@Configuration
public class RibbonConfiguration {

    /**
     * 设置Ribbon要使用的规则
     *
     * @return
     */
    @Bean
    public IRule RibbonRule() {
        return new NacosWeightedRule();
    }

    @Bean
    public IPing pingRule() {
        return new PingUrl();
    }

}
