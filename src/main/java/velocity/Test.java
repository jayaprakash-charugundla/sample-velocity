package velocity;

import java.io.FileReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test
{

    public static void main( String[] args ) throws Exception
    {

        VelocityEngine ve = new VelocityEngine();
        //Properties Configuration using Velocity
        /*ve.setProperty( "resource.loader", "class" );
        ve.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        //ve.setProperty( "class.resource.loader.class", ClasspathResourceLoader.class.getName() );
        ve.init();*/

        //Properties Configuration using Java Util 
        /*Properties p = new Properties();
        p.setProperty( "resource.loader", "class" );
        p.setProperty( "class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader" );
        ve.init( p );*/

        //Properties Configuration using Spring
        Resource res = new ClassPathResource( "velocity.properties" );
        Properties prop = new Properties();
        prop.load( res.getInputStream() );
        ve.init( prop );

        //Configuring properties location 
        /*ve.init( "E:\\Work\\Dev_Workspace\\velocity\\target\\classes\\velocity.properties" );*/

        VelocityContext context = new VelocityContext();

        /*        Template template = ve.getTemplate( "person.vsl" );
                VelocityContext context = new VelocityContext();
                context.put( "stringUtils", StringUtils.class );
                context.put( "person", new Person( "Jhon", "London" ) );
                StringWriter writer = new StringWriter();
                template.merge( context, writer );
                System.out.println( writer.toString() );*/

        //Header Section

        //Template headerTemplate = ve.getTemplate( "header.vsl" );
        Header headerObj = new Header( "Sample VSL Project" );
        context.put( "header", headerObj );

        //Body Section 
        Template bodyTemplate = ve.getTemplate( "templates/table.vsl" );
        List<Contract> contractList = new ArrayList<Contract>();

        Contract contract1 = new Contract( "Tangoe1", "contract1", "US" );
        Company company1 = new Company( "Tangoe India1", "India" );
        contract1.setCompanyDetails( company1 );

        Contract contract2 = new Contract( "Tangoe2", "contract2", "US" );
        Company company2 = new Company( "Tangoe India2", "India" );
        contract2.setCompanyDetails( company2 );

        Contract contract3 = new Contract( "Tangoe3", "contract3", "US" );
        Company company3 = new Company( "Tangoe India3", "India" );
        contract3.setCompanyDetails( company3 );

        contractList.add( contract1 );
        contractList.add( contract2 );
        contractList.add( contract3 );
        context.put( "contractList", contractList );

        Map<String, String> map = new HashMap<String, String>();
        map.put( "hi", "hello" );
        context.put( "stringUtils", StringUtils.class );
        StyleConstants styleConstants = new StyleConstantsImpl();
        context.put( "StyleConstants", styleConstants );

        context.put( "map", map );

        JSONParser parser = new JSONParser();

        try
        {
            Object obj = parser.parse( new FileReader( "E:\\Work\\Dev_Workspace\\velocity\\src\\main\\resources\\templates\\menu.json" ) );
            JSONObject jsonObject = (JSONObject) obj;
            context.put( "jsonObj", jsonObject );
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }

        context.put( "person", new Person( "Jayaprakash1", "Bangalore" ) );
        context.put( "TestUtil", TestUtil.class );

        //Footer Section

        Footer footerObj = new Footer( "Footer Content" );
        context.put( "footer", footerObj );

        StringWriter writer = new StringWriter();
        bodyTemplate.merge( context, writer );

        System.out.println( writer.toString() );
        writer.close();
    }
}
