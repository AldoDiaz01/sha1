package sha1;
import java.math.BigInteger;
import java.security.MessageDigest;
import Leer.Leer;

/**
 *
 * @author kronoz
 */
public class SHA1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        conexionDB con = conexionDB.getInstance();
        Usuario usu = new Usuario();
        System.out.println("Ingresa un nombre de usuario: ");
        String usuario = Leer.datoString();
        usu.setUsuario(usuario);
        System.out.println("Ingresa su clave: ");
        String clave = Leer.datoString();
        usu.setClave(clave);
		
	// With the java libraries
	try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
	    digest.reset();
	    digest.update(usu.getClave().getBytes("utf8"));
	    usu.setClave(String.format("%040x", new BigInteger(1, digest.digest())));
                
        } catch (Exception e){
            e.printStackTrace();
	}

        boolean registrar = false;
        String sql="INSERT INTO usuarios values ('"+ usu.getUsuario() +
                "','"+usu.getClave()+"')";
        registrar = con.execute(sql);
        
        System.out.println("Registro agregado con éxito");
        
        System.out.println("Con Hash SHA1");
	System.out.println( "El sha1 de usuario \""+ usu.getUsuario() + "\" es:");
	System.out.println( usu.getClave() );
	System.out.println();
	// With Apache commons
	System.out.println("Con Hash Apache");
        usu.setClave(org.apache.commons.codec.digest.DigestUtils.shaHex(clave));
	System.out.println( "El sha1 de usuario \""+ usu.getUsuario() + "\" es:");
	System.out.println( usu.getClave() );
    }
    
}
