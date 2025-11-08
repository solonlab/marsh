package org.noear.marsh.base.handler;

import org.noear.snack4.ONode;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录处理器，含输入输出及异常[后置处理]
 *
 * @author noear
 */
public class BaseLogHandler implements Handler {
    static Logger log = LoggerFactory.getLogger(BaseLogHandler.class);

    @Override
    public void handle(Context ctx) throws Throwable {
        TagsMDC.tag0(ctx.pathNew());

        if (ctx.errors == null) {
            String output = ctx.attr("output");

            if(output == null) {
                if (ctx.result instanceof String) {
                    output = (String) ctx.result;
                } else {
                    output = ONode.serialize(ctx.result);
                }
            }

            log.info("> Header: {}\n> Param: {}\n\n< Body: {}",
                    ONode.serialize(ctx.headerMap()),
                    ONode.serialize(ctx.paramMap()),
                    output);
        } else {
            log.error("> Header: {}\n> Param: {}\n\n< Error: {}",
                    ONode.serialize(ctx.headerMap()),
                    ONode.serialize(ctx.paramMap()),
                    ctx.errors);
        }
    }
}
