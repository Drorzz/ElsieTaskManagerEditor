package org.drorzz.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.rule.CDN;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

import javax.servlet.ServletContext;

/**
 * Created with IntelliJ IDEA.
 * User: Denis Ivansky
 * Date: 26.12.13
 * Time: 13:46
 */
public class RewriteConfigurationProvider extends HttpConfigurationProvider{
    protected static final Logger LOG = LogManager.getLogger(RewriteConfigurationProvider.class.getName());

    @Override
    public Configuration getConfiguration(ServletContext servletContext) {
        LOG.info("Rewrite is active.");
        return ConfigurationBuilder.begin()

                .addRule(Join.path("/").to("/pages/index.xhtml"))
                .addRule(Join.path("/login").to("/pages/login.xhtml"))
                .addRule(Join.path("/{page}").to("/pages/security/{page}.xhtml"))

                // Pretty ccs and image links
//                .addRule(CDN.relocate("/javax.faces.resource/{file}.xhtml?ln={lib}")
//                        .to("/{lib}/{file}"))
//              .addRule(Join.path("/css/{file}").to("/javax.faces.resource/{file}.xhtml?ln=css"))
//              .addRule(Join.path("/{lib}/{file}").to("/javax.faces.resource/{file}.xhtml?ln={lib}"))
                    //.where("lib").matches("css|img")
                    //.where("file").matches("(\\w)+\\.(\\w)+")
        ;
    }

    @Override
    public int priority() {
        return 10;
    }
}
