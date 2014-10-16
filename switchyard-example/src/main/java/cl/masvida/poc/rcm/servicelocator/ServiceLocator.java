package cl.masvida.poc.rcm.servicelocator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @briefClase {@link ServiceLocator} Se utiliza esta lógica de
 *             negocio como parte de la estrategia del plan migración y
 *             reingeniería de aplicaciones del SNA. 
 * @author TLine Inc.
 * @date Febrero 28, 2014
 * 
 */
public class ServiceLocator {

    private Map<String, Object> cache;
    private InitialContext ic;
    private static ServiceLocator me;
    //private static final String jndiLocalLookUp = "java:comp/env/";//Resources.getValue("basicWebParam_path", "lookUpLocalJndi");

    static {
        try {
            me = new ServiceLocator();
        } catch (Exception se) {
            se.printStackTrace(System.err);
        }
    }

    private ServiceLocator() {
        try {
            ic = new InitialContext();
            cache = Collections.synchronizedMap(new HashMap<String, Object>());
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }

    public static final ServiceLocator getInstance() {
        return me;
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    /**
     * Conecta con un EJB Remoto 
     * @param jndiHomeName
     * @return
     * @throws ServiceLocatorException
     */
//    public Object doRemoteLookUp(String app, String module, String distinct, String bean, String interfaz)throws ServiceLocatorException{
//    	Object ejbRemote = null;    	
//    	//ArrayList list = leerArchivoProperties();
//    	//String ip = (String) list.get(0);
//    	//String port = (String) list.get(1);
//    	String jndi="ejb:" + app + "/" + module + "/" + distinct + "/" + bean + "!" + interfaz;
//    	try {
//            if (cache.containsKey(jndi)) {
//                ejbRemote = (Object) cache.get(jndi);
//            } else {            	      	            	            	                      	       
//            	final Hashtable jndiProperties = new Hashtable();
//            	jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//                Context ctx = new InitialContext(jndiProperties);
//               // System.out.println("Got context");                
//                ejbRemote = (Object) ctx.lookup(jndi);             	            	            	            	            	            	
//                cache.put(jndi, ejbRemote);
//            }
//        } catch (NamingException ex) {
//            throw new ServiceLocatorException("JNDI "+jndi+ " incorrecto", ex.getCause());
//        } catch (Exception e) {
//            throw new ServiceLocatorException(e.getCause());
//        }
//        return ejbRemote;    	
//    }
    
    public Object doRemoteLookUp(String app, String module, String distinct, String bean, String interfaz)throws ServiceLocatorException{
    	Context context=null;
    	Object ejbRemote = null;
        try{
        	Properties props = new Properties();
        	//props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        	props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        	props.put("jboss.naming.client.ejb.context", true);
        	props.put(Context.PROVIDER_URL, "remote://localhost:4447");
        	context = new InitialContext(props);
        	System.out.println("\n\tGot initial Context: "+context);
        	// Lookup Format will be
        	// <app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
        	ejbRemote=(Object)context.lookup(app+"/"+module+"/"+bean+"!"+interfaz);
        
        }catch (Exception e){
        	e.printStackTrace();
        	throw new ServiceLocatorException(e);
        }
        
        return ejbRemote;
    }
}
