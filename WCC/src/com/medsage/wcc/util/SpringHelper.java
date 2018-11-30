package com.medsage.wcc.util;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
/**
 * Helper class for accessing the Spring context object.  This class loads up a default application
 * context configuration file and delegates requests for any spring registered services to the context.
 * By always using this class, the context will always be initialized only once and from only one spot.
 */
public class SpringHelper  implements ApplicationContextAware
{
    private static List contexts = new LinkedList();
    private static ApplicationContext rootContext;

    private boolean isRootContext;

    private static Log log = LogFactory.getLog(SpringHelper.class);

    public void setApplicationContext(ApplicationContext context)
    {
        if (isRootContext)
        {
            if (rootContext == null)
                rootContext = context;
            else
                throw new RuntimeException("Attempt to set root context more than once, only one root context allowed per application");
        } else
        {
            contexts.add(context);
            if (rootContext != null)
            {
                if (context instanceof ConfigurableApplicationContext)
                {
                    ((ConfigurableApplicationContext)context).setParent(rootContext);
                    ((ConfigurableApplicationContext)context).refresh();
                    log.info("Set root context as parent of new context");
                } else
                {
                    log.warn("Received context but unable to cast to ConfigurableApplicationContext");
                }
            }
        }
    }



    public void setIsRootContext(boolean isRoot)
    {
        this.isRootContext = isRoot;
    }

    /*
     *  @param name name of bean to lookup in application context
     *  @return Object
    */

    public static Object getBean(String name)
    {
        Object obj = null;
        try
        {
            obj = rootContext.getBean(name);
            if (obj != null)
                return obj;
        } catch (Exception e)
        {
            log.error("Caught exception looking up bean with name " + name + " in root context", e);
        }

        for (int i = 0, n = contexts.size(); i < n; i++)
        {
            ApplicationContext context = (ApplicationContext)contexts.get(i);
            if (context instanceof ConfigurableApplicationContext)
            {
                if (((ConfigurableApplicationContext)context).getParent() == null)
                {
                    ((ConfigurableApplicationContext)context).setParent(rootContext);
                    ((ConfigurableApplicationContext)context).refresh();
                    log.info("Set root context as parent of new context");
                }
            } else
            {
                log.warn("Received context but unable to cast to ConfigurableApplicationContext");
            }
            try
            {
                obj = context.getBean(name);
                if (obj != null)
                    return obj;
            }   catch (Exception e)
            {
                log.info("Caught exception looking for bean with name: " + name + " in context: " + context.toString(), e);
            }
        }

        throw new RuntimeException("Unable to load bean with name " + name);
    }

}


