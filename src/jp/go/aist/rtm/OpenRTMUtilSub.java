// -*- Java -*-
// <rtc-template block="description">
/*!
 * @file OpenRTMUtil.java
 * @date $Date$
 *
 * $Id$
 */
// </rtc-template>

package jp.go.aist.rtm;

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;
import jp.go.aist.rtm.RTC.RegisterModuleFunc;
import jp.go.aist.rtm.RTC.util.Properties;

/**
 * OpenRTMUtil
 * <p> 
 * ModuleDescription
 */
public class OpenRTMUtilSub implements RtcNewFunc, RtcDeleteFunc, RegisterModuleFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
            "implementation_id", "OpenRTMUtil",
            "type_name",         "OpenRTMUtil",
            "description",       "ModuleDescription",
            "version",           "1.0.0",
            "vendor",            "VenderName",
            "category",          "Category",
            "activity_type",     "STATIC",
            "max_instance",      "1",
            "language",          "Java",
            "lang_type",         "compile",
    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new OpenRTMUtilImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
    public void registerModule() {
        Properties prop = new Properties(component_conf);
        final Manager manager = Manager.instance();
        manager.registerFactory(prop, new OpenRTMUtilSub(), new OpenRTMUtilSub());
    }
}
