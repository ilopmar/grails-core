/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grails.web.api

import grails.core.GrailsApplication
import grails.core.GrailsControllerClass
import grails.plugins.GrailsPluginManager
import grails.web.mvc.FlashScope
import grails.web.servlet.mvc.GrailsParameterMap
import org.grails.web.util.GrailsApplicationAttributes
import groovy.transform.CompileStatic

import org.grails.web.servlet.mvc.GrailsWebRequest
import org.springframework.web.context.request.RequestContextHolder

/**
 *
 * Common web attributes inherited by all controllers and tag libraries
 *
 * @author Jeff Brown
 * @author Graeme Rocher
 *
 * @since 3.0
 *
 */
@CompileStatic
trait WebAttributes {
    
    private GrailsApplication grailsApplication

    GrailsWebRequest currentRequestAttributes() {
        (GrailsWebRequest)RequestContextHolder.currentRequestAttributes()
    }
    
    /**
     * Obtains the GrailsApplicationAttributes instance
     *
     * @return The GrailsApplicationAttributes instance
     */
    GrailsApplicationAttributes getGrailsAttributes() {
        currentRequestAttributes().getAttributes()
    }

    /**
     * Obtains the currently executing controller name
     * @return The controller name
     */
    String getControllerName() {
        currentRequestAttributes().getControllerName()
    }

    /**
     * Obtains the currently executing controller namespace
     * @return The controller name
     */
    String getControllerNamespace() {
        currentRequestAttributes().getControllerNamespace()
    }

    /**
     * Obtains the currently executing controller class
     *
     * @return The controller class
     */
    GrailsControllerClass getControllerClass() {
        currentRequestAttributes().getControllerClass()
    }

    /**
     * Obtains the pluginContextPath
     *
     * @param delegate The object the method is being invoked on
     * @return The plugin context path
     */
    String getPluginContextPath() {
        GrailsPluginManager manager = getGrailsApplication().getMainContext().getBean(GrailsPluginManager.class)
        final String pluginPath = manager ? manager.getPluginPathForInstance(this) : null
        return pluginPath ?: ""
    }

    /**
     * Obtains the currently executing action name
     * @return The action name
     */
    String getActionName() {
        currentRequestAttributes().getActionName()
    }
    
    /**
     * Obtains the Grails FlashScope instance
     *
     * @return The FlashScope instance
     */
    FlashScope getFlash() {
        currentRequestAttributes().getFlashScope()
    }

    /**
     * Obtains the Grails parameter map
     *
     * @return The GrailsParameterMap instance
     */
    GrailsParameterMap getParams() {
        currentRequestAttributes().getParams()
    }
    /**
     * Obtains the currently executing web request
     *
     * @return The GrailsWebRequest instance
     */
    GrailsWebRequest getWebRequest() {
        currentRequestAttributes()
    }
    
    /**
     * Obtains the GrailsApplication instance
     * @return The GrailsApplication instance
     */
    GrailsApplication getGrailsApplication() {
        if (grailsApplication == null) {
            grailsApplication = getGrailsAttributes().getGrailsApplication()
        }
        grailsApplication
    }
}
