package images;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Bdd {
	private String url = "jdbc:mysql://localhost:3306/bibliotheque";
	private String login = "root";
	private String password = "WINDSURF";
	private Statement statement = null;
	private ResultSet result = null;
	private Connection conn;
	private FileInputStream entree;
	public Bdd() {
	}

	public void initialiserConnexion(String fichierConfig) throws Exception {
		Properties propBD = new Properties();

		try {
			FileInputStream entree = new FileInputStream(fichierConfig);
			propBD.load(entree);
		} finally {
			entree.close();
		}

		Class.forName(propBD.getProperty("driver"));
		conn = DriverManager.getConnection(propBD.getProperty("url"), propBD.getProperty("user"),
				propBD.getProperty("password"));
	}

	public void deconnexion() throws Exception {
		if (conn != null) {
			conn.close();
		}
	}


public void sauveIMG(String location, String name) throws Exception 
{
  File monImage = new File(location);
  FileInputStream istreamImage = new FileInputStream(monImage);
  try 
  {
    PreparedStatement ps = conn.prepareStatement("insert into image (name, img) values (?,?)");
    try 
    {
        ps.setString(1, name);
        ps.setBinaryStream(2, istreamImage, (int) monImage.length());
        ps.executeUpdate();
    }
    finally 
    {
      ps.close();
    }
  } 
  finally 
  {
    istreamImage.close();
  }
}

public void chargeIMG(String name, String location) throws Exception
{
  File monImage = new File(location);
  FileOutputStream ostreamImage = new FileOutputStream(monImage);
            
  try
  {
    PreparedStatement ps = conn.prepareStatement("select img from image where name=?");

    try
    {
      ps.setString(1,name);
      ResultSet rs = ps.executeQuery();
      
      try
      {
        if(rs.next())
        {
      	  InputStream istreamImage = rs.getBinaryStream("img");
      
      	  byte[] buffer = new byte[1024];
      	  int length = 0;
	
      	  while((length = istreamImage.read(buffer)) != -1)
      	  {
      	    ostreamImage.write(buffer, 0, length);
	  }
  	}
      }
      finally
      {
        rs.close();
      }
    }
    finally
    {
      ps.close();
    }
  }
  finally
  {
    ostreamImage.close();
  }
}

public Vector getAllNames() throws Exception
{
  Vector res = new Vector();

  Statement stmt = conn.createStatement();

  try
  {
    ResultSet rset = stmt.executeQuery("select name from image");

    try
    {
      while(rset.next())
      {
        res.add(rset.getString("name"));
      }
    }
    finally
    {
      rset.close();
    }
  }
  finally
  {
    stmt.close();
  }

  return res;
}
}