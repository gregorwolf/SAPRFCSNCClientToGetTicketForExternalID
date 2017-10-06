import java.util.concurrent.CountDownLatch;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoFunctionTemplate;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

/**
 * basic examples for Java to ABAP communication  
 */
public class SNCClientToGetTicketForExternalID
{
    static String ABAP_AS = "ABAP_AS_WITHOUT_POOL";
    
    public static void getTicketForExternalID() throws JCoException
    {
        //JCoDestination is the logic address of an ABAP system and ...
        JCoDestination destination = JCoDestinationManager.getDestination(ABAP_AS);
        // ... it always has a reference to a metadata repository
        JCoFunction function = destination.getRepository().getFunction("SUSR_CHECK_LOGON_DATA");
        if(function == null)
            throw new RuntimeException("SUSR_CHECK_LOGON_DATA not found in SAP.");

        //JCoFunction is container for function values. Each function contains separate
        //containers for import, export, changing and table parameters.
        //To set or get the parameters use the APIS setValue() and getXXX(). 
        
        /*
        function.getImportParameterList().setValue("AUTH_METHOD", "R"); // RFC trusted system
        function.getImportParameterList().setValue("USERID", "BWDEVELOPER");
        */
        function.getImportParameterList().setValue("AUTH_METHOD", "E"); // External ID (PAS)
        function.getImportParameterList().setValue("AUTH_DATA",   "gregor.wolf@gmail.com");
        function.getImportParameterList().setValue("EXTID_TYPE",  "ID"); // External ID from Trusted RFC System
        // function.getImportParameterList().setValue("AUTH_DATA",   "GATEWAY\\GWOLF");
        // function.getImportParameterList().setValue("EXTID_TYPE",  "NT"); // NT Domain User (domain\\user)
        
        try
        {
            //execute, i.e. send the function to the ABAP system addressed 
            //by the specified destination, which then returns the function result.
            //All necessary conversions between Java and ABAP data types
            //are done automatically.
            function.execute(destination);
        }
        catch(AbapException e)
        {
            System.out.println(e.toString());
            return;
        }
        
        System.out.println("SUSR_CHECK_LOGON_DATA finished:");
        System.out.println(" USER_ID: " + function.getExportParameterList().getString("USER_ID"));
        System.out.println(" TICKET : " + function.getExportParameterList().getString("TICKET"));
        System.out.println();
    }    
        
    public static void main(String[] args) throws JCoException
    {
        getTicketForExternalID();
    }
}
