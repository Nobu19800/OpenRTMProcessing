// -*- Java -*-
// <rtc-template block="description">
/*!
 * @file OpenRTMUtilComp.java
 * @brief Standalone component
 * @date $Date$
 *
 * $Id$
 */
// </rtc-template>
package jp.go.aist.rtm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.ModuleInitProc;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.util.Properties;
import jp.go.aist.rtm.RTC.port.InPort;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;

/**
 * OpenRTMUtilComp
 * <p>
 * Standalone component Class
 *
 */
public class OpenRTMUtil implements ModuleInitProc {
    static String m_instanceName = new String("");
    private Properties component_conf = new Properties(OpenRTMUtilSub.component_conf);
    RTObject_impl comp;
    
    public OpenRTMUtil() {}

    public void myModuleInit(Manager mgr) {
      //Properties prop = new Properties(OpenRTMUtilSub.component_conf);
      mgr.registerFactory(component_conf, new OpenRTMUtilSub(), new OpenRTMUtilSub());

      // prepare arg for createComponent()
      /*
      if (!m_instanceName.isEmpty()) {
          arg += "?instance_name=" + m_instanceName;
      }
      */
      // Create a component
      comp = mgr.createComponent("OpenRTMUtil");
      if( comp==null ) {
          System.err.println("Component create failed.");
          System.exit(0);
      }
      
      // Example
      // The following procedure is examples how handle RT-Components.
      // These should not be in this function.

//      // Get the component's object reference
//      Manager manager = Manager.instance();
//      RTObject rtobj = null;
//      try {
//          rtobj = RTObjectHelper.narrow(manager.getPOA().servant_to_reference(comp));
//      } catch (ServantNotActive e) {
//          e.printStackTrace();
//      } catch (WrongPolicy e) {
//          e.printStackTrace();
//      }
//
//      // Get the port list of the component
//      PortListHolder portlist = new PortListHolder();
//      portlist.value = rtobj.get_ports();
//
//      // getting port profiles
//      System.out.println( "Number of Ports: " );
//      System.out.println( portlist.value.length );
//      for( int intIdx=0;intIdx<portlist.value.length;++intIdx ) {
//          Port port = portlist.value[intIdx];
//          System.out.println( "Port" + intIdx + " (name): ");
//          System.out.println( port.get_port_profile().name );
//        
//          PortInterfaceProfileListHolder iflist = new PortInterfaceProfileListHolder();
//          iflist.value = port.get_port_profile().interfaces;
//          System.out.println( "---interfaces---" );
//          for( int intIdx2=0;intIdx2<iflist.value.length;++intIdx2 ) {
//              System.out.println( "I/F name: " );
//              System.out.println( iflist.value[intIdx2].instance_name  );
//              System.out.println( "I/F type: " );
//              System.out.println( iflist.value[intIdx2].type_name );
//              if( iflist.value[intIdx2].polarity==PortInterfacePolarity.PROVIDED ) {
//                  System.out.println( "Polarity: PROVIDED" );
//              } else {
//                  System.out.println( "Polarity: REQUIRED" );
//              }
//          }
//          System.out.println( "---properties---" );
//          NVUtil.dump( new NVListHolder(port.get_port_profile().properties) );
//          System.out.println( "----------------" );
//      }
    }

    //public static void createComponent(String[] args) {
    public void createComponent(String name) {
    	component_conf.setProperty("type_name", name);
        // store instance_name to static and removed args created
        List<String> mgrargs = new ArrayList();
        /*
        for (int i = 0; i < args.length; ++i) {
            if (args[i].indexOf("--instance_name=") == -1) {
                mgrargs.add(args[i]);
            } else {
                m_instanceName = args[i].replace("--instance_name=", "");
            }
        }*/    
        // Initialize manager
        final Manager manager = Manager.init(mgrargs.toArray(new String[mgrargs.size()]));

        // Set module initialization proceduer
        // This procedure will be invoked in activateManager() function.
        
        manager.setModuleInitProc(this);

        // Activate manager and register to naming service
        manager.activateManager();

        // run the manager in blocking mode
        // runManager(false) is the default.
        manager.runManager(true);

        // If you want to run the manager in non-blocking mode, do like this
        // manager.runManager(true);
    }
    
    public <T> InPort<T> addInPort(String name, DataRef<T> inport) {
    	InPort<T> inInport = new InPort<T>("in", inport);
    	
    	try {
    		comp.addInPort("in", inInport);
    	} 
    	catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
    	}
    	
    	return inInport;
    }
    
    public <T> OutPort<T> addOutPort(String name, DataRef<T> outport) {
    	OutPort<T> outOutport = new OutPort<T>("in", outport);
    	
    	try {
    		comp.addOutPort("in", outOutport);
    	} 
    	catch (Exception e) {
    	   e.printStackTrace();
    	   return null;
    	}
    	
    	return outOutport;
    }
    
    public void exit()
    {
    	final Manager manager = Manager.instance();
    	manager.shutdown();
    }

}
