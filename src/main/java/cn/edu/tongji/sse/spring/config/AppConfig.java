package cn.edu.tongji.sse.spring.config;

import cn.edu.tongji.sse.spring.dao.RecordDAO;
import cn.edu.tongji.sse.spring.dao.RecordDAOImpl;
import cn.edu.tongji.sse.spring.dao.WifiDAO;
import cn.edu.tongji.sse.spring.dao.WifiDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * Created by mark on 12/4/16.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.edu.tongji.sse")
public class AppConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public static DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@//10.10.240.118:61521/orcl");
        dataSource.setUsername("wifi_user");
        dataSource.setPassword("tongji1234");
        return dataSource;
    }

    @Bean
    public WifiDAO getWifiDAO(){
        return new WifiDAOImpl(getDataSource());
    }

    @Bean
    public RecordDAO getWifiRecordDAO(){
        return new RecordDAOImpl(getDataSource());
    }
}
