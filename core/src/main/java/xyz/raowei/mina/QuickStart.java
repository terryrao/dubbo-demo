package xyz.raowei.mina;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.nio.charset.Charset;

/**
 * @author terryrao on 2016/8/26.
 */
public class QuickStart {

    public static void main(String[] args) {
        IoAcceptor socketAcceptor = new NioSocketAcceptor();

        socketAcceptor.getFilterChain().addLast("logger", new LoggingFilter());
        socketAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter
                (new TextLineCodecFactory(Charset.forName("utf-8"))));
//        socketAcceptor.setHandler();
    }
}
