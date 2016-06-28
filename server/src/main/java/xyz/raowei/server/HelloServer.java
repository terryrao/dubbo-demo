package xyz.raowei.server;

import org.springframework.stereotype.Service;
import xyz.raowei.api.service;

/**
 * ${DESCRIPTION}
 * create: 2016-06-22 10:27
 *
 * @author admin
 */
@Service("helloServer")
public class HelloServer implements service {
    public String hello(String name) {
        return "hello :" + name;
    }


    public static void main(String[] args) {
        String javaVersion = System.getProperty("java.version");
        String majorJavaVersion = null;
        // version String should look like "1.4.2_10"
        if (javaVersion.indexOf("1.8.") != -1) {
            majorJavaVersion = "JAVA_18";
        }else
        if (javaVersion.indexOf("1.7.") != -1) {
            majorJavaVersion = "JAVA_17";
        }
        else if (javaVersion.indexOf("1.6.") != -1) {
            majorJavaVersion = "JAVA_16";
        }
        else if (javaVersion.indexOf("1.5.") != -1) {
            majorJavaVersion = "JAVA_15";
        }
        else {
            // else leave 1.4 as default (it's either 1.4 or unknown)
            majorJavaVersion = "JAVA_14";
        }

        System.out.println(majorJavaVersion);
    }
}
