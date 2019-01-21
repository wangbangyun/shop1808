package com.qf.shop_back1;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;


@Import(FdfsClientConfig.class)
@SpringBootApplication(scanBasePackages = "com.qf")
public class ShopBack1Application {

    public static void main(String[] args) {
        SpringApplication.run(ShopBack1Application.class, args);
    }

}

