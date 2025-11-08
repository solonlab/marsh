package org.noear.marsh.uadmin.integration;

import org.noear.solon.cloud.CloudClient;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.Plugin;

/**
 * @author noear 2021/2/16 created
 * @since 1.0
 */
public class MarshUadminSolonPlugin implements Plugin {
    @Override
    public void start(AppContext context) {
        context.beanScan(MarshUadminSolonPlugin.class);

        CloudClient.configLoad("grit", "gritclient.yml");
    }
}
