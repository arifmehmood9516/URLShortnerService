package service.gr.com;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

public class ServiceManager {
	
	public static List<String> toMap(Context ctx) throws NamingException {
	    String namespace = "java:global/urlservice/";
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    List<String> names = new ArrayList<String>();
	    //System.out.println("> Listing namespace: " + namespace);
	    NamingEnumeration<NameClassPair> list = ctx.list("java:global/urlservice/");
	    while (list.hasMoreElements()) {
	        NameClassPair next = list.next();
	        String name = next.getName();
	        String jndiPath = namespace + name;
	        names.add(name);
	        Object lookup;
	        try {
	        	 //System.out.println("> Looking up name: " + jndiPath);
	            Object tmp = ctx.lookup(jndiPath);
	            if (tmp instanceof Context) {
	                lookup = toMap((Context) tmp);
	            } else {
	                lookup = tmp.toString();
	            }
	        } catch (Throwable t) {
	            lookup = t.getMessage();
	        }
	        map.put(jndiPath, lookup);
	    }
	    return names;
	}
	
	
	public static Object jndiLookup(Class ejbJndi )
	{
		try {
		InitialContext ctx = new InitialContext();
		List<String> list=toMap(ctx);
		int lp=list.indexOf(ejbJndi.getSimpleName());
		//System.out.println(list.toString());
		String loup="java:global/urlservice/"+list.get(lp+1);
		Object lpObj= ctx.lookup(loup);
		return lpObj;
		}
		catch(Exception e){
			System.out.println(e.getStackTrace().toString());
			return null;
		}
	}
	
	
}
