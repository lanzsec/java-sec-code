package org.joychou.utils;

import com.google.common.net.InternetDomainName;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class Security {
    /**
     * @param url
     * @return 安全url返回true，危险url返回false
     */
    public static Boolean checkSafeUrl(String url, String[] urlwhitelist) {
        try {
            URL u = new URL(url);
            URI uri = new URI(url);
            // 判断是否是http(s)协议
            if (!u.getProtocol().startsWith("http") && !u.getProtocol().startsWith("https")) {
                System.out.println("The protocol of url is not http or https.");
                return false;
            }
            // 使用uri获取host
            String host = uri.getHost().toLowerCase();

            // 如果非顶级域名后缀会报错
            String rootDomain = InternetDomainName.from(host).topPrivateDomain().toString();

            for (String whiteurl : urlwhitelist) {
                if (rootDomain.equals(whiteurl)) {
                    return true;
                }
            }

            System.out.println("Url is not safe.");
            return false;
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return false;
        }
    }


    /**
     * @param file
     * @desc 判断文件内容是否是图片
     */
    public static boolean isImage(File file) throws IOException {
        BufferedImage bi = ImageIO.read(file);
        if (bi == null) {
            return false;
        }
        return true;
    }
}